package com.xk.warehouse.service;

import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.IndetailDTO;
import com.xk.warehouse.model.entity.DemoIndetail;


public interface IIndetailServiceImp {
    public PageDto<IndetailDTO> page(PageQueryDto<DemoIndetail> pageDto);//查询
    public IndetailDTO save(IndetailDTO formData);//保存
    public void removeMulti(String ids);//删除
}
