package org.jeecgframework.web.demo.service.impl.test;

import java.util.List;
import java.util.Map;

import org.jeecgframework.web.demo.dao.test.JeecgProcedureDao;
import org.jeecgframework.web.demo.entity.test.JeecgDemo;
import org.jeecgframework.web.demo.service.test.JeecgProcedureServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jeecgProcedureServiceImpl")
@Transactional
public class JeecgProcedureServiceImpl extends JdbcDaoSupport implements JeecgProcedureServiceI{
	
	//update-begin--Author:luobaoli  Date:20150711 for：新增调用存储过程的service方法
	@Autowired
	private JeecgProcedureDao jeecgProcedureDao;
	public List queryDataByProcedure(String tableName, String fields, String whereSql) {
		//return jeecgProcedureDao.queryDataByProcedure(tableName, fields, whereSql);
		String sql = "call formDataList('"+tableName+"','"+fields+"','"+whereSql+"')";
		return this.getJdbcTemplate().queryForList(sql);
	}
	//update-end--Author:luobaoli  Date:20150711 for：新增调用存储过程的service方法
}