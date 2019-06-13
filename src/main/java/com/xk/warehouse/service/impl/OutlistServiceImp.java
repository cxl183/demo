package com.xk.warehouse.service.impl;

import com.xk.common.core.dict.service.CommonDictService;
import com.xk.framework.common.*;
import com.xk.framework.jpa.specification.SimpleSpecificationBuilder;
import com.xk.warehouse.dao.*;
import com.xk.warehouse.model.dto.outlistDTO;
import com.xk.warehouse.model.entity.Posdetail;
import com.xk.warehouse.model.entity.Stock;
import com.xk.warehouse.model.entity.outdetail;
import com.xk.warehouse.model.entity.outlist;
import com.xk.warehouse.service.IOutlistServiceImp;
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
public class OutlistServiceImp implements IOutlistServiceImp {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OutlistRepository outlistrepository;
    @Autowired
    private OutdetailRepository outdetailrepository;
    @Autowired
    private CommonDictService cacheService;
    @Autowired
    private StockRepository stockrepository;
    @Autowired
    private PosdetailRepository posdetailrepository;
    @Autowired
    private AreaRepository arearepository;
    @Autowired
    private PositionsRepository positionsrepository;
    @Transactional(readOnly = true)
    @Override
    public PageDto<outlistDTO> page(PageQueryDto<outlist> pageDto) {
        try {
            Page<outlist> pageData =outlistrepository.queryPage(pageDto);
            if (pageData == null || pageData.getContent() == null || pageData.getContent().size() <= 0) {
                return null;
            }
            List<outlistDTO> lists = BeanMapperUtils.mapList(pageData.getContent(), outlist.class,
                    outlistDTO.class);
            //把实体类表的部分信息手动关联到dto信息中
            for(int i=0;i<pageData.getContent().size();i++){
                lists.get(i).setOutstate(cacheService.getDictByCode("outliststate",""+lists.get(i).getDatastatus()).getDictname());
            }
            // 设置当前的记录，总记录数，总页数，当前页
            return new PageDto<outlistDTO>(lists, pageData.getTotalElements(), pageData.getTotalPages(),pageData.getNumber());
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }
    @Transactional
    @Override
    public outlistDTO save(@RequestBody outlistDTO formData) throws Exception{
        try {
            outlist demooutlistEntity = new outlist();
            if (formData != null && !StringUtils.isEmpty(formData.getId())) {
                demooutlistEntity = outlistrepository.findOne(formData.getId());
            }
            demooutlistEntity.setDatastatus(0);
            BeanMapperUtils.map(formData, demooutlistEntity);
            // 保存
            return BeanMapperUtils.map(outlistrepository.saveAndFlush(demooutlistEntity), outlistDTO.class);
        } catch (Exception e) {
            logger.error("保存数据失败");
            e.printStackTrace();
            throw new ServiceException("保存数据失败", CommonErrorCode.SAVE_ERROR);
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
                        SimpleSpecificationBuilder<outdetail> ssb=new SimpleSpecificationBuilder<outdetail>();
                        ssb.add("outlist.id",Constants.OPER_EQ,id);
                        List<outdetail> list=outdetailrepository.findAll(ssb.generateSpecification());
                        if(list.size()>0){
                            return new APIResponse <String>(2,"删除数据失败，该出库单下有明细数据！");
                        }
                    }
                }

            }
            outlistrepository.removeMuiltByIds(ids);
        } catch (ServiceException e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", e.errorCode);
        }
        return new APIResponse <String>(0,"数据删除成功！");
    }
    @Transactional
    @Override
    public void insert(String ids) throws Exception{
        try{
            SimpleSpecificationBuilder<outdetail> ssb=new SimpleSpecificationBuilder<outdetail>();
            ssb.add("outlist.id",Constants.OPER_EQ,ids);
            List<outdetail> list=outdetailrepository.findAll(ssb.generateSpecification());
            for(int i=0;i<list.size();i++){
                //库位库存表修改
                SimpleSpecificationBuilder<Posdetail> ssb2=new SimpleSpecificationBuilder<Posdetail>();
                ssb2.add("position.id",Constants.OPER_EQ,list.get(i).getPositionid());
                ssb2.add("product.id",Constants.OPER_EQ,list.get(i).getProduct().getId());
                List<Posdetail> list5=posdetailrepository.findAll(ssb2.generateSpecification());
                if(posdetailrepository.findAll(ssb2.generateSpecification()).size()>0){
                    Posdetail posdetail2=posdetailrepository.findAll(ssb2.generateSpecification()).get(0);
                    if (posdetail2.getStorenum()-list.get(i).getQty()<0){throw new ServiceException("出库数据失败,库位库存数量不足", CommonErrorCode.SAVE_ERROR);};
                    posdetail2.setStorenum(posdetail2.getStorenum()-list.get(i).getQty());
                    posdetailrepository.saveAndFlush(posdetail2);
                }
                else{
                    throw new ServiceException("出库数据失败,库位库存表不存在该信息", CommonErrorCode.SAVE_ERROR);
                }
                //总库存表修改
                String areaid=positionsrepository.findOne(list.get(i).getPositionid()).getArea().getId();
                SimpleSpecificationBuilder<Stock> ssb3=new SimpleSpecificationBuilder<Stock>();
                ssb3.add("depot.id",Constants.OPER_EQ,arearepository.findOne(areaid).getDepot().getId());
                ssb3.add("product.id",Constants.OPER_EQ,list.get(i).getProduct().getId());
                if(stockrepository.findAll(ssb3.generateSpecification()).size()>0){
                    Stock stock2=stockrepository.findAll(ssb3.generateSpecification()).get(0);
                    if (stock2.getProductnum()-list.get(i).getQty()<0){throw new ServiceException("出库数据失败,总库存数量不足", CommonErrorCode.SAVE_ERROR);};
                    stock2.setProductnum(stock2.getProductnum()-list.get(i).getQty());
                    stockrepository.saveAndFlush(stock2);
                }
                else {
                    throw new ServiceException("出库数据失败,总库存表不存在该信息", CommonErrorCode.SAVE_ERROR);
                }
                outlistrepository.findOne(ids).setDatastatus(1);
                outlistrepository.findOne(ids).setOuttime(new Date());
                outlistrepository.saveAndFlush(outlistrepository.findOne(ids));
            }
        }
        catch(Exception e){
            logger.error("入库失败");
            e.printStackTrace();
            throw new ServiceException("入库数据失败", CommonErrorCode.SAVE_ERROR);
        }
    }
}
