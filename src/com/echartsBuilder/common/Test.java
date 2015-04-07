package com.echartsBuilder.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.echartsBuilder.entity.TSaleOrder;

public class Test {
	public static void main(String[] args) {
		try {
			org.apache.ibatis.logging.LogFactory.useStdOutLogging();
			String resours = "Configuration.xml";
			InputStream ins = Resources.getResourceAsStream(resours);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(ins);
			SqlSession sqlSession = sessionFactory.openSession();
			Map map = new HashMap();
			map.put("sql", "sign_date as 'signDate',company_code as 'companyCode'");
			map.put("where", "where id = 17088");
			//map.put("id", 17088);
			/*map.put("orderWt", "500");*/
			//map.put("prodDept", "泰国代表处");
			List<TSaleOrder> list = sqlSession.selectList("TSaleOrder.test",map);
			TSaleOrder saleOrder = new TSaleOrder();
			if(list.size() > 0){
				saleOrder = list.get(0);
				System.out.println(saleOrder.getCompanyCode());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
