package top.lilixin.registerjob.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.lilixin.registerjob.dao.RegisterJobDao;
import top.lilixin.registerjob.service.RegisterJobService;
import top.lilixin.registerjob.vo.RegisterVo;

@Service("registerJobService")
public class RegisterJobServiceImpl implements RegisterJobService{
	
	@Autowired
	private RegisterJobDao registerJobDao;

	@Override
	public void insertRegisterData(RegisterVo registerVo) {
		registerJobDao.insertRegisterData(registerVo);
	}

	@Override
	public List<RegisterVo> getRegisterDataByParam() {
		Map<String, Object> params = new HashMap<>();
		return registerJobDao.getRegisterDataByParam(params);
	}

}
