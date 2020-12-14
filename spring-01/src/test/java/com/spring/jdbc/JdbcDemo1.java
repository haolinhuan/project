package com.spring.jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcDemo1 {

    @Test
    public void testFindAll() throws SQLException, ClassNotFoundException {
        //5.执行查询所有方法
        //1.注册驱动
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection con  = DriverManager.getConnection("jdbc:mysql://192.168.2.104:3306/mysql","root","123456");
        //3.获取操作数据库的预处理对象
        PreparedStatement pre = con.prepareStatement("select * from student");
        //4.执行sql,得到结果集
        ResultSet rs = pre.executeQuery();
        //5.遍历结果集
        while(rs.next()){
            System.out.println(rs.getString("stu_id"));
        }
        //6.释放资源
        rs.close();
        pre.close();
        con.close();
    }
}
