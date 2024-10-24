--JDBC 토이프로젝트 제출
---> Dynamic Web Project로 생성
--
--주제 : 학생 관리 프로그램
--
--1. DB 사용자 계정 새로 생성 -> 계정명/비밀번호 자유
--2. 테이블명 : TB_STUDENT
--컬럼 1) STD_NO         NUMBER (PK)                     학생 번호
--       2) STD_NAME     VARCHAR2(20) NOT NULL     학생 이름
--       3) STD_AGE        NUMBER  NOT NULL           학생 나이
--       4) STD_GENDER  CHAR(1)   CHECK                학생 성별('M' , 'F' 제한)
--       5) STD_SCORE    CHAR(1)   CHECK                 학생 성적('A','B','C','D','F' 제한)
--
--3. 화면 구성
--1) 메인페이지 
--	- 등록되어있는 모든 학생 목록 출력
--	- 학생 목록에서 이름 클릭 시 학생 상세조회 화면 이동
--	- 학생 추가 버튼 (클릭 시 학생 추가 화면 이동)    
--
--2) 상세조회 화면
--	- 선택된 학생에 대한 상세 정보 출력(이름/나이/성별/성적)
--	- 목록으로 버튼
--	- 수정 버튼 (클릭 시 수정 화면으로 이동)
--	- 삭제 버튼 (성공 시 메인페이지로, 실패 시 상세조회 화면으로)
--
--3) 학생 추가 화면
--	- 이름/나이/성별/성적 입력 할 수 있는 태그 작성(여러가지 사용해보기 ex.radio, checkbox, select 등)
--	- 추가 성공 시 메인페이지로, 실패 시 추가 화면으로 
--
--4) 수정 화면 
--	- 이름/나이/성적 수정 가능하도록 해야함
--	- 기존 정보 출력되어 있는 상태여야 함
--	- 수정 성공 시 메인페이지로, 실패 시 수정 화면으로
--
--- alret 창에 띄울 message 는 자유

--(SYS) 계정에서
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
CREATE USER toyProject_jdbc IDENTIFIED BY toy1234;
GRANT CREATE SESSION TO toyproject_jdbc;
GRANT RESOURCE, CONNECT TO todoList_jdbc;
ALTER USER todoList_jdbc DEFAULT TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM;

CREATE TABLE TB_STUDENT(
 	STD_NO NUMBER PRIMARY KEY,
 	STD_NAME VARCHAR2(30) NOT NULL,
 	STD_AGE NUMBER NOT NULL,
 	STD_GENDER CHAR(1) CHECK(STD_GENDER IN('M','F')),
 	STD_SCORE CHAR(1) CHECK(STD_SCORE IN('A', 'B', 'C', 'D', 'E'))
);


SELECT * FROM TB_STUDENT  ;
COMMIT;











