package com.xk.warehouse.web;

import com.xk.framework.common.APIResponse;
import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;
import com.xk.warehouse.model.dto.IndetailDTO;
import com.xk.warehouse.model.entity.DemoIndetail;
import com.xk.warehouse.service.IIndetailServiceImp;
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
@RequestMapping("/api/warehouse/indetail")
@Api(value = "入库明细管理接口")
public class IndetailController {
    @Autowired
    private IIndetailServiceImp indetailservice;
    /**
     * 描述：根据Id查询
     * @param id  入库明细信息id
     */
    @ApiOperation(value = "分页获取入库明细信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType="pageQueryDto")})
    public APIResponse<IndetailDTO> page(PageQueryDto<DemoIndetail> pageDto){
        PageDto<IndetailDTO> pd =indetailservice.page(pageDto);
        return new APIResponse<IndetailDTO>(pd);
    }
    @ApiOperation(value = "保存入库明细信息", httpMethod = "POST")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formData", value = "保存信息", dataType="IndetailDTO")})
    public APIResponse<IndetailDTO> save(@RequestBody IndetailDTO formData){
        if (null != formData) {
            return new APIResponse<IndetailDTO>(indetailservice.save(formData));
        }
        return new APIResponse<IndetailDTO>(-1, "数据传输为空");
    }
    /**
     * 描述：删除多个明细信息
     * @param ids 明细信息ids
     */
    @ApiOperation(value = "删除多个明细信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    @RequestMapping(value = "/multiDel", method = RequestMethod.DELETE)
    public APIResponse<IndetailDTO> multiDel(String ids) {
        indetailservice.removeMulti(ids);
        return new APIResponse<IndetailDTO>(0, "数据删除成功");
    }
}
