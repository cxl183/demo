package com.xk.warehouse.service.impl;

import com.xk.framework.common.*;
import com.xk.warehouse.dao.*;
import com.xk.warehouse.model.dto.IndetailDTO;
import com.xk.warehouse.model.entity.DemoIndetail;
import com.xk.warehouse.service.IIndetailServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IndetailServiceImp implements IIndetailServiceImp {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IndetailRepository indetailrepository;
    @Autowired
    private PositionsRepository positionrepository;
    @Autowired
    private AreaRepository arearepository;
    @Autowired
    private DemoProductRepository demoproductrepository;
    @Autowired
    private InlistRepository inlistrepository;
    @Transactional(readOnly = true)
    @Override
    public PageDto<IndetailDTO> page(PageQueryDto<DemoIndetail> pageDto) {
        try {
            Page<DemoIndetail> pageData =indetailrepository.queryPage(pageDto);
            if (pageData == null || pageData.getContent() == null || pageData.getContent().size() <= 0) {
                return null;
            }
            List<IndetailDTO> lists = BeanMapperUtils.mapList(pageData.getContent(), DemoIndetail.class, IndetailDTO.class);
            List<DemoIndetail> list=pageData.getContent();
            //把库位等详细信息存放至DTO中
            for(int i=0;i<lists.size();i++){
                lists.get(i).setProductcode(list.get(i).getProduct().getProductcode());
                lists.get(i).setProductname(list.get(i).getProduct().getProductname());
                lists.get(i).setPositionname(positionrepository.findOne(list.get(i).getPositionid()).getPositionname());
                lists.get(i).setAreaname(positionrepository.findOne(list.get(i).getPositionid()).getArea().getAreaname());
                String areaid=positionrepository.findOne(list.get(i).getPositionid()).getArea().getId();
                lists.get(i).setDepotname(arearepository.findOne(areaid).getDepot().getDepotname());
            }
            // 设置当前的记录，总记录数，总页数，当前页
            return new PageDto<IndetailDTO>(lists, pageData.getTotalElements(), pageData.getTotalPages(),pageData.getNumber());
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
    public IndetailDTO save(IndetailDTO formData) {
        try {
            DemoIndetail demoindetail = new DemoIndetail();
            if (formData != null && !StringUtils.isEmpty(formData.getId())) {
                demoindetail = indetailrepository.findOne(formData.getId());
            }

            BeanMapperUtils.map(formData, demoindetail);
            // 保存
            demoindetail.setInlist(inlistrepository.findOne(formData.getInlistid()));
            demoindetail.setProduct(demoproductrepository.findOne((formData.getProductid())));
            demoindetail.setDatastatus(0);
            return BeanMapperUtils.map(indetailrepository.saveAndFlush(demoindetail), IndetailDTO.class);
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
            indetailrepository.removeMuiltByIds(ids);
        } catch (Exception e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", CommonErrorCode.DELETE_ERROR);
        }
    }
}
