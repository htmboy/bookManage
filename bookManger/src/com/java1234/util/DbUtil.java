package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author fantasy
 * 数据库建立连接的工具类
 */
public class DbUtil {

	private String dbUrl = "jdbc:mysql://139.199.77.144:3306/db_book";
	private String dbUserName = "htmboy";
	private String dbPassword = "htmboy8@MSN.com";
	private String jdbcName = "com.mysql.cj.jdbc.Driver";
	
	
	public Connection getCon() throws Exception {
		
		// 自动引入类 需要 加入jar包
		Class.forName(jdbcName);
		
		// 驱动管理 返回数据库连接对象
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		// 
		return con;
	}
	
	public void closeCon(Connection con) throws Exception {
		if(con != null) {
			con.close();			
		}
	}
	
	// 测试用入口
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
