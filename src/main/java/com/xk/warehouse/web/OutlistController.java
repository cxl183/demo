package com.xk.warehouse.web;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.outlistDTO;
import com.xk.warehouse.model.entity.outlist;
import com.xk.warehouse.service.IOutlistServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse/outlist")
@Api(value = "出库信息管理接口")
public class OutlistController {
    @Autowired
    private IOutlistServiceImp  ioutlistserviceimp;
    @ApiOperation(value = "分页获取出库单信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType="pageQueryDto")})
    public APIResponse<outlistDTO> page(PageQueryDto<outlist> pageDto){
        PageDto<outlistDTO> pd =ioutlistserviceimp.page(pageDto);
        return new APIResponse<outlistDTO>(pd);
    }
    @ApiOperation(value = "保存出库单", httpMethod = "POST")
    @ApiImplicitParam(name = "formData", value = "保存信息", required = true, dataType = "ProductDto")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public APIResponse<outlistDTO> save(@RequestBody outlistDTO formData) throws Exception {
        if (null != formData) {
            return new APIResponse<outlistDTO>(ioutlistserviceimp.save(formData));
        }
        return new APIResponse<outlistDTO>(-1, "数据传输为空");
    }
    /**
     * 描述：删除多个出库信息
     * @param ids 班级信息ids
     */
    @ApiOperation(value = "删除多个出库信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    @RequestMapping(value = "/multiDel", method = RequestMethod.DELETE)
    public APIResponse<String> multiDel(String ids) {
        if(StringUtils.isNotEmpty(ids)) {
            return  ioutlistserviceimp.removeMulti(ids);
        }
        return new APIResponse<String>(-1, "数据传输为空");
    }
    /**
     * 描述：入库产品
     * @param id 明细信息id
     */
    @ApiOperation(value = "出库产品", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "出库产品", required = true, dataType = "String")
    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public void insert(String id){
        try {
            ioutlistserviceimp.insert(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
