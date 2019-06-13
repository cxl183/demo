package com.xk.warehouse.service;

import com.xk.warehouse.model.dto.CategoryDTO;
import com.xk.warehouse.model.entity.DemoCategory;

import java.util.List;

public interface ICategoryServiceImp {
    public List<CategoryDTO> ducs();//查询商品种类
    public List<String> select();//获取所有种类名称
    public void addm(String id,String name,String remark) throws Exception;//增加商品种类
    public DemoCategory findbyId(String id);
}
