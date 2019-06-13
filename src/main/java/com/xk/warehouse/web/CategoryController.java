package com.xk.warehouse.web;

import com.xk.warehouse.model.dto.CategoryDTO;
import com.xk.warehouse.service.ICategoryServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse/category")
@Api(value = "分类信息管理接口")
public class CategoryController {
    @Autowired
    private ICategoryServiceImp categoryService;
    @ApiOperation(value = "获取种类信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = RequestMethod.GET)
public List<CategoryDTO> findall(){
        List<CategoryDTO> list=null;
    try{
         list=categoryService.ducs();
        return list;
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return list;
}
}
