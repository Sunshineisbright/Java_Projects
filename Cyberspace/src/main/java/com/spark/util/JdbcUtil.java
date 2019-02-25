package com.spark.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
	private static String path;
public static void main(String [] args){
	try {
		System.out.println("-------> " + JdbcUtil.getDataSource().getConnection());
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println("-------- " + path);
}
	public static DataSource ds = null;
	static {
		try {
			//1.实例化Properties对象用于加载配置文件
			Properties p = new Properties();
			////获取字节码目录
			path = JdbcUtil.class.getClassLoader().getResource("db.properties").getPath();
			System.out.println("************* " + path);
			FileInputStream in = new FileInputStream(path);
			p.load(in);
			//ds = BasicDataSourceFactory.createDataSource(p);
			ds = DruidDataSourceFactory.createDataSource(p);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	///获取数据源
	public static DataSource  getDataSource() {
		return ds;
	}
	// 2.连接数据
	public static Connection getConn() {
		try {
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 关闭资源
	 */
	public static void close(Connection conn,Statement st,ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
