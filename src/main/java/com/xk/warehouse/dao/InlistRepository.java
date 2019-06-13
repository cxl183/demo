package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.DemoInlist;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InlistRepository extends JpaSpecificationExecutor<DemoInlist>, BaseRepository<DemoInlist, String> {
}

