package com.kh.jdbc.day04.student.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {
	// 디자인 패턴 : 각기 다른 소프트웨어 모듈이나 기능을 가진 응용 SW를 개발할 때
	// 공통되는 설계, 문제를 해결하기 위하여 사용되는 패턴임
	// ==> 효율적인 방식을 위함
	// 패턴의 종류 : 생성패턴, 구조패턴, 행위패턴, ...
	// 1. 생성패턴 : 싱글톤 패턴, 추상팩토리, 팩토리 메소드, ...
	// 2. 구조패턴 : 컴포지트, 데코레이트, ...
	// 3. 행위패턴 : 옵저버, 스테이트, 전략, 템플릿 메서드, ...
	
	/*
	 * public class Singletone {
	 * 		private static Singletone instance;
	 * 
	 * 		private Singletone() {}
	 * 
	 * 		public static Singletone getInstance(){
	 * 			if(instance == null){
	 * 				instance = new Singletone();
	 * 			}
	 * 		}
	 * }
	 * 
	 */
	
	// 무조건 딱 한 번만 생성되고 없을 때에만 생성
	// 이미 존재한다면 존재하는 객체를 사용
	private static JDBCTemplate instance;
	private static Connection conn;
	private JDBCTemplate() {
		
	}
	
	public static JDBCTemplate getInstance() {
		// 이미 만들어져 있는지 체크
		if(instance == null) {
			// 없다면 만들어서 사용
			instance = new JDBCTemplate();
		}
		// 있다면 사용
		return instance;
	}
	private Properties prop;
	public Connection createConnection() {
		
		try {
			prop = new Properties();
			Reader reader = new FileReader("resources/dev.properties");
			prop.load(reader);
			String driverName = prop.getProperty("driverName");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			if(conn == null || conn.isClosed()) {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, password);
				// DBCP(DataBase Connection Pool)
				conn.setAutoCommit(false); // 오토 커밋 해제
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) 
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
