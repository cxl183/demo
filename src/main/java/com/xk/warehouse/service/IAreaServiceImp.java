package com.xk.warehouse.service;

import com.xk.warehouse.model.dto.AreaDTO;

import java.util.List;

public interface IAreaServiceImp {
    public List<AreaDTO> page(String id);
}
