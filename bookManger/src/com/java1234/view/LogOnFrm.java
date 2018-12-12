package com.java1234.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * @author fantasy
 * 控制器和图像类
 * 主入口类
 */
public class LogOnFrm extends JFrame {
	
	
	
	
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
	
	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// 改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
					
					// 居中显示
					frame.setLocationRelativeTo(null);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogOnFrm() {
		
		// 禁止最大化
		setResizable(false);
		
		setTitle("\u7BA1\u7406\u5458\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 424, 252);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		label.setIcon(new ImageIcon("C:\\www\\finance\\images\\add.png"));
		label.setBounds(163, 26, 128, 20);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setIcon(new ImageIcon("C:\\www\\finance\\img\\switch.png"));
		lblNewLabel.setBounds(121, 102, 88, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801:");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\www\\finance\\img\\throbber.gif"));
		lblNewLabel_1.setBounds(121, 145, 88, 15);
		panel.add(lblNewLabel_1);
		
		userNameTxt = new JTextField();
		userNameTxt.setBounds(244, 99, 98, 21);
		panel.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(244, 142, 98, 21);
		panel.add(passwordTxt);
		
		JButton jb_logon = new JButton("\u767B\u5F55");
		
		// 登录按钮事件
		jb_logon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 获取输入框里的内容
				String userName = userNameTxt.getText();
				String password = new String(passwordTxt.getPassword());
				
				// 判断输入框是否为空
				if("".equals(userName) || userName == null) {
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					return;
				}
				if("".equals(password) || password == null) {
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return;
				}
				
				// 把值放入模型并创建对象
				User user = new User(userName, password);
				Connection con = null;
				try {
					//
					con = dbUtil.getCon();
					
					// 把数据库连接对象和模型对象放入Dao工具查询, 返回有结果的模型
					User currentUser = userDao.login(con, user);
					
					// 判断是否成功
					if(currentUser != null) {
						dispose();
						new MainFrm().setVisible(true);
						JOptionPane.showMessageDialog(null, currentUser.getUserName() + "登录成功");
					}else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误");
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "连接错误");
				}finally {
					try {
						dbUtil.closeCon(con);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		jb_logon.setBounds(58, 190, 93, 23);
		panel.add(jb_logon);
		
		// 重置按钮事件
		JButton jf_reset = new JButton("\u91CD\u7F6E");
		jf_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 设置输入框为空
				userNameTxt.setText("");
				passwordTxt.setText("");
			}
		});
		jf_reset.setBounds(219, 190, 93, 23);
		panel.add(jf_reset);
	}
}
