package com.xk.demo.web;


import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;


import com.xk.demo.service.IDemoClassroomService;
import com.xk.demo.model.entity.DemoClassroomEntity;
import com.xk.demo.model.dto.DemoClassroomDto;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xk.framework.common.APIResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
* 描述：班级信息控制层
* @author LV
* @since 2019-05-04
*/
@RestController
@RequestMapping("/api/demo/classroom")
@Api(value = "班级信息管理接口")
public class DemoClassroomController {

    @Autowired
    private IDemoClassroomService demoClassroomService;


    @ApiOperation(value = "分页获取班级信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType = "PageQueryDto")})
    public APIResponse<DemoClassroomDto> page(PageQueryDto<DemoClassroomEntity> pageDto) {
        PageDto<DemoClassroomDto> pd = demoClassroomService.page(pageDto);
        return new APIResponse<DemoClassroomDto>(pd);
    }

    /**
    * 描述：根据Id查询
    * @param id  班级信息id
    */
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public APIResponse<DemoClassroomDto> get(@PathVariable("id") String id)throws Exception {
         return new APIResponse<DemoClassroomDto>(demoClassroomService.getById(id));
    }

    /**
    * 描述:创建班级信息
    * @param formData  班级信息DTO
    */
    @ApiOperation(value = "保存班级信息信息", httpMethod = "POST")
    @ApiImplicitParam(name = "formData", value = "保存信息", required = true, dataType = "DemoClassroomDto")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public APIResponse<DemoClassroomDto> save(@RequestBody DemoClassroomDto formData) throws Exception {
            if (null != formData) {
                return new APIResponse<DemoClassroomDto>(demoClassroomService.save(formData));
            }
            return new APIResponse<DemoClassroomDto>(-1, "数据传输为空");
    }


    /**
    * 描述：删除单个班级信息
    * @param id 班级信息id
    */
    @ApiOperation(value = "删除单个班级信息信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public APIResponse<String> remove(@PathVariable String id) {
        if(StringUtils.isNotEmpty(id)) {
           return  demoClassroomService.remove(id);
        }
        return new APIResponse<String>(-1, "数据传输为空");
    }

    /**
    * 描述：删除多个班级信息
    * @param ids 班级信息ids
    */
    @ApiOperation(value = "删除多个班级信息信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    @RequestMapping(value = "/multiDel", method = RequestMethod.DELETE)
    public APIResponse<String> multiDel(String ids) {
        if(StringUtils.isNotEmpty(ids)) {
           return  demoClassroomService.removeMulti(ids);
        }
        return new APIResponse<String>(-1, "数据传输为空");
     }

    /**
    * 描述：更新班级信息
    * @param id 班级信息id
    */
    @ApiOperation(value = "更新班级信息数据", httpMethod = "PUT")
    @ApiImplicitParams({
    @ApiImplicitParam(required = true, name = "id", paramType = "path", dataType = "String", value = "id"),
    @ApiImplicitParam(name = "formData", value = "更新信息", required = true, dataType = "DemoClassroomDto")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public APIResponse<DemoClassroomDto> update(@PathVariable("id") String id,@RequestBody DemoClassroomDto formData) throws Exception {
        if (null != formData) {
            return new APIResponse<DemoClassroomDto>(demoClassroomService.save(formData));
        }
        return new APIResponse<DemoClassroomDto>(-1, "数据传输为空");
    }

}