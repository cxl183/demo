package com.xk.warehouse.dao;

import com.xk.framework.jpa.reposiotry.base.BaseRepository;
import com.xk.warehouse.model.entity.outdetail;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutdetailRepository extends JpaSpecificationExecutor<outdetail>, BaseRepository<outdetail, String> {
}
