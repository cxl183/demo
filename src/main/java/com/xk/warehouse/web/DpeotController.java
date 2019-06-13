package com.xk.warehouse.web;

import com.xk.warehouse.model.dto.AreaDTO;
import com.xk.warehouse.model.dto.DepotDTO;
import com.xk.warehouse.model.dto.PositionDTO;
import com.xk.warehouse.service.IAreaServiceImp;
import com.xk.warehouse.service.IDepotServiceImp;
import com.xk.warehouse.service.IPositionServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse/depot")
@Api(value = "产品信息管理接口")
public class DpeotController {
    @Autowired
    private IDepotServiceImp idepotserviceimp;
    @Autowired
    private IAreaServiceImp iareaserviceimp;
    @Autowired
    private IPositionServiceImp ipositionserviceimp;
    @ApiOperation(value = "获取部门信息", httpMethod = "GET")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public List<DepotDTO> page(){
        List<DepotDTO> pd = idepotserviceimp.all();
        return pd;
    }
    @ApiOperation(value = "获取区域信息", httpMethod = "GET")
    @RequestMapping(value = "/{id}", method= {RequestMethod.GET})
    public List<AreaDTO> area(@PathVariable("id") String id){
        List<AreaDTO> pd = iareaserviceimp.page(id);
        return pd;
    }
    @ApiOperation(value = "获取库位信息", httpMethod = "GET")
    @RequestMapping(value = "/areid/{id}", method= {RequestMethod.GET})
    public List<PositionDTO> position(@PathVariable("id") String id){
        List<PositionDTO> pd =ipositionserviceimp.page(id);
        return pd;
    }
}
