package com.xk.demo.dao;

import com.xk.demo.model.entity.DemoStudentEntity;
import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
* 描述：学生信息DAO接口
* @author LV
* @date 2019-05-04
*/
public interface DemoStudentRepository extends JpaSpecificationExecutor<DemoStudentEntity>, BaseRepository<DemoStudentEntity, String>  {
    /**
     * 根据班级ID查询学生数量
     * @param cid
     * @return
     */
    public Long countByCidAndDelFlag(String cid,Integer delFlag);

}