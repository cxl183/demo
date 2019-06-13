package com.xk.warehouse.web;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.outdetailDTO;
import com.xk.warehouse.model.entity.Posdetail;
import com.xk.warehouse.model.entity.outdetail;
import com.xk.warehouse.service.IOutdetailServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse/outdetail")
@Api(value = "出库明细管理接口")
public class OutdetailController {
    @Autowired
    private IOutdetailServiceImp ioutdetailserviceimp;
    /**
     * 描述：根据Id查询
     *
     */
    @ApiOperation(value = "分页获取入库明细信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType="pageQueryDto")})
    public APIResponse<outdetailDTO> page(PageQueryDto<outdetail> pageDto){
        PageDto<outdetailDTO> pd =ioutdetailserviceimp.page(pageDto);
        return new APIResponse<outdetailDTO>(pd);
    }
    @ApiOperation(value = "分页获取库存产品信息", httpMethod = "GET")
    @RequestMapping(value = "/select", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType="pageQueryDto")})
    public APIResponse<outdetailDTO>  pag(PageQueryDto<Posdetail> pageDto){
        PageDto<outdetailDTO> pd =ioutdetailserviceimp.pag(pageDto);
        return new APIResponse<outdetailDTO>(pd);
    }
    @ApiOperation(value = "保存出库明细信息", httpMethod = "POST")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formData", value = "保存信息", dataType="IndetailDTO")})
    public APIResponse<outdetailDTO> save(@RequestBody outdetailDTO formData){
        if (null != formData) {
            return new APIResponse<outdetailDTO>(ioutdetailserviceimp.save(formData));
        }
        return new APIResponse<outdetailDTO>(-1, "数据传输为空");
    }
    /**
     * 描述：删除多个明细信息
     * @param ids 明细信息ids
     */
    @ApiOperation(value = "删除多个明细信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    @RequestMapping(value = "/multiDel", method = RequestMethod.DELETE)
    public APIResponse<outdetailDTO> multiDel(String ids) {
        ioutdetailserviceimp.removeMulti(ids);
        return new APIResponse<outdetailDTO>(0, "数据删除成功");
    }
}
