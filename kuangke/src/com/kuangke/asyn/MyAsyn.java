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
			System.out.println("第"+i+"次执行扫描");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}

	}
	
	
	public void handle(){
		// 数据库驱动类名的字符串
        String driver = "com.mysql.jdbc.Driver";
        // 数据库连接串
        String url = "jdbc:mysql://127.0.0.1:3306/student";
        // 用户名
        String username = "root";
        // 密码
        String password = "root";
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        String insql=null;
        String upStudentSql="update student set flag='1' where id=?";
        PreparedStatement pstmt=null;
        PreparedStatement psStudent=null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(driver);
            // 2、获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            // 3、获取数据库操作对象
            stmt = conn.createStatement();
            // 4、定义操作的SQL语句
            String sql = "select * from student where flag is null or flag =''";
            // 5、执行数据库操作
            rs = stmt.executeQuery(sql);
            // 6、获取并操作结果集
            while (rs.next()) {
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("name"));
            	  //等级控制表
            	  insql="insert student_controller (id,name,sex,age,memo,mdtime) values(?,?,?,?,?,?)";
            	  pstmt=(PreparedStatement) conn.prepareStatement(insql);
            	  pstmt.setInt(1,rs.getInt("id"));
            	  pstmt.setString(2,rs.getString("name"));
            	  System.out.println(rs.getString("name"));
            	  pstmt.setString(3,rs.getString("sex"));
            	  pstmt.setString(4,rs.getString("age"));
            	  pstmt.setString(5,rs.getString("memo"));
            	  //获取系统时间
            	  Date date=new Date();
            	  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            	  String date1=format.format(date);
            	  pstmt.setString(6,date1);
            	  pstmt.executeUpdate();
            	  pstmt.close();
            	  
            	  //更新学生等级表flag-1已等级
            	  psStudent=(PreparedStatement) conn.prepareStatement(upStudentSql);
            	  psStudent.setInt(1,rs.getInt("id"));
            	  psStudent.executeUpdate();
            	  psStudent.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、关闭对象，回收数据库资源
            if (rs != null) { //关闭结果集对象
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) { // 关闭数据库操作对象
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭数据库连接对象
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
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
            }
        
    }
	}
}
