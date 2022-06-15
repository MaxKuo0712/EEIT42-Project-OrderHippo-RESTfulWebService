package com.orderhippo.service.servicelmp.viewServicelmp;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderhippo.model.viewBean.VMealBomBean;
import com.orderhippo.repository.viewRepository.VMealBomRepository;
import com.orderhippo.service.service.viewService.VMealBomService;

@Service
@Transactional(rollbackFor = SQLException.class)
public class VMealBomServicelmp implements VMealBomService {

	@Autowired
	private VMealBomRepository vMealBomRepository;
	
	@Override
	public List<VMealBomBean> getAll() {
		List<VMealBomBean> result = vMealBomRepository.findAll();
		
		if (!result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}

}
