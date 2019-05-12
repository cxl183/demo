package com.xk.demo.service;
import java.util.List;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.demo.model.entity.DemoStudentEntity;
import com.xk.demo.model.dto.DemoStudentDto;
/**
* 描述：学生信息 服务实现层接口
* @author LV
* @date 2019-05-04
*/
public interface IDemoStudentService{

    /**
    * 分页查询学生信息
    *
    * @param pageQueryDto
    * @return
    */
    PageDto<DemoStudentDto> page(PageQueryDto <DemoStudentEntity> pageQueryDto);

    /**
    * 保存学生信息信息
    *
    * @param formData
    * @return
    */
    DemoStudentDto save(DemoStudentDto formData);

    /**
    * 删除学生信息信息
    *
    * @return
    */
    void removeMulti(String ids);

    /**
    * 根据学生信息id查询学生信息信息
    *
    * @param id 主键id
    * @return
    */
   DemoStudentDto getById(String id);

    /**
    * 根据id删除学生信息
    *
    * @param id 主键
    */
    public void remove(String id);

}