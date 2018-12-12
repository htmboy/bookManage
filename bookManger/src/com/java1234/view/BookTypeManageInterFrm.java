package com.java1234.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java1234.dao.BookTypeDao;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManageInterFrm extends JInternalFrame {
	DbUtil dbUtil = new DbUtil();
	BookTypeDao bookTypeDao = new BookTypeDao();
	
	private JTextField s_bookTypeNameTxt;
	private JTable bookTypeTable;
	private JTextField idTxt;
	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrm frame = new BookTypeManageInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void fillTable(BookType bookType) {
		DefaultTableModel dtm = (DefaultTableModel)bookTypeTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookTypeDao.bookTypeList(con, bookType);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public BookTypeManageInterFrm() {
		
		setClosable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u7EF4\u62A4");
		setBounds(100, 100, 492, 468);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0:");
		lblNewLabel.setBounds(37, 24, 93, 15);
		getContentPane().add(lblNewLabel);
		
		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setBounds(140, 21, 184, 21);
		getContentPane().add(s_bookTypeNameTxt);
		s_bookTypeNameTxt.setColumns(10);
		
		JButton jb_search = new JButton("\u67E5\u8BE2");
		jb_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s_bookTypeName = s_bookTypeNameTxt.getText();
				BookType bookType = new BookType();
				bookType.setBookTypeName(s_bookTypeName);
				fillTable(bookType);
			}
		});
		jb_search.setBounds(344, 20, 93, 23);
		getContentPane().add(jb_search);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(37, 77, 400, 118);
		getContentPane().add(scrollPane);
		
		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
				int row = bookTypeTable.getSelectedRow();
				idTxt.setText((String)bookTypeTable.getValueAt(row, 0));
				bookTypeNameTxt.setText((String)bookTypeTable.getValueAt(row, 1));
				bookTypeDescTxt.setText((String)bookTypeTable.getValueAt(row, 2));
			}
		});
		scrollPane.setViewportView(bookTypeTable);
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0"
			}
		));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 205, 405, 212);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u7F16\u53F7:");
		lblNewLabel_1.setBounds(33, 34, 54, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u56FE\u7247\u7C7B\u522B\u540D\u79F0:");
		lblNewLabel_2.setBounds(160, 34, 80, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u63CF\u8FF0:");
		lblNewLabel_3.setBounds(33, 79, 54, 15);
		panel.add(lblNewLabel_3);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBounds(79, 31, 66, 21);
		panel.add(idTxt);
		idTxt.setColumns(10);
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setBounds(262, 31, 102, 21);
		panel.add(bookTypeNameTxt);
		bookTypeNameTxt.setColumns(10);
		
		bookTypeDescTxt = new JTextArea();
		bookTypeDescTxt.setBounds(79, 75, 285, 70);
		panel.add(bookTypeDescTxt);
		
		JButton jb_modify = new JButton("\u4FEE\u6539");
		jb_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String id = idTxt.getText();
				String bookTypeName = bookTypeNameTxt.getText();
				String bookTypeDesc = bookTypeDescTxt.getText();
				if(StringUtil.isEmpty(id)) {
					JOptionPane.showMessageDialog(null, "请选择要修改的记录");
					return;
				}
				BookType bookType = new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
				Connection con = null;
				try {
					con = dbUtil.getCon();
					int modifyNum = bookTypeDao.bookTypeModify(con, bookType);
					if(modifyNum == 1) {
						JOptionPane.showMessageDialog(null, "修改成功");
						resetValue();
						fillTable(new BookType());
					}
					else
						JOptionPane.showMessageDialog(null, "修改失败");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据传输有误");
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
		jb_modify.setBounds(42, 168, 93, 23);
		panel.add(jb_modify);
		
		JButton jb_delete = new JButton("\u5220\u9664");
		jb_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String id = idTxt.getText();
				if(StringUtil.isEmpty(id)) {
					JOptionPane.showMessageDialog(null, "请选择要删除的记录!");
					return;
				}
				if(JOptionPane.showConfirmDialog(null, "确定要删除这条记录吗?") == 0) {
					Connection con = null;
					try {
						con = dbUtil.getCon();
						int deleteNum = bookTypeDao.bookTypeDelete(con, id);
						if(deleteNum == 1) {
							JOptionPane.showMessageDialog(null, "删除成功");
							resetValue();
							fillTable(new BookType());
						}
						else
							JOptionPane.showMessageDialog(null, "删除失败");
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "数据传输有误");
					}finally {
						try {
							dbUtil.closeCon(con);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		jb_delete.setBounds(224, 168, 93, 23);
		panel.add(jb_delete);
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(114);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(438);
		this.fillTable(new BookType());
		
	}
	
	private void resetValue() {
		this.idTxt.setText("");
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
}
