package com.xk.warehouse.service.impl;


import com.xk.common.core.dict.service.CommonDictService;
import com.xk.framework.common.*;
import com.xk.warehouse.dao.DemoCategoryRepository;
import com.xk.warehouse.dao.DemoProductRepository;
import com.xk.warehouse.model.dto.ProductDto;
import com.xk.warehouse.model.entity.DemoCategory;
import com.xk.warehouse.model.entity.DemoProduct;
import com.xk.warehouse.service.IProductServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements IProductServiceImp {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/*@Autowired
	private  StaffDAO staffdao;*/
	@Autowired
	private DemoProductRepository productdao;
	@Autowired
	private DemoCategoryRepository categorydao;
	@Autowired
	private CategoryServiceImp categoryservicesmp;
	@Autowired
	private CommonDictService cacheService;
	@Override
	public List<ProductDto> pro(ProductDto puc) {
		return null;
	}

	@Transactional(rollbackFor = java.lang.Exception.class)
	@Override
	public void detep(String ids) throws Exception {
		try {
			productdao.removeMuiltByIds(ids);
		} catch (Exception e) {
			logger.error("删除数据失败");
			e.printStackTrace();
			throw new ServiceException("删除数据失败", CommonErrorCode.DELETE_ERROR);
		}
	}
	@Transactional(rollbackFor = java.lang.Exception.class)
	@Override
	public ProductDto addp(ProductDto code) throws Exception {
			try {
				DemoProduct demoproductEntity = new DemoProduct();
				if (code != null && !StringUtils.isEmpty(code.getId())) {
					demoproductEntity = productdao.findOne(code.getId());
				}
				BeanMapperUtils.map(code, demoproductEntity);
                DemoCategory demoCategory=categoryservicesmp.findbyId(code.getCategoryname());
				demoproductEntity.setDemoCategory(demoCategory);
				// 保存
				return BeanMapperUtils.map(productdao.saveAndFlush(demoproductEntity), ProductDto.class);
			} catch (Exception e) {
				logger.error("保存数据失败");
				e.printStackTrace();
				throw new ServiceException("保存数据失败", CommonErrorCode.SAVE_ERROR);
			}

	}
	@Transactional(readOnly = true)
	@Override
	public ProductDto getById(String id) {
		try {
			DemoProduct demoproduct = productdao.findOne(id);
			if (demoproduct == null) {
				return null;
			}
			ProductDto dto=BeanMapperUtils.map(demoproduct,ProductDto.class);
			dto.setCategoryname(demoproduct.getDemoCategory().getId());
			return dto;
		} catch (Exception e) {
			logger.error("查询数据失败");
			e.printStackTrace();
			throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
		}
	}
	@Transactional(readOnly = true)
	@Override
	public PageDto<ProductDto> page(PageQueryDto<DemoProduct> pageDto) {
		try {
			Page<DemoProduct> pageData =productdao.queryPage(pageDto);
			if (pageData == null || pageData.getContent() == null || pageData.getContent().size() <= 0) {
				return null;
			}
			List<ProductDto> lists = BeanMapperUtils.mapList(pageData.getContent(), DemoProduct.class,
					ProductDto.class);
               //把种类表的信息关联到产品信息中
					for (int i=0;i<pageData.getContent().size();i++){
						lists.get(i).setCategoryname(pageData.getContent().get(i).getDemoCategory().getCategoryname());
						lists.get(i).setStaus(cacheService.getDictByCode("productStatus",""+lists.get(i).getDatastatus()).getDictname());
						lists.get(i).setUnit(cacheService.getDictByCode("productUnit",pageData.getContent().get(i).getUnit()).getDictname());
			}
			// 设置当前的记录，总记录数，总页数，当前页
			return new PageDto<ProductDto>(lists, pageData.getTotalElements(), pageData.getTotalPages(),pageData.getNumber());
		} catch (Exception e) {
			logger.error("查询数据失败");
			e.printStackTrace();
			throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
		}
	}
}
