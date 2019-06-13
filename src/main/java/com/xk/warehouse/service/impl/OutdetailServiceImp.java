package com.xk.warehouse.service.impl;

import com.xk.framework.common.*;
import com.xk.framework.jpa.specification.SimpleSpecificationBuilder;
import com.xk.warehouse.dao.*;
import com.xk.warehouse.model.dto.outdetailDTO;
import com.xk.warehouse.model.entity.Posdetail;
import com.xk.warehouse.model.entity.outdetail;
import com.xk.warehouse.service.IOutdetailServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class OutdetailServiceImp implements IOutdetailServiceImp {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OutdetailRepository outdetailrepository;
    @Autowired
    private PositionsRepository positionsrepository;
    @Autowired
    private AreaRepository arearepository;
    @Autowired
    private DemoProductRepository demoproductrepository;
    @Autowired
    private OutlistRepository outlistrepository;
    @Autowired
    private PosdetailRepository posdetailrepository;
    @Transactional(readOnly = true)
    @Override
    public PageDto<outdetailDTO> page(PageQueryDto<outdetail> pageDto) {
        try {
            Page<outdetail> pageData =outdetailrepository.queryPage(pageDto);
            if (pageData == null || pageData.getContent() == null || pageData.getContent().size() <= 0) {
                return null;
            }
            List<outdetailDTO> lists = BeanMapperUtils.mapList(pageData.getContent(), outdetail.class, outdetailDTO.class);
            List<outdetail> list=pageData.getContent();
            //把库位等详细信息存放至DTO中
            for(int i=0;i<lists.size();i++){
                lists.get(i).setProductcode(list.get(i).getProduct().getProductcode());
                lists.get(i).setProductname(list.get(i).getProduct().getProductname());
                lists.get(i).setPositionname(positionsrepository.findOne(list.get(i).getPositionid()).getPositionname());
                lists.get(i).setAreaname(positionsrepository.findOne(list.get(i).getPositionid()).getArea().getAreaname());
                String areaid=positionsrepository.findOne(list.get(i).getPositionid()).getArea().getId();
                lists.get(i).setDepotname(arearepository.findOne(areaid).getDepot().getDepotname());
            }
            // 设置当前的记录，总记录数，总页数，当前页
            return new PageDto<outdetailDTO>(lists, pageData.getTotalElements(), pageData.getTotalPages(),pageData.getNumber());
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public PageDto<outdetailDTO> pag(PageQueryDto<Posdetail> pageDto) {
        try {
            SimpleSpecificationBuilder<Posdetail> ssb = pageDto.genSimpleSpecification();
            Page<Posdetail> pages = posdetailrepository.page(ssb, pageDto.getPageIndex(), pageDto.getPageSize());
            List<Posdetail> list = pages.getContent();
            List<outdetailDTO> list2 = new ArrayList<outdetailDTO>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStorenum()!=0){//如果库存为0，不显示
                outdetailDTO ourdetai=new outdetailDTO();
                ourdetai.setCategoryname(list.get(i).getProduct().getDemoCategory().getCategoryname());
                ourdetai.setDelFlag(list.get(i).getDelFlag());
                ourdetai.setProductid(list.get(i).getProduct().getId());
                ourdetai.setQty(list.get(i).getStorenum());
                ourdetai.setProductcode(list.get(i).getProduct().getProductcode());
                ourdetai.setProductname(list.get(i).getProduct().getProductname());
                ourdetai.setPositionname(list.get(i).getPosition().getPositionname());
                ourdetai.setPositionid(list.get(i).getPosition().getId());
                ourdetai.setAreaname(list.get(i).getPosition().getArea().getAreaname());
                String areaid=list.get(i).getPosition().getArea().getId();
                ourdetai.setDepotname(arearepository.findOne(areaid).getDepot().getDepotname());
                SimpleSpecificationBuilder<outdetail> ssb2 = new SimpleSpecificationBuilder<outdetail>();
                ssb2.add("product.id",Constants.OPER_EQ,list.get(i).getProduct().getId());
                ssb2.add("positionid",Constants.OPER_EQ,list.get(i).getPosition().getId());
                ssb2.add("outlist.datastatus",Constants.OPER_EQ,0);
                List<outdetail> outlist=outdetailrepository.findAll(ssb2.generateSpecification());
                double a=ourdetai.getQty();
                if(outlist.size()>0){//把其他还未提交出库的明细中同库位同产品的数量减去，展示实际可出库的数量
                for(int c=0;c<outlist.size();c++){
                    a-=outlist.get(c).getQty();
                }
                }
               ourdetai.setLeastqty(a);
                list2.add(ourdetai);
                }
            }
            return new PageDto<outdetailDTO>(list2, pages.getTotalElements(), pages.getTotalPages(),pages.getNumber());
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }
    /**
     * 保存
     *
     * @param formData 保存或者更新的实体
     * @return DemoStudentDto
     */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public outdetailDTO save(outdetailDTO formData) {
        try {
            outdetail demooutdetail = new outdetail();
            if (formData != null && !StringUtils.isEmpty(formData.getId())) {
                demooutdetail = outdetailrepository.findOne(formData.getId());
            }

            BeanMapperUtils.map(formData, demooutdetail);
            // 保存
            demooutdetail.setOutlist(outlistrepository.findOne(formData.getOutlistid()));
            demooutdetail.setProduct(demoproductrepository.findOne((formData.getProductid())));
            demooutdetail.setDatastatus(0);
            return BeanMapperUtils.map(outdetailrepository.saveAndFlush(demooutdetail), outdetailDTO.class);
        } catch (Exception e) {
            logger.error("保存数据失败");
            e.printStackTrace();
            throw new ServiceException("保存数据失败", CommonErrorCode.SAVE_ERROR);
        }
    }
    /**
     * 删除多个学生信息信息
     *
     * @param ids 删除的id
     */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public void removeMulti(String ids) {
        try {
            outdetailrepository.removeMuiltByIds(ids);
        } catch (Exception e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", CommonErrorCode.DELETE_ERROR);
        }
    }
}
