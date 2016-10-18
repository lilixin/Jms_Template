package top.lilixin.registerjob.dao;

import java.util.List;
import java.util.Map;

import top.lilixin.registerjob.vo.RegisterVo;

public interface RegisterJobDao {
	
	public void insertRegisterData(RegisterVo registerVo);
	
	public List<RegisterVo> getRegisterDataByParam(Map<String,Object> params);
}
