package com.xk.demo.service;
import java.util.List;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.demo.model.entity.DemoClassroomEntity;
import com.xk.demo.model.dto.DemoClassroomDto;
/**
* 描述：班级信息 服务实现层接口
* @author LV
* @date 2019-05-04
*/
public interface IDemoClassroomService{

    /**
    * 分页查询班级信息
    *
    * @param pageQueryDto
    * @return
    */
    PageDto<DemoClassroomDto> page(PageQueryDto <DemoClassroomEntity> pageQueryDto);

    /**
    * 保存班级信息信息
    *
    * @param formData
    * @return
    */
    DemoClassroomDto save(DemoClassroomDto formData);

    /**
    * 删除班级信息信息
    *
    * @return
    */
    APIResponse<String> removeMulti(String ids);

    /**
    * 根据班级信息id查询班级信息信息
    *
    * @param id 主键id
    * @return
    */
   DemoClassroomDto getById(String id);

    /**
     * 根据id删除班级信息
     * @param id
     * @return 0=成功 1=参数有误 2=系统异常
     */
    APIResponse<String> remove(String id);

}