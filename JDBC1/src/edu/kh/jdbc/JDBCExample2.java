package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {

	public static void main(String[] args) {

		// 입력 받은 급여보다 초과해서 받는 사원의
		// 사번, 이름, 급여 조회

		// 1. JDBC 객체 참조용 변수 선언

		// 2. DriverManager 객체를 이용해서 Connection 객체 생성
		// 2-1) Oracle JDBC Driver 객체 메모리 로드
		// 2-2) DB연결 정보 작성
		// 2-3) DB 연결 정보와 DriverManager를 이용해서 Connection 객체 생성

		// 3. SQL 작성
		// 입력받은 급여 -> Scanner 필요함
		// int input 에 급여 담기

		// 4. Statement 객체 생성

		// 5. Statement 객체를 이용하여 SQL 수행 후 결과 반환 받기
		// executeQuery() : SELECT 실행, ResultSet 반환
		// executeUpdate() : DML 실행 , 결과 행의 개수 반환 ( int )

		// 6. 조회 결과가 담겨있는 ResultSet 을
		// 커서 이용해 1행씩 접근해 각 행에 작성된 컬럼값 얻어오기
		// -> while 안에서 꺼낸 데이터 출력

		// 201 / 송중기 / 6000000원
		// ...

		// 7. 사용 완료된 JDBC 객체 자원 반환 ( close )
		// -> 생성된 역순으로 close !

		Connection conn = null; // DB연결정보 저장 객체
		Statement stmt = null; // SQL 수행, 결과 반환용 객체
		ResultSet rs = null; // SELECT 수행 결과 저장 객체

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String type = "jdbc:oracle:thin:@";
			String host = "localhost";
			String port = ":1521";
			String dbName = ":XE";
			String userName = "kh_kyu";
			String password = "kh1234";

			conn = DriverManager.getConnection(type + host + port + dbName, userName, password);

			System.out.println(conn);

			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE";
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			rs = stmt.executeQuery(sql);
			Scanner sc = new Scanner(System.in);
			System.out.print("급여 입력 : ");
			int input = sc.nextInt();

			while (rs.next()) {

				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				if (salary > input) {
					System.out.printf("사번 : %s / 이름 : %s / 급여 : %d\n", empId, empName, salary);
				}

			}
		} catch (ClassNotFoundException e) {
			System.out.println("해당 클래스를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
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
