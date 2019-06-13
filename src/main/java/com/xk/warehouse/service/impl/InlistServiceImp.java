package com.xk.warehouse.service.impl;

import com.xk.common.core.dict.service.CommonDictService;
import com.xk.framework.common.*;
import com.xk.framework.jpa.specification.SimpleSpecificationBuilder;
import com.xk.warehouse.dao.*;
import com.xk.warehouse.model.dto.InlistDTO;
import com.xk.warehouse.model.entity.DemoIndetail;
import com.xk.warehouse.model.entity.DemoInlist;
import com.xk.warehouse.model.entity.Posdetail;
import com.xk.warehouse.model.entity.Stock;
import com.xk.warehouse.service.IInlistServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class InlistServiceImp implements IInlistServiceImp {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private InlistRepository Inlistdao;
    @Autowired
    private AreaRepository arearepository;
    @Autowired
    private PositionsRepository positionsrepository;
    @Autowired
    private IndetailRepository indetailrepository;
    @Autowired
    private PosdetailRepository posdetailrepository;
     @Autowired
    private CommonDictService cacheService;
     @Autowired
     private  StockRepository stockrepository;
    @Transactional(readOnly = true)
    @Override
    public PageDto<InlistDTO> page(PageQueryDto<DemoInlist> pageDto) {
        try {
            Page<DemoInlist> pageData =Inlistdao.queryPage(pageDto);
            if (pageData == null || pageData.getContent() == null || pageData.getContent().size() <= 0) {
                return null;
            }
            List<InlistDTO> lists = BeanMapperUtils.mapList(pageData.getContent(), DemoInlist.class,
                    InlistDTO.class);
            //把实体类表的部分信息手动关联到dto信息中
           for(int i=0;i<pageData.getContent().size();i++){
               lists.get(i).setInstate(cacheService.getDictByCode("inSate",""+lists.get(i).getDatastatus()).getDictname());
           }
            // 设置当前的记录，总记录数，总页数，当前页
            return new PageDto<InlistDTO>(lists, pageData.getTotalElements(), pageData.getTotalPages(),pageData.getNumber());
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }
    @Transactional
    @Override
    public InlistDTO save(@RequestBody InlistDTO formData) throws Exception{
        try {
            DemoInlist demoinlistEntity = new DemoInlist();
            if (formData != null && !StringUtils.isEmpty(formData.getId())) {
                demoinlistEntity = Inlistdao.findOne(formData.getId());
            }
            demoinlistEntity.setDatastatus(0);
            BeanMapperUtils.map(formData, demoinlistEntity);
            // 保存
            return BeanMapperUtils.map(Inlistdao.saveAndFlush(demoinlistEntity), InlistDTO.class);
        } catch (Exception e) {
            logger.error("保存数据失败");
            e.printStackTrace();
            throw new ServiceException("保存数据失败", CommonErrorCode.SAVE_ERROR);
        }
    }
    @Transactional
    @Override
    public void insert(String ids) throws Exception{
        try{
            SimpleSpecificationBuilder<DemoIndetail> ssb=new SimpleSpecificationBuilder<DemoIndetail>();
            ssb.add("inlist.id",Constants.OPER_EQ,ids);
            List<DemoIndetail> list=indetailrepository.findAll(ssb.generateSpecification());
            for(int i=0;i<list.size();i++){
                //库位库存表修改
                SimpleSpecificationBuilder<Posdetail> ssb2=new SimpleSpecificationBuilder<Posdetail>();
                ssb2.add("position.id",Constants.OPER_EQ,list.get(i).getPositionid());
                ssb2.add("product.id",Constants.OPER_EQ,list.get(i).getProduct().getId());
                List<Posdetail> list5=posdetailrepository.findAll(ssb2.generateSpecification());
                if(posdetailrepository.findAll(ssb2.generateSpecification()).size()>0){
                    Posdetail posdetail2=posdetailrepository.findAll(ssb2.generateSpecification()).get(0);
                    posdetail2.setStorenum(list.get(i).getQty()+posdetail2.getStorenum());
                    posdetailrepository.saveAndFlush(posdetail2);
                }
                else{
                Posdetail posdetail =new Posdetail();
                posdetail.setProduct(list.get(i).getProduct());
                posdetail.setStorenum(list.get(i).getQty());
                posdetail.setPosition(positionsrepository.findOne(list.get(i).getPositionid()));
                posdetailrepository.save(posdetail);
                }
                //总库存表修改
                String areaid=positionsrepository.findOne(list.get(i).getPositionid()).getArea().getId();
                SimpleSpecificationBuilder<Stock> ssb3=new SimpleSpecificationBuilder<Stock>();
                ssb3.add("depot.id",Constants.OPER_EQ,arearepository.findOne(areaid).getDepot().getId());
                ssb3.add("product.id",Constants.OPER_EQ,list.get(i).getProduct().getId());
                if(stockrepository.findAll(ssb3.generateSpecification()).size()>0){
                    Stock stock2=stockrepository.findAll(ssb3.generateSpecification()).get(0);
                    stock2.setProductnum(list.get(i).getQty()+stock2.getProductnum());
                    stockrepository.saveAndFlush(stock2);
                }
                else {
                Stock stock=new Stock();
                stock.setProduct(list.get(i).getProduct());
                double a=list.get(i).getQty();
                stock.setProductnum(list.get(i).getQty());
                stock.setDepot(arearepository.findOne(areaid).getDepot());
                stockrepository.save(stock);
            }
                Inlistdao.findOne(ids).setDatastatus(1);
                Inlistdao.findOne(ids).setIntime(new Date());
                Inlistdao.saveAndFlush(Inlistdao.findOne(ids));
            }
        }
        catch(Exception e){
            logger.error("入库失败");
            e.printStackTrace();
            throw new ServiceException("入库数据失败", CommonErrorCode.SAVE_ERROR);
        }
    }
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public APIResponse<String> removeMulti(String ids) {
        try {
            //判断传入的ID是否为空，且查询该入库单下是否有明细
            if(StringUtils.isNotEmpty(ids)){
                String[] idArray = ids.split(",");
                if(null!=idArray && idArray.length>0){
                    for(String id:idArray){
                        SimpleSpecificationBuilder<DemoIndetail> ssb=new SimpleSpecificationBuilder<DemoIndetail>();
                        ssb.add("inlist.id",Constants.OPER_EQ,id);
                        List<DemoIndetail> list=indetailrepository.findAll(ssb.generateSpecification());
                        if(list.size()>0){
                            return new APIResponse <String>(2,"删除数据失败，该入库单下有明细数据！");
                        }
                    }
                }

            }
            Inlistdao.removeMuiltByIds(ids);
        } catch (ServiceException e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", e.errorCode);
        }
        return new APIResponse <String>(0,"数据删除成功！");
    }
}
