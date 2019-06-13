package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.Posdetail;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PosdetailRepository extends JpaSpecificationExecutor<Posdetail>, BaseRepository<Posdetail, String> {
}
