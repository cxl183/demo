package com.xk.demo.service.impl;
import com.xk.demo.model.dto.DemoClassroomDto;
import com.xk.demo.model.entity.DemoStudentEntity;
import com.xk.demo.dao.DemoStudentRepository;
import com.xk.demo.service.IDemoClassroomService;
import com.xk.demo.service.IDemoStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xk.demo.model.dto.DemoStudentDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.xk.framework.common.*;


import java.util.ArrayList;
import java.util.List;

/**
* 描述：学生信息 服务实现层
* @author LV
* @date 2019-05-04
*/
@Service
public class DemoStudentServiceImpl implements IDemoStudentService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DemoStudentRepository demoStudentRepository;
    @Autowired
    private IDemoClassroomService demoClassroomService;



    /**
    * 保存
    *
    * @param formData 保存或者更新的实体
    * @return DemoStudentDto
    */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public DemoStudentDto save(DemoStudentDto formData) {
        try {
            DemoStudentEntity demoStudentEntity = new DemoStudentEntity();
            if (formData != null && !StringUtils.isEmpty(formData.getId())) {
                demoStudentEntity = demoStudentRepository.findOne(formData.getId());
            }

            BeanMapperUtils.map(formData, demoStudentEntity);
            // 保存
            return BeanMapperUtils.map(demoStudentRepository.saveAndFlush(demoStudentEntity), DemoStudentDto.class);
        } catch (Exception e) {
            logger.error("保存数据失败");
            e.printStackTrace();
            throw new ServiceException("保存数据失败", CommonErrorCode.SAVE_ERROR);
        }
    }

    /**
    * 获取学生信息信息
    *
    * @param id 主键id
    * @return DemoStudentDto
    */
    @Transactional(readOnly = true)
    @Override
    public DemoStudentDto getById(String id) {
        try {
            DemoStudentEntity demoStudentEntity = demoStudentRepository.findOne(id);
            if (demoStudentEntity == null) {
            return null;
            }
            return BeanMapperUtils.map(demoStudentEntity, DemoStudentDto.class);
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
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
            demoStudentRepository.removeMuiltByIds(ids);
        } catch (Exception e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", CommonErrorCode.DELETE_ERROR);
         }
    }

    /**
    * 删除单个学生信息信息
    *
    * @param id 学生信息编号
    */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public void remove(String id) {
        try {
            //逻辑删除，将delFlag的值改为1
            demoStudentRepository.remove(id);
            //物理删除，直接从数据库中删除
             demoStudentRepository.delete(id);
        } catch (Exception e) {
            logger.error("删除数据失败");
            e.printStackTrace();
            throw new ServiceException("删除数据失败", CommonErrorCode.DELETE_ERROR);
        }
    }

    /**
    * 分页获取学生信息信息
    *
    * @param pageDto 分页的数据
    * @return PageDto {@code<DemoStudentDto>}
    */
    @Transactional(readOnly = true)
    @Override
    public PageDto<DemoStudentDto> page(PageQueryDto<DemoStudentEntity> pageDto) {
        try {
            Page<DemoStudentEntity> pageData = demoStudentRepository.queryPage(pageDto);
            if (pageData == null || pageData.getContent() == null || pageData.getContent().size() <= 0) {
                return null;
            }
            List<DemoStudentDto> lists = BeanMapperUtils.mapList(pageData.getContent(), DemoStudentEntity.class,
                DemoStudentDto.class);
            //查询班级信息，并将班级信息关联到学生信息
            if(null!=lists && lists.size()>0 && StringUtils.isNotEmpty(lists.get(0).getCid())){
                DemoClassroomDto classroomDto=demoClassroomService.getById(lists.get(0).getCid());
                if(null!=classroomDto && StringUtils.isNotEmpty(classroomDto.getGrade()) && StringUtils.isNotEmpty(classroomDto.getName())){
                    for (DemoStudentDto stu:lists){
                        stu.setClassroom(classroomDto.getName());
                        stu.setGrade(classroomDto.getGrade());
                    }
                }
            }
            // 设置当前的记录，总记录数，总页数，当前页
            return new PageDto<DemoStudentDto>(lists, pageData.getTotalElements(), pageData.getTotalPages(),pageData.getNumber());
        } catch (Exception e) {
            logger.error("查询数据失败");
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }



}

