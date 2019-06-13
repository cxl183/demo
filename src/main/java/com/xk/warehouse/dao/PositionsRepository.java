package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.DemoPosition;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PositionsRepository extends JpaSpecificationExecutor<DemoPosition>, BaseRepository<DemoPosition, String> {
}
