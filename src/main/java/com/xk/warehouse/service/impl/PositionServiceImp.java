package com.xk.warehouse.service.impl;

import com.xk.framework.common.BeanMapperUtils;
import com.xk.framework.common.CommonErrorCode;
import com.xk.framework.common.Constants;
import com.xk.framework.common.ServiceException;
import com.xk.framework.jpa.specification.SimpleSpecificationBuilder;
import com.xk.warehouse.dao.PositionsRepository;
import com.xk.warehouse.model.dto.PositionDTO;
import com.xk.warehouse.model.entity.DemoPosition;
import com.xk.warehouse.service.IPositionServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PositionServiceImp implements IPositionServiceImp {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PositionsRepository positionsrepository;
    @Transactional(readOnly = true)
    @Override
    public List<PositionDTO> page(String id) {
        try {
            SimpleSpecificationBuilder<DemoPosition> ssb=new SimpleSpecificationBuilder<DemoPosition>();
            ssb.add("area.id",Constants.OPER_EQ,id);
            List<DemoPosition> list=positionsrepository.findAll(ssb.generateSpecification());
            return BeanMapperUtils.mapList(positionsrepository.findAll(ssb.generateSpecification()),DemoPosition.class, PositionDTO.class);
        } catch (Exception e) {
            logger.error(CommonErrorCode.SELECT_ERROR.getMessage());
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }
}
