package com.kh.jdbc.day04.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.day04.common.JDBCTemplate;
import com.kh.jdbc.day04.model.dao.StudentDAO;
import com.kh.jdbc.day04.model.vo.Student;

public class StudentService {
	private StudentDAO studentDao;
	private JDBCTemplate jdbcTemplate;
	
	public StudentService() {
		studentDao = new StudentDAO();
		jdbcTemplate = JDBCTemplate.getInstance();
	}

	public List<Student> selectAllStudents() {
		Connection conn = jdbcTemplate.createConnection();
		List<Student> sList = studentDao.selectAllStudents(conn);
		jdbcTemplate.close();
		return sList;
	}

	public List<Student> selectStudentsByName(String studentName) {
		Connection conn = jdbcTemplate.createConnection();
		List<Student> sList = studentDao.selectStudentsByName(conn, studentName);
		jdbcTemplate.close();
		return sList;
	}

	public Student selectOneById(String studentId) {
		Connection conn = jdbcTemplate.createConnection();
		Student student = studentDao.selectOneById(conn, studentId);
		jdbcTemplate.close();
		return student;
	}

	public int insertStudent(Student student) {
		Connection conn = jdbcTemplate.createConnection();
		int result = studentDao.insertStudent(conn, student);
		result += studentDao.updateStudent(conn, student);
		if(result > 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		jdbcTemplate.close();
		return result;
	}

	public int deleteStudent(String studentId) {
		Connection conn = jdbcTemplate.createConnection();
		int result = studentDao.deleteStudent(conn, studentId);
		jdbcTemplate.close();
		return result;
	}

	public int updateStudent(Student student) {
		Connection conn = jdbcTemplate.createConnection();
		int result = studentDao.updateStudent(conn, student);
		jdbcTemplate.close();
		return result;
	}
}
