package com.java1234.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.java1234.dao.BookTypeDao;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class BookTypeInterFrm extends JInternalFrame {
	DbUtil dbUtil = new DbUtil();
	BookTypeDao bookTypeDao = new BookTypeDao();
	
	private JTextArea bookTypeDescTxt;
	
	private JTextField bookTypeNameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeInterFrm frame = new BookTypeInterFrm();
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
	public BookTypeInterFrm() {
		setTitle("\u56FE\u4E66\u6DFB\u52A0\u7C7B\u522B");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0");
		lblNewLabel.setBounds(62, 56, 80, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0:");
		lblNewLabel_1.setBounds(62, 102, 80, 15);
		getContentPane().add(lblNewLabel_1);
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setBounds(168, 53, 230, 21);
		getContentPane().add(bookTypeNameTxt);
		bookTypeNameTxt.setColumns(10);
		
		
		
		
		
		bookTypeDescTxt = new JTextArea();
		bookTypeDescTxt.setColumns(8);
		bookTypeDescTxt.setRows(8);
		bookTypeDescTxt.setBounds(168, 98, 230, 100);
		getContentPane().add(bookTypeDescTxt);
		
		JButton jb_add = new JButton("\u6DFB\u52A0");
		jb_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String bookTypeName = bookTypeNameTxt.getText();
				String bookTypeDesc = bookTypeDescTxt.getText();
				if("".equals(bookTypeName) || bookTypeName == null) {
					JOptionPane.showMessageDialog(null, "图书类目不能为空");
					return;
				}
				if("".equals(bookTypeDesc) || bookTypeDesc == null) {
					JOptionPane.showMessageDialog(null, "图书描述不能为空");
					return;
				}
				BookType bookType = new BookType(bookTypeName, bookTypeDesc);
				Connection con = null;
				try {
					con = dbUtil.getCon();
					int n = bookTypeDao.bookTypeAdd(con, bookType);
					if(n == 1) {
						JOptionPane.showMessageDialog(null, "图书类别添加成功!");
						resetValues();
					}else {
						JOptionPane.showMessageDialog(null, "图书类别添加失败");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "数据传输有误!");
				}
			}
		});
		
		jb_add.setBounds(62, 212, 93, 23);
		getContentPane().add(jb_add);
		
		JButton jb_reset = new JButton("\u91CD\u7F6E");
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		jb_reset.setBounds(232, 212, 93, 23);
		getContentPane().add(jb_reset);
	}
	
	private void resetValues() {
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
}
