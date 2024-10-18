package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {

	public static void main(String[] args) {
		
		// 아이디, 비밀번호, 이름을 입력받아
		// 아이디, 비밀번호가 일치하는 사용자(TB_USER)와
		// 이름을 수정(UPDATE)
		
		Connection conn = null;		
		PreparedStatement pstmt = null;
		
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh_kyu";
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("아이디 입력 : ");
			String id = sc.nextLine();
			System.out.print("비밀번호 입력 : ");
			String pw = sc.nextLine();
			System.out.print("이름 입력 : ");
			String name = sc.nextLine();

			String sql = 
						"UPDATE TB_USER SET USER_NAME = ? WHERE USER_ID"
						+ " = ? AND USER_PW = ?" ;					
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);

			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println(name + "님이 수정 되었습니다");
				conn.commit();
			} else {
				System.out.println("맞는 계정이 존재하지 않습니다");
				conn.rollback();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
