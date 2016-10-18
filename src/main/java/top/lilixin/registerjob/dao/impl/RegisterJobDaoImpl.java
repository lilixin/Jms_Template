package top.lilixin.registerjob.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import top.lilixin.registerjob.dao.RegisterJobDao;
import top.lilixin.registerjob.vo.RegisterVo;

public class RegisterJobDaoImpl extends SqlSessionDaoSupport implements RegisterJobDao {
	private static final String SQL_NAMESPACE_PREFIX = RegisterJobDaoImpl.class.getCanonicalName() + ".";

	@Override
	public void insertRegisterData(RegisterVo registerVo) {
		super.getSqlSession().insert(SQL_NAMESPACE_PREFIX+"insert",registerVo);
	}

	@Override
	public List<RegisterVo> getRegisterDataByParam(Map<String, Object> params) {
		return super.getSqlSession().selectList(SQL_NAMESPACE_PREFIX+"query", params);
	}

}
