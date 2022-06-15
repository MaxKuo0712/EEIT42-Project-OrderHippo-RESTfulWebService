package com.orderhippo.controller.viewController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderhippo.model.StoreInfoBean;
import com.orderhippo.model.viewBean.VMealBomBean;
import com.orderhippo.service.service.StoreInfoService;
import com.orderhippo.service.service.viewService.VMealBomService;
import com.orderhippo.utils.ProjectUtils;

import io.swagger.annotations.Api;

@Api(tags = "菜單管理主頁API")
@RestController
@RequestMapping("/api")
@CrossOrigin
//@CrossOrigin(origins = "http://127.0.0.1:8080")
public class VMealBomController {
	@Autowired
	private VMealBomService vMealBomService;
	
	@Autowired
	private StoreInfoService storeInfoService;
	
	@GetMapping("/{requestID}/vmealbom")
	public Object getAll(
			@PathVariable String requestID,
			@RequestParam(name = "token", required = true) String realHashToken) {
		List<StoreInfoBean> storeinfo = storeInfoService.getStoreInfoByStoreid(requestID);
		
		String dbToken = ProjectUtils.getDBToken(null, storeinfo, requestID);
		boolean verifyResult = ProjectUtils.verifyToken(realHashToken, dbToken);
		
		if (verifyResult) {
			return vMealBomService.getAll();
		} else {
			return new ResponseEntity<String>("權限不足", HttpStatus.BAD_REQUEST);
		}
	}
}
