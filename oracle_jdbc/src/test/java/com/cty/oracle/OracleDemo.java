package com.cty.oracle;

import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.*;

public class OracleDemo {

    @Test
    public void javaClaaOracle() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到Connection连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.23.92:1521:xe", "cty", "123");
        //获取预编译对象
        PreparedStatement statement = connection.prepareStatement("select * from emp where empno = ?");
        //给参数赋值
        statement.setObject(1,7788);
        //执行数据库查询操作
        ResultSet resultSet = statement.executeQuery();
        //遍历输出结果
        while(resultSet.next()){
            System.out.println(resultSet.getString("ename"));
//            System.out.println(resultSet.getString(0));
        }
        //释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }


    /**
     * java调用存储过程
     * {?=call <procedure-name>[(<arg1>,<arg2>...)]}    调用存储函数使用
     * {call <procedure-name>[(<arg1>,<arg2>...)]}      调用存储过程使用
     * @throws Exception
     */
    @Test
    public void javaCallProcedure() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到Connection连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.23.92:1521:xe", "cty", "123");
        //获取预编译对象
        CallableStatement statement = connection.prepareCall("{call p_yearsal(?,?)}");
        //给参数赋值
        statement.setObject(1,7788);
        statement.registerOutParameter(2, OracleTypes.NUMBER);
        //执行数据库查询操作
        statement.execute();
        //遍历输出结果[第二个参数]
        System.out.println(statement.getObject(2));
        //释放资源
        statement.close();
        connection.close();
    }


    /**
     * java调用存储函数
     * {?=call <procedure-name>[(<arg1>,<arg2>...)]}    调用存储函数使用
     * {call <procedure-name>[(<arg1>,<arg2>...)]}      调用存储过程使用
     * @throws Exception
     */
    @Test
    public void javaCallFunction() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到Connection连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.23.92:1521:xe", "cty", "123");
        //获取预编译对象
        CallableStatement statement = connection.prepareCall("{?= call f_yearsal(?)}");
        //给参数赋值
        statement.setObject(2,7788);
        statement.registerOutParameter(1, OracleTypes.NUMBER);
        //执行数据库查询操作
        statement.execute();
        //遍历输出结果[第二个参数]
        System.out.println(statement.getObject(1));
        //释放资源
        statement.close();
        connection.close();
    }
}
