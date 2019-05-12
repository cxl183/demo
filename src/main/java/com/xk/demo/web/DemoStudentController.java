package com.xk.demo.web;


import com.xk.framework.common.PageDto;
import com.xk.framework.common.PageQueryDto;


import com.xk.demo.service.IDemoStudentService;
import com.xk.demo.model.entity.DemoStudentEntity;
import com.xk.demo.model.dto.DemoStudentDto;

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
* 描述：学生信息控制层
* @author LV
* @since 2019-05-04
*/
@RestController
@RequestMapping("/api/demo/student")
@Api(value = "学生信息管理接口")
public class DemoStudentController {

    @Autowired
    private IDemoStudentService demoStudentService;


    @ApiOperation(value = "分页获取学生信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageDto", paramType = "query", value = "查询条件", dataType = "PageQueryDto")})
    public APIResponse<DemoStudentDto> page(PageQueryDto<DemoStudentEntity> pageDto) {
        PageDto<DemoStudentDto> pd = demoStudentService.page(pageDto);
        return new APIResponse<DemoStudentDto>(pd);
    }

    /**
    * 描述：根据Id查询
    * @param id  学生信息id
    */
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public APIResponse<DemoStudentDto> get(@PathVariable("id") String id)throws Exception {
         return new APIResponse<DemoStudentDto>(demoStudentService.getById(id));
    }

    /**
    * 描述:创建学生信息
    * @param formData  学生信息DTO
    */
    @ApiOperation(value = "保存学生信息信息", httpMethod = "POST")
    @ApiImplicitParam(name = "formData", value = "保存信息", required = true, dataType = "DemoStudentDto")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public APIResponse<DemoStudentDto> save(@RequestBody DemoStudentDto formData) throws Exception {
            if (null != formData) {
                return new APIResponse<DemoStudentDto>(demoStudentService.save(formData));
            }
            return new APIResponse<DemoStudentDto>(-1, "数据传输为空");
    }


    /**
    * 描述：删除单个学生信息
    * @param id 学生信息id
    */
    @ApiOperation(value = "删除单个学生信息信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public APIResponse<DemoStudentDto> remove(@PathVariable String id) {
            demoStudentService.remove(id);
        return new APIResponse<DemoStudentDto>(0, "数据删除成功");
    }

    /**
    * 描述：删除多个学生信息
    * @param ids 学生信息ids
    */
    @ApiOperation(value = "删除多个学生信息信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    @RequestMapping(value = "/multiDel", method = RequestMethod.DELETE)
    public APIResponse<DemoStudentDto> multiDel(String ids) {
            demoStudentService.removeMulti(ids);
        return new APIResponse<DemoStudentDto>(0, "数据删除成功");
     }

    /**
    * 描述：更新学生信息
    * @param id 学生信息id
    */
    @ApiOperation(value = "更新学生信息数据", httpMethod = "PUT")
    @ApiImplicitParams({
    @ApiImplicitParam(required = true, name = "id", paramType = "path", dataType = "String", value = "id"),
    @ApiImplicitParam(name = "formData", value = "更新信息", required = true, dataType = "DemoStudentDto")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public APIResponse<DemoStudentDto> update(@PathVariable("id") String id,@RequestBody DemoStudentDto formData) throws Exception {
        if (null != formData) {
            return new APIResponse<DemoStudentDto>(demoStudentService.save(formData));
        }
        return new APIResponse<DemoStudentDto>(-1, "数据传输为空");
    }

}