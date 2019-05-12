package com.xk.demo.service.impl;
import com.xk.demo.dao.DemoStudentRepository;
import com.xk.demo.model.entity.DemoClassroomEntity;
import com.xk.demo.dao.DemoClassroomRepository;
import com.xk.demo.service.IDemoClassroomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xk.demo.model.dto.DemoClassroomDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.xk.framework.common.*;
import tk.mybatis.mapper.util.StringUtil;


import java.util.ArrayList;
import java.util.List;

/**
* 描述：班级信息 服务实现层
* @author LV
* @date 2019-05-04
*/
@Service
public class DemoClassroomServiceImpl implements IDemoClassroomService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DemoClassroomRepository demoClassroomRepository;
    @Autowired
    private DemoStudentRepository demoStudentRepository;


    /**
    * 保存
    *
    * @param formData 保存或者更新的实体
    * @return DemoClassroomDto
    */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public DemoClassroomDto save(DemoClassroomDto formData) {
        try {
            DemoClassroomEntity demoClassroomEntity = new DemoClassroomEntity();
            if (formData != null && !StringUtils.isEmpty(formData.getId())) {
                demoClassroomEntity = demoClassroomRepository.findOne(formData.getId());
            }

            BeanMapperUtils.map(formData, demoClassroomEntity);
            // 保存
            return BeanMapperUtils.map(demoClassroomRepository.saveAndFlush(demoClassroomEntity), DemoClassroomDto.class);
        } catch (Exception e) {
            logger.error("保存数据失败");
            e.printStackTrace();
            throw new ServiceException("保存数据失败", CommonErrorCode.SAVE_ERROR);
        }
    }

    /**
    * 获取班级信息信息
    *
    * @param id 主键id
    * @return DemoClassroomDto
    */
    @Transactional(readOnly = true)
    @Override
    public DemoClassroomDto getById(String id) {
        try {
            DemoClassroomEntity demoClassroomEntity = demoClassroomRepository.findOne(id);
            if (demoClassroomEntity == null) {
            return null;
            }
            return BeanMapperUtils.map(demoClassroomEntity, DemoClassroomDto.class);
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }
    /**
    * 删除多个班级信息信息
    *
    * @param ids 删除的id
    */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public APIResponse<String> removeMulti(String ids) {
        try {
            //判断传入的ID是否为空，且查询该班级下是否有学生
            if(StringUtils.isNotEmpty(ids)){
                String[] idArray = ids.split(",");
                if(null!=idArray && idArray.length>0){
                    for(String id:idArray){
                        Long sn=demoStudentRepository.countByCidAndDelFlag(id,0);
                        if(null!=sn && sn>0){
                            return new APIResponse <String>(2,"删除数据失败，该班级下有学生数据！");
                        }
                    }
                }

            }
            demoClassroomRepository.removeMuiltByIds(ids);
        } catch (ServiceException e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", e.errorCode);
         }
        return new APIResponse <String>(0,"数据删除成功！");
    }

    /**
    * 删除单个班级信息信息
    *
    * @param id 班级信息编号
    */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public APIResponse<String> remove(String id) {
        try {
            if(StringUtils.isEmpty(id)){
                return new APIResponse <String>(-1,"参数有误！");
            }
            Long sn=demoStudentRepository.countByCidAndDelFlag(id,0);
            if(null!=sn && sn>0){
                return new APIResponse <String>(2,"删除数据失败，该班级下有学生数据！");
            }
            //逻辑删除，改变delFlag的值为1
             demoClassroomRepository.remove(id);
            //物理删除，直接冲数据库中删除
            //demoClassroomRepository.delete(id);
             return new APIResponse <String>(0,"数据删除成功！");
        } catch (Exception e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", CommonErrorCode.DELETE_ERROR);
        }
    }

    /**
    * 分页获取班级信息信息
    *
    * @param pageDto 分页的数据
    * @return PageDto {@code<DemoClassroomDto>}
    */
    @Transactional(readOnly = true)
    @Override
    public PageDto<DemoClassroomDto> page(PageQueryDto<DemoClassroomEntity> pageDto) {
        try {
            Page<DemoClassroomEntity> pageData = demoClassroomRepository.queryPage(pageDto);
            if (pageData == null || pageData.getContent() == null || pageData.getContent().size() <= 0) {
                return null;
            }
            List<DemoClassroomDto> lists = BeanMapperUtils.mapList(pageData.getContent(), DemoClassroomEntity.class,
                DemoClassroomDto.class);

            // 设置当前的记录，总记录数，总页数，当前页
            return new PageDto<DemoClassroomDto>(lists, pageData.getTotalElements(), pageData.getTotalPages(),pageData.getNumber());
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }



}

