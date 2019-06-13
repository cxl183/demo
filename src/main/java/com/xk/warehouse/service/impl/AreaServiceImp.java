package com.xk.warehouse.service.impl;

import com.xk.framework.common.BeanMapperUtils;
import com.xk.framework.common.CommonErrorCode;
import com.xk.framework.common.Constants;
import com.xk.framework.common.ServiceException;
import com.xk.framework.jpa.specification.SimpleSpecificationBuilder;
import com.xk.warehouse.dao.AreaRepository;
import com.xk.warehouse.model.dto.AreaDTO;
import com.xk.warehouse.model.entity.DemoArea;
import com.xk.warehouse.service.IAreaServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AreaServiceImp implements IAreaServiceImp {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AreaRepository arearepository;
    @Transactional(readOnly = true)
    @Override
    public List<AreaDTO> page(String id) {
        try {
            SimpleSpecificationBuilder<DemoArea> ssb=new SimpleSpecificationBuilder<DemoArea>();
            ssb.add("depot.id",Constants.OPER_EQ,id);
            List<DemoArea> list=arearepository.findAll(ssb.generateSpecification());
            return BeanMapperUtils.mapList(arearepository.findAll(ssb.generateSpecification()),DemoArea.class,AreaDTO.class);
        } catch (Exception e) {
            logger.error(CommonErrorCode.SELECT_ERROR.getMessage());
            e.printStackTrace();
            throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
        }
    }
}
