package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.DemoDepot;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepotRepository extends JpaSpecificationExecutor<DemoDepot>, BaseRepository<DemoDepot, String> {
}
