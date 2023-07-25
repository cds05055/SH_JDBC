package com.kh.jdbc.day04.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.controller.StudentController;
import com.kh.jdbc.day04.model.vo.Student;

public class StudentView {
	private StudentController ctrl;
	
	public StudentView() {
		ctrl = new StudentController();
	}
	
	public void startProgram() {
		Student student = null;
		List<Student> sList = null;
		
		end:
		for(;;) {
			int choice = printMenu();
			switch(choice) {
				case 1 : 
					sList = ctrl.selectAllStudents();
					if(!sList.isEmpty()) {
						// 성공
						showAllStudents(sList);
						displaySuccess("모든 학생의 정보 조회 성공");
					}else {
						// 실패
						displayError("학생의 정보가 존재하지 않습니다.");
					}
					break;
					
				case 2 : 
					String studentId = inputStudentId("검색");
					student = ctrl.selectOneById(studentId);
					if(student != null) {
						showStudent(student);
						displaySuccess(studentId + "로 검색한 학생 정보 조회 성공");
					} else {
						displayError("해당 학생의 정보가 존재하지 않습니다.");
					}
					break;
					
				case 3 : 
					String studentName = inputStudentName("검색");
					sList = ctrl.selectStudentsByName(studentName);
					if(!sList.isEmpty()) {
						showAllStudents(sList);
						displaySuccess(studentName + "로 검색한 학생 정보 조회 성공");
					} else {
						displayError("해당하는 이름의 학생 정보가 존재하지 않습니다.");
					}
					break;
					
				case 4 : 
					student = inputStudent();
					int result = ctrl.insertStudent(student);
					if(result > 0) {
						displaySuccess("학생 등록 성공");
					} else {
						displayError("학생 등록 실패");
					}
					break;
					
				case 5 : 
					studentId = inputStudentId("수정");
					student = ctrl.printStudentById(studentId);
					if(student != null) {
						student = modifyStudent();
						student.setStudentId(studentId);
						result = ctrl.modifyStudent(student);
						if(result > 0 ) {
							displaySuccess(studentId + " 학생의 정보 수정 성공");
						} else {
							displayError(studentId + " 학생의 정보 수정 실패");
						}
					}
					break;
					
				case 6 : 
					studentId = inputStudentId("삭제");
					result = ctrl.deleteStudent(studentId);
					if(result > 0) {
						displaySuccess("해당 유저의 정보 삭제가 완료되었습니다.");
					} else {
						displayError("해당 유저의 정보 삭제를 실패하였습니다.");
					}
					break;
				
				case 0 : 
					printEndMsg();
					break end;
				default : 
					displayError("메뉴에 해당하는 번호를 입력해주세요.");
					break;
			}
		}
	}
	
	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생관리 프로그램 =====");
		System.out.println("1. 학생 전체 조회");
		System.out.println("2. 학생 아이디로 조회");
		System.out.println("3. 학생 이름으로 조회");
		System.out.println("4. 학생 정보 등록");
		System.out.println("5. 학생 정보 수정");
		System.out.println("6. 학생 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		return input;
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
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();
		Student student = new Student(studentId, studentPw, studentName
				, gender, age, email, phone, address, hobby);
		return student;
	}

	private String inputStudentId(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 아이디 : ");
		String searchId = sc.next();
		return searchId;
	}

	private String inputStudentName(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 이름 : ");
		String searchName = sc.next();
		return searchName;
	}

	private void showStudent(Student student) {
		System.out.println("====== 아이디로 검색한 학생 정보 ======");
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

	private void displaySuccess(String msg) {
		System.out.println("[서비스 성공] : " + msg);
	}

	private void displayError(String msg) {
		System.out.println("[서비스 실패] : " + msg);
		
	}

	private void printEndMsg() {
		System.out.println("프로그램을 종료합니다.");
	}
}
