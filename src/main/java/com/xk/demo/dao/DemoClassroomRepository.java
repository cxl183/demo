package com.xk.demo.dao;

import com.xk.demo.model.entity.DemoClassroomEntity;
import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 描述：班级信息DAO接口
* @author LV
* @date 2019-05-04
*/
public interface DemoClassroomRepository extends JpaSpecificationExecutor<DemoClassroomEntity>, BaseRepository<DemoClassroomEntity, String>  {

}