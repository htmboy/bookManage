package com.java1234.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class MainFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		// 设置窗口最大化
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1450, 1300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u57FA\u672C\u6570\u636E\u7EF4\u62A4");
		menuBar.add(mnNewMenu);
		
		JMenu menu = new JMenu("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		mnNewMenu.add(menu);
		
		JMenuItem jmiBookTypeAdd = new JMenuItem("\u6DFB\u52A0\u56FE\u4E66\u7C7B\u522B");
		
		menu.add(jmiBookTypeAdd);
		
		
		
		JMenu menu_1 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		mnNewMenu.add(menu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u6DFB\u52A0\u56FE\u4E66");
		menu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u56FE\u4E66\u7EF4\u62A4");
		menu_1.add(mntmNewMenuItem_4);
		
		JMenuItem jmtExit = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		jmtExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否退出系统?");
				if(result == 0) {
					dispose();
				}
			}
		});
		mnNewMenu.add(jmtExit);
		
		JMenu mnNewMenu_1 = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmjava = new JMenuItem("\u5173\u4E8Ejava1234");
		mnNewMenu_1.add(mntmjava);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane table = new JDesktopPane();
		table.setBounds(0, 0, 1300, 1200);
//		table.setBounds(0, 0, MAXIMIZED_VERT, MAXIMIZED_HORIZ);
		contentPane.add(table);
		table.setLayout(null);
		
		jmiBookTypeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			BookTypeInterFrm bookTypeInterFrm = new BookTypeInterFrm();
			bookTypeInterFrm.setVisible(true);
			table.add(bookTypeInterFrm);
			}
			
			
		});
		
		JMenuItem jmiBookTypeManage = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u7EF4\u62A4");
		jmiBookTypeManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				BookTypeManageInterFrm bookTypeManageInterFrm = new BookTypeManageInterFrm();
				bookTypeManageInterFrm.setVisible(true);
				table.add(bookTypeManageInterFrm);
			}
		});
		menu.add(jmiBookTypeManage);
		
	}
}
