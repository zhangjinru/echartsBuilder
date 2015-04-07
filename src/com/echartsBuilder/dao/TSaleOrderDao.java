package com.echartsBuilder.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.echartsBuilder.entity.TSaleOrder;

public class TSaleOrderDao extends BaseDaoMB{
	private SqlSession sqlSession;
	
	public List<TSaleOrder> select_ex(String sqlName, Map map){
		List<TSaleOrder> results = new ArrayList<TSaleOrder>();
		try{
			sqlSession = getSessionFactory().openSession();
			results = sqlSession.selectList(sqlName, map);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		return results;
	}
}
