package com.xk.warehouse.service;

import com.xk.warehouse.model.dto.PositionDTO;

import java.util.List;

public interface IPositionServiceImp {
    public List<PositionDTO> page(String id);
}
