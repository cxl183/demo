package com.xk.warehouse.service.impl;

import com.xk.framework.common.BeanMapperUtils;
import com.xk.warehouse.dao.DepotRepository;
import com.xk.warehouse.model.dto.DepotDTO;
import com.xk.warehouse.model.entity.DemoDepot;
import com.xk.warehouse.service.IDepotServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepotServiceImp implements IDepotServiceImp {
    @Autowired
    private DepotRepository depotrepository;
    public List<DepotDTO> all(){
        List<DepotDTO> list = BeanMapperUtils.mapList(depotrepository.findAll(), DemoDepot.class,DepotDTO.class);
        return list;
        };
}
