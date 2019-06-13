package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.outlist;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutlistRepository extends JpaSpecificationExecutor<outlist>, BaseRepository<outlist, String> {
}
