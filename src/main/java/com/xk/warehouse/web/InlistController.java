package com.xk.warehouse.web;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.InlistDTO;
import com.xk.warehouse.model.entity.DemoInlist;
import com.xk.warehouse.service.IInlistServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse/inlist")
@Api(value = "入库信息管理接口")
public class InlistController {
    @Autowired
    private IInlistServiceImp inlistService;
    @ApiOperation(value = "分页获取入库单信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType="pageQueryDto")})
    public APIResponse<InlistDTO> page(PageQueryDto<DemoInlist> pageDto){
        PageDto<InlistDTO> pd =inlistService.page(pageDto);
        return new APIResponse<InlistDTO>(pd);
    }
    @ApiOperation(value = "保存产品", httpMethod = "POST")
    @ApiImplicitParam(name = "formData", value = "保存信息", required = true, dataType = "ProductDto")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public APIResponse<InlistDTO> save(@RequestBody InlistDTO formData) throws Exception {
        if (null != formData) {
            return new APIResponse<InlistDTO>(inlistService.save(formData));
        }
        return new APIResponse<InlistDTO>(-1, "数据传输为空");
    }
    /**
     * 描述：更新班级信息
     * @param id 班级信息id
     */
    @ApiOperation(value = "更新入库单信息数据", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "id", paramType = "path", dataType = "String", value = "id"),
            @ApiImplicitParam(name = "formData", value = "更新信息", required = true, dataType = "InlistDTO")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public APIResponse<InlistDTO> update(@PathVariable("id") String id, @RequestBody InlistDTO formData) throws Exception {
        if (null != formData) {
            return new APIResponse<InlistDTO>(inlistService.save(formData));
        }
        return new APIResponse<InlistDTO>(-1, "数据传输为空");
    }
    /**
     * 描述：入库产品
     * @param id 明细信息id
     */
    @ApiOperation(value = "入库产品", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "入库产品", required = true, dataType = "String")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(String id){
        try {
            inlistService.insert(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 描述：删除多个入库信息
     * @param ids 班级信息ids
     */
    @ApiOperation(value = "删除多个入库信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    @RequestMapping(value = "/multiDel", method = RequestMethod.DELETE)
    public APIResponse<String> multiDel(String ids) {
        if(StringUtils.isNotEmpty(ids)) {
            return  inlistService.removeMulti(ids);
        }
        return new APIResponse<String>(-1, "数据传输为空");
    }
}
