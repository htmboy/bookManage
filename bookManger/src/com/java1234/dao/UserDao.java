package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.User;

/**
 * @author fantasy
 * ���ݿ��ѯ��
 * ��Ҫ���ݿ����Ӷ���,ģ�Ͷ���
 */
public class UserDao {

	public User login(Connection con, User user) throws Exception {
		
		// Ԥ���ģ��
		User resultUser = null;
		
		// Ԥ���ѯ����
		String sql = "select * from t_user where userName=? and password=?";
		
		// Ԥ�����ѯ���
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// ���õ�һ������
		pstmt.setString(1, user.getUserName());
		
		// ���õڶ�������
		pstmt.setString(2, user.getPassword());
		
		// ִ��sql���
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultUser = new User();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	
}
