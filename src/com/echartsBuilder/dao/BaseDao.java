package com.echartsBuilder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 开启关闭数据库
 * @author Administrator
 *
 */
public class BaseDao {
	private final String URL ="jdbc:mysql://10.25.18.130:3306/osv";
	private final String USER = "osv";
	private final String PWD = "osv";

	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected int rsi = 0;

	static{
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 打开数据库
	 */
	protected void openDB(){
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 关闭数据库
	 */
	protected void closeDB(){
		try {
			if (rsi != 0){
				rsi = 0;
			}
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查找信息
	 */
	protected void querySql(String sql,Object...objects){
		try {
			pstmt = conn.prepareStatement(sql);
			if (objects != null){
				for(int i = 0;i < objects.length;i++){
					pstmt.setObject(i+1, objects[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 修改数据库中的信息
	 */
	protected void updateSql(String sql,Object...objects){
		try {
			pstmt = conn.prepareStatement(sql);
			if (objects != null){
				for(int i = 0;i < objects.length;i++){
					pstmt.setObject(i+1, objects[i]);
				}
			}
			rsi = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
