package top.lilixin.registerjob.service;

import java.util.List;
import java.util.Map;

import top.lilixin.registerjob.vo.RegisterVo;

public interface RegisterJobService {

	public void insertRegisterData(RegisterVo registerVo);
	
	public List<RegisterVo> getRegisterDataByParam();
}
