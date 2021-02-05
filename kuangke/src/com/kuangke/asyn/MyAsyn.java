package com.kuangke.asyn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.mysql.jdbc.PreparedStatement;

public class MyAsyn extends Thread {
	//private Logger logger=Logger.getLogger(MyAsyn.class);
	
	@Override
	public void run() {
		int i=0;
		while(true){
			handle();
			i++;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = df.format(new Date());
			//logger.debug(format+"   ��"+i+"��ִ��ɨ��");
			System.out.println(format+"   ��"+i+"��ִ��ɨ��");
			try {
				Thread.sleep(60000);
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
        String upStudentSql="update student set flag ='1' where flag is null or flag =''";
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
            String sql = "select count(1) count from student where flag is null or flag =''";
            // 5��ִ�����ݿ����
            rs = stmt.executeQuery(sql);
            // 6����ȡ�����������
            while (rs.next()) {
            	  int count=rs.getInt("count");
            	  if(count>0){
            		  //�ȼ����Ʊ�
                	  insql="insert into student_controller(id,name,sex,age,memo,mdtime)(select id,name,sex,age,memo,date_format(now(),'%Y%m%d%H%i%s') "
                			  +"from student where flag is null or flag='')";
                	  pstmt=(PreparedStatement) conn.prepareStatement(insql);
                	  pstmt.execute();
                	  pstmt.close();
                	  
                	  //����ѧ���ȼ���flag-1�ѵȼ�
                	  psStudent=(PreparedStatement) conn.prepareStatement(upStudentSql);
                	  psStudent.execute();
                	  psStudent.close(); 
            	  }
            	 
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
