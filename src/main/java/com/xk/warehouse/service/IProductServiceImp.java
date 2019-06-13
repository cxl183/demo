package com.xk.warehouse.service;

import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.ProductDto;
import com.xk.warehouse.model.entity.DemoProduct;

import java.util.List;

public interface IProductServiceImp {
    public List<ProductDto> pro(ProductDto puc);//查询产品

    public ProductDto addp(ProductDto product) throws Exception;//新增产品

    public void detep(String ids) throws Exception;//删除产品

    public PageDto<ProductDto> page(PageQueryDto<DemoProduct> pageDto);//分页查询

    public ProductDto getById(String id);//根据id获取信息
}
