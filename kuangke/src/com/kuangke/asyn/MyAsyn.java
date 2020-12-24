package com.kuangke.asyn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import com.mysql.jdbc.PreparedStatement;

public class MyAsyn extends Thread {

	@Override
	public void run() {
		int i=0;
		while(true){
			handle();
			i++;
			System.out.println("��"+i+"��ִ��ɨ��");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}

	}
	
	
	public void handle(){
		// ���ݿ������������ַ���
        String driver = "com.mysql.jdbc.Driver";
        // ���ݿ����Ӵ�
        String url = "jdbc:mysql://127.0.0.1:3306/student";
        // �û���
        String username = "root";
        // ����
        String password = "root";
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        String insql=null;
        String upStudentSql="update student set flag='1' where id=?";
        PreparedStatement pstmt=null;
        PreparedStatement psStudent=null;
        try {
            // 1���������ݿ������� �ɹ����غ󣬻ὫDriver���ʵ��ע�ᵽDriverManager���У�
            Class.forName(driver);
            // 2����ȡ���ݿ�����
            conn = DriverManager.getConnection(url, username, password);
            // 3����ȡ���ݿ��������
            stmt = conn.createStatement();
            // 4�����������SQL���
            String sql = "select * from student where flag is null or flag =''";
            // 5��ִ�����ݿ����
            rs = stmt.executeQuery(sql);
            // 6����ȡ�����������
            while (rs.next()) {
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("name"));
            	  //�ȼ����Ʊ�
            	  insql="insert student_controller (id,name,sex,age,memo,mdtime) values(?,?,?,?,?,?)";
            	  pstmt=(PreparedStatement) conn.prepareStatement(insql);
            	  pstmt.setInt(1,rs.getInt("id"));
            	  pstmt.setString(2,rs.getString("name"));
            	  System.out.println(rs.getString("name"));
            	  pstmt.setString(3,rs.getString("sex"));
            	  pstmt.setString(4,rs.getString("age"));
            	  pstmt.setString(5,rs.getString("memo"));
            	  //��ȡϵͳʱ��
            	  Date date=new Date();
            	  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            	  String date1=format.format(date);
            	  pstmt.setString(6,date1);
            	  pstmt.executeUpdate();
            	  pstmt.close();
            	  
            	  //����ѧ���ȼ���flag-1�ѵȼ�
            	  psStudent=(PreparedStatement) conn.prepareStatement(upStudentSql);
            	  psStudent.setInt(1,rs.getInt("id"));
            	  psStudent.executeUpdate();
            	  psStudent.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7���رն��󣬻������ݿ���Դ
            if (rs != null) { //�رս��������
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) { // �ر����ݿ��������
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // �ر����ݿ����Ӷ���
                try {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pstmt!=null){
            	try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
            }
        
    }
	}
}
