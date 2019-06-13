package com.xk.warehouse.service;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.InlistDTO;
import com.xk.warehouse.model.entity.DemoInlist;
import org.springframework.web.bind.annotation.RequestBody;

public interface IInlistServiceImp {
    public PageDto<InlistDTO> page(PageQueryDto<DemoInlist> pageDto);//分页获取订单信息
    public InlistDTO save(@RequestBody InlistDTO formData) throws Exception;
    public void insert(String ids) throws Exception;
    public APIResponse<String> removeMulti(String ids);
}
