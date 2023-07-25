package com.kh.jdbc.day04.controller;

import java.util.List;

import com.kh.jdbc.day04.model.service.StudentService;
import com.kh.jdbc.day04.model.vo.Student;

public class StudentController {
	private StudentService sService;
	
	public StudentController() {
		sService = new StudentService();
	}
	
	public List<Student> selectAllStudents() {
		List<Student> sList = sService.selectAllStudents();
		return sList;
	}

	public List<Student> selectStudentsByName(String studentName) {
		List<Student> sList = sService.selectStudentsByName(studentName);
		return sList;
	}

	public Student selectOneById(String studentId) {
		Student student = sService.selectOneById(studentId);
		return student;
	}

	public Student printStudentById(String studentId) {
		Student student = sService.selectOneById(studentId);
		return student;
	}

	public int insertStudent(Student student) {
		int result = sService.insertStudent(student);
		return result;
	}

	public int deleteStudent(String studentId) {
		int result = sService.deleteStudent(studentId);
		return result;
	}

	public int modifyStudent(Student student) {
		int result = sService.updateStudent(student);
		return result;
	}

}
