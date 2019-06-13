package com.xk.warehouse.web;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.ProductDto;
import com.xk.warehouse.model.entity.DemoProduct;
import com.xk.warehouse.service.IProductServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse/product")
@Api(value = "产品信息管理接口")
public class ProductController {//展示所有产品，包括查询产品的展示
    @Autowired
    private IProductServiceImp productService;

    @ApiOperation(value = "分页获取产品信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType="pageQueryDto")})
    public APIResponse<ProductDto> page(PageQueryDto< DemoProduct > pageDto){
        PageDto<ProductDto> pd =productService.page(pageDto);
        return new APIResponse<ProductDto>(pd);
    }
    @ApiOperation(value = "保存产品", httpMethod = "POST")
    @ApiImplicitParam(name = "formData", value = "保存信息", required = true, dataType = "ProductDto")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public APIResponse<ProductDto> save(@RequestBody ProductDto formData) throws Exception {
        if (null != formData) {
            return new APIResponse<ProductDto>(productService.addp(formData));
        }
        return new APIResponse<ProductDto>(-1, "数据传输为空");
    }
    /**
     * 描述:删除产品信息
     * @param ids  产品信息id
     */
    @ApiOperation(value = "删除多个产品信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    @RequestMapping(value = "/multiDel", method = RequestMethod.DELETE)
    public APIResponse<ProductDto> multiDel(String ids) {
        try {
            productService.detep(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new APIResponse<ProductDto>(0, "数据删除成功");
    }
    /**
     * 描述：根据Id查询
     * @param id  产品信息id
     */
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public APIResponse<ProductDto> get(@PathVariable("id") String id)throws Exception {
        return new APIResponse<ProductDto>(productService.getById(id));
    }
    /**
     * 描述：更新产品信息
     * @param id 学产品信息id
     */
    @ApiOperation(value = "更新产品信息数据", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "id", paramType = "path", dataType = "String", value = "id"),
            @ApiImplicitParam(name = "formData", value = "更新信息", required = true, dataType = "ProductDto")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public APIResponse<ProductDto> update(@PathVariable("id") String id, @RequestBody ProductDto formData) throws Exception {
        if (null != formData) {
            return new APIResponse<ProductDto>(productService.addp(formData));
        }
        return new APIResponse<ProductDto>(-1, "数据传输为空");
    }
}
