package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author fantasy
 * ���ݿ⽨�����ӵĹ�����
 */
public class DbUtil {

	private String dbUrl = "jdbc:mysql://139.199.77.144:3306/db_book";
	private String dbUserName = "htmboy";
	private String dbPassword = "htmboy8@MSN.com";
	private String jdbcName = "com.mysql.cj.jdbc.Driver";
	
	
	public Connection getCon() throws Exception {
		
		// �Զ������� ��Ҫ ����jar��
		Class.forName(jdbcName);
		
		// �������� �������ݿ����Ӷ���
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		// 
		return con;
	}
	
	public void closeCon(Connection con) throws Exception {
		if(con != null) {
			con.close();			
		}
	}
	
	// ���������
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("���ݿ����ӳɹ�");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
