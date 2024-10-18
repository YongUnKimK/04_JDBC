package edu.kh.jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBCExample3 {
	public static void main(String[] args) {
		// 입력 받은 최소 급여 이상
		// 입력 받은 최대 급여 이하를 받는
		// 사원의 사번, 이름, 급여를 급여 내림차순으로 조회 (DESC)
		// 이클립스 콘솔 출력
		
		// [실행화면]
		// 최소 급여 : 100000
		// 최대 급여 : 10000000
		// 사번 / 이름 / 급여
		
		// 1. JDBC 객체 참조 변수 선언하기
		Connection conn = null; // 통로
		Statement stmt = null; // 회송통로 
		ResultSet rs = null; // SELECT처럼 결과창
		Scanner sc = new Scanner(System.in);
		try {
			// 2. DriverManager 객체를 이용해서 Connection 객체 생성하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@";
			String host = "localhost";
			String port = ":1521";
			String dbName = ":XE";
			String userName = "kh_kyu";
			String password = "kh1234";
			conn = DriverManager.getConnection(type + host + port + dbName, userName, password);
			System.out.println(conn); // 필수 요소 X
			
			// 3. SQL 작성
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE ORDER BY SALARY DESC";
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs = stmt.executeQuery(sql);
			System.err.print("최소 급여 입력 : ");
			int input1 = sc.nextInt();			
			System.err.print("최대 급여 입력 : ");
			int input2 = sc.nextInt();
			int count = 0;
			String sql1 = """					
							SELECT EMP_ID 
							EMP_NAME SALARY FROM EMPLOYEE 
							WHERE SALARTY BETWEEN """
							+ input1 +  "AND" + input2
							+ "	ORDER BY SALARY DESC ";
			
			
			while (rs.next()) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d 원 \n", empId, empName, salary);
				count++;
				/* 
				 if(salary >= input1 && salary <= input2) {
				 System.out.printf("사번 : %s / 이름 : %s / 급여 : %d 원 \n", empId, empName, salary);}
				*/	
				
			} System.out.print("총원 : "+ count + "명");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
		}
		}
	}
	}
