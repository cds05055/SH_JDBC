package com.kh.jdbc.day03.student.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day03.student.controller.StudentController;
import com.kh.jdbc.day03.student.model.vo.Student;

public class StudentView {
	private StudentController ctrl;
	
	public StudentView() {
		ctrl = new StudentController();
	}
	
	public void studentProgram() {
		List<Student> sList = null;
		Student student = null;
		
		end:
		for(;;) {
			int input = printMenu();
			switch(input) {
				case 0 : 
					student = inputLoginInfo();
					student = ctrl.studentLogin(student);
					if(student != null) {
						// 성공
						
						displaySuccess("로그인 성공");
					}else {
						// 실패
						displayError("로그인 실패");
					}
					break;
					
				case 1 : 
					sList = ctrl.selectAllStudent();
					if(!sList.isEmpty()) {
						showAllStudents(sList);
						displaySuccess("모든 학생의 정보 조회 성공");
					}else
						displayError("학생 정보가 조회되지 않습니다.");
					break;
				
				case 2 : 
					String studentId = inputStudentId("검색");
					student = ctrl.printStudentById(studentId);
					if(student != null) {
						showStudent(student);
						displaySuccess(studentId + "로 검색한 학생 정보 조회 성공");
					}else {
						displayError("해당 아이디의 학생은 존재하지 않습니다.");
					}
					break;
					
				case 3 : 
					String studentName = inputStudentName("검색");
					sList = ctrl.printStudentsByName(studentName);
					if(!sList.isEmpty()) {
						showAllStudents(sList);
						displaySuccess(studentName + "로 검색한 학생 정보 조회 성공");
					}else {
						displayError("학생 정보가 조회되지 않습니다.");
					}
					break;
					
				case 4 : 
					student = inputStudent();
					int result = ctrl.insertStudent(student);
					if(result > 0) {
						displaySuccess("학생 정보 등록 성공");
					} else {
						displayError("학생 정보 등록 실패");
					}
					break;
					
				case 5 : 
					studentId = inputStudentId("수정");
					student = ctrl.printStudentById(studentId);
					if(student != null) {
						student = modifyStudent();
						student.setStudentId(studentId);
						result = ctrl.modifyStudent(student);
						if(result > 0) {
							// 성공 메시지 출력
							displaySuccess("학생 정보가 변경되었습니다.");
						}else {
							// 실패 메시지 출력
							displayError("학생 정보가 수정되지 않았습니다.");
						}
					}else {
						displayError("해당 아이디의 학생은 존재하지 않습니다.");
					}
					break;
					
				case 6 : 
					studentId = inputStudentId("삭제");
					result = ctrl.deleteStudent(studentId);
					if(result > 0) {
						displaySuccess("학생 정보 삭제 성공");
					} else {
						displayError("삭제가 완료되지 않았습니다.");
					}
					break;
					
				case 7 : 
					endProgram();
					break end;
			}
		}
	}
	
	private Student inputLoginInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 학생 로그인 ======");
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		Student student = new Student(studentId, studentPw);
		return student;
	}

	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생관리 프로그램 =====");
		System.out.println("0. 학생 로그인");
		System.out.println("1. 학생 전체 조회");
		System.out.println("2. 학생 아이디로 조회");
		System.out.println("3. 학생 이름으로 조회");
		System.out.println("4. 학생 정보 등록");
		System.out.println("5. 학생 정보 수정");
		System.out.println("6. 학생 정보 삭제");
		System.out.println("7. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		return input;
	}

	private Student inputStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		System.out.print("이름 : ");
		String studentName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();	// 공백 제거, 엔터 제거
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();
		Student student = new Student(studentId, studentPw, studentName
				, gender, age, email, phone, address, hobby);
		return student;
	}

	private String inputStudentName(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 이름 : ");
		String searchName = sc.next();
		return searchName;
	}

	private String inputStudentId(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 아이디 : ");
		String searchId = sc.next();
		return searchId;
	}

	private Student modifyStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 정보 수정 =====");
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();	// 공백 제거, 엔터 제거
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();
		Student student = new Student(studentPw, email, phone, address, hobby);
		return student;
	}

	private void showStudent(Student student) {
		System.out.println("===== 학생 정보 출력(아이디로 조회) =====");
		System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s"
				+ ", 성별 : %s, 이메일 : %s, 전화번호 : %s, 주소 : %s"
				+ ", 취미 : %s, 가입날짜 : %s\n"
				, student.getStudentName()
				, student.getAge()
				, student.getStudentId()
				, student.getGender()
				, student.getEmail()
				, student.getPhone()
				, student.getAddress()
				, student.getHobby()
				, student.getEnrollDate());
	}

	private void showAllStudents(List<Student> sList) {
		System.out.println("====== 학생 전체 정보 출력 ======");
		for(Student student : sList) {
			System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %s"
					+ ", 이메일 : %s, 전화번호 : %s, 주소 : %s, 취미 : %s, 가입날짜 : %s\n"
					, student.getStudentName()
					, student.getAge()
					, student.getStudentId()
					, student.getGender()
					, student.getEmail()
					, student.getPhone()
					, student.getAddress()
					, student.getHobby()
					, student.getEnrollDate());
		}
	}

	private void displaySuccess(String message) {
		System.out.println("[서비스 성공] : " + message);
	}
	
	private void displayError(String message) {
		System.out.println("[서비스 실패] : " + message);
	}

	private void endProgram() {
		System.out.println("프로그램을 종료합니다.");
	}
}
