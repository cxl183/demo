package com.xk.warehouse.service;

import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.outdetailDTO;
import com.xk.warehouse.model.entity.Posdetail;
import com.xk.warehouse.model.entity.outdetail;

public interface IOutdetailServiceImp {
    public PageDto<outdetailDTO> page(PageQueryDto<outdetail> pageDto);
    public PageDto<outdetailDTO> pag(PageQueryDto<Posdetail> pageDto);
    public outdetailDTO save(outdetailDTO formData);
    public void removeMulti(String ids);
}
