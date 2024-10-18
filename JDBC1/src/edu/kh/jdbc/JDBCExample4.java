package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {

	public static void main(String[] args) {
		
		// 부서명을 입력받아
		// 해당 부서에 근무하는 사원의
		// 사번, 이름, 부서명, 직급명을
		// 직급코드 오름차순으로 조회
		
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
			System.out.print("부서 입력 : " );
			String input = sc.nextLine();
			String sql = """						
					SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '없음') DEPT_TITLE, JOB_NAME
					FROM EMPLOYEE
					LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
					JOIN JOB USING(JOB_CODE) 
					WHERE DEPT_TITLE = '""" + input
					+ "' ORDER BY JOB_CODE ";
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
				
			
			rs = stmt.executeQuery(sql);
			boolean flag = true;
			// 조회 결과 있다면 false
			
			while(rs.next()) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				
				System.out.printf("사번 : %s / 이름 : %s / 부서 : %s / 직급 : %s \n", empId, empName, deptTitle, jobName);
				flag = false;
			}
			if(flag) {System.out.println("일치하는 부서가 없습니다");}
			 
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


