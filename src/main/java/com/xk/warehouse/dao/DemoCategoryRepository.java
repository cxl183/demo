package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.DemoCategory;
import com.xk.warehouse.model.entity.DemoProduct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * 描述：产品种类DAO接口
 *
 * @date 2019-05-16
 */
public interface DemoCategoryRepository extends JpaSpecificationExecutor<DemoCategory>, BaseRepository<DemoCategory, String> {

}