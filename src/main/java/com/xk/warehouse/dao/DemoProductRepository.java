package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.DemoProduct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DemoProductRepository extends JpaSpecificationExecutor<DemoProduct>, BaseRepository<DemoProduct, String>{
}
