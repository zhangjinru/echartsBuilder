package com.echartsBuilder.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseDaoMB {
	private String resours = "Configuration.xml";
	private InputStream ins ;
	private SqlSessionFactory sessionFactory ;
	
	public BaseDaoMB(){
        try {
			ins = Resources.getResourceAsStream(resours);
			sessionFactory = new SqlSessionFactoryBuilder().build(ins);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SqlSessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
