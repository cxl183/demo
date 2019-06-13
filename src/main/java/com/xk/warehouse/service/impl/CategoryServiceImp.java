package com.xk.warehouse.service.impl;

import com.xk.framework.common.BeanMapperUtils;
import com.xk.framework.common.CommonErrorCode;
import com.xk.framework.common.ServiceException;
import com.xk.warehouse.dao.DemoCategoryRepository;
import com.xk.warehouse.model.dto.CategoryDTO;
import com.xk.warehouse.model.dto.ProductDto;
import com.xk.warehouse.model.entity.DemoCategory;
import com.xk.warehouse.service.ICategoryServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImp implements ICategoryServiceImp {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DemoCategoryRepository categoryRepository;
	@Transactional
	public List<CategoryDTO> ducs(CategoryDTO cat){
		List<CategoryDTO> list=new ArrayList<CategoryDTO>();

		return list;
	}

	@Override
	public List<CategoryDTO> ducs() {
		List<DemoCategory> list=categoryRepository.findAll();
		List<CategoryDTO> lists= (List<CategoryDTO>) BeanMapperUtils.mapList(list,DemoCategory.class,CategoryDTO.class);
		return lists;
	}

	@Override
	public List<String> select() {

		return null;
	}

	@Override
	public void addm(String id, String name, String remark) throws Exception {

	}

	@Override
	public DemoCategory findbyId(String id) {
		try {
			DemoCategory demoCategoryEntity = categoryRepository.findOne(id);
			if (demoCategoryEntity == null) {
				return null;
			}
			return demoCategoryEntity;
		} catch (Exception e) {
			logger.error("查询数据失败");
			e.printStackTrace();
			throw new ServiceException("查询数据失败", CommonErrorCode.SELECT_ERROR);
		}
	}
	/*@Transactional
	public List<String> select(){
		List<String> list=new ArrayList<String>();
		List<Locale.Category> list2=categorydao.findAll();
		for(int i=0;i<list2.size();i++){
			String s=list2.get(i).getCategoryname();
			list.add(s);
		}
		return list;
	}
	public void addm(String id,String name,String remark) throws Exception{
		if(categorydao.findByCategorycode(id).size()>0){
			throw new Exception("id重复");
		}
		Category category=new Category();
		category.setCategoryid(UUID.randomUUID().toString().replace("-", ""));
		category.setCategorycode(id);
		category.setCategoryname(name);
		categorydao.save(category);
	}*/
}
