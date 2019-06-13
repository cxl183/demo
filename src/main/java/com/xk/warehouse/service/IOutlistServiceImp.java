package com.xk.warehouse.service;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.outlistDTO;
import com.xk.warehouse.model.entity.outlist;
import org.springframework.web.bind.annotation.RequestBody;

public interface IOutlistServiceImp {
    public PageDto<outlistDTO> page(PageQueryDto<outlist> pageDto);
    public outlistDTO save(@RequestBody outlistDTO formData) throws Exception;
    public APIResponse<String> removeMulti(String ids);
    public void insert(String ids) throws Exception;
}
