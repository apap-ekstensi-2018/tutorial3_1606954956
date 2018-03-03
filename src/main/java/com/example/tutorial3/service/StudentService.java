package com.example.tutorial3.service;
import java.util.List;
import com.example.tutorial3.model.StudentModel;

public interface StudentService {
	StudentModel selectStudent(String npm);
	
	void deleteStudent(String npm);  //soal
	
	List<StudentModel> selectAllStudents();
	
	void addStudent(StudentModel student);
}
