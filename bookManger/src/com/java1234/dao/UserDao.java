package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.User;

/**
 * @author fantasy
 * 数据库查询类
 * 需要数据库连接对象,模型对象
 */
public class UserDao {

	public User login(Connection con, User user) throws Exception {
		
		// 预设空模型
		User resultUser = null;
		
		// 预设查询语言
		String sql = "select * from t_user where userName=? and password=?";
		
		// 预处理查询语句
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// 设置第一个参数
		pstmt.setString(1, user.getUserName());
		
		// 设置第二个参数
		pstmt.setString(2, user.getPassword());
		
		// 执行sql语句
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultUser = new User();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	
}
