package com.kh.jdbc.day03.student.controller;

import java.util.List;

import com.kh.jdbc.day03.student.model.dao.StudentDAO;
import com.kh.jdbc.day03.student.model.vo.Student;

public class StudentController {
	private StudentDAO stDao;
	
	public StudentController() {
		stDao = new StudentDAO();
	}
	
	public List<Student> selectAllStudent() {
		List<Student> sList = stDao.selectAll();
		return sList;
	}

	public Student printStudentById(String studentId) {
		Student student = stDao.selectOneById(studentId);
		return student;
	}

	public List<Student> printStudentsByName(String studentName) {
		List<Student> sList = stDao.selectStudentsbyName(studentName);
		return sList;
	}

	public int insertStudent(Student student) {
		int result = stDao.insertStudent(student);
		return result;
	}

	public int deleteStudent(String studentId) {
		int result = stDao.deleteStudent(studentId);
		return result;
	}

	public int modifyStudent(Student student) {
		int result = stDao.updateStudent(student);
		return result;
	}

	public Student studentLogin(Student student) {
		Student std = stDao.selectLoginInfo(student);
		return std;
	}

}
