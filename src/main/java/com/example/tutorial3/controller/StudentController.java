package com.example.tutorial3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.ui.Model;

import com.example.tutorial3.model.StudentModel;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController(){
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping ("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm,
					  @RequestParam(value = "name", required = true) String name,
					  @RequestParam(value = "gpa", required = true) double gpa) {
			StudentModel student = new StudentModel (npm, name, gpa);
			studentService.addStudent(student);
			return "add";
	}
	
	@RequestMapping("/student/view")
	public String view(Model model, @RequestParam (value = "npm", required = true)String npm) {
		StudentModel student = studentService.selectStudent(npm);
		model.addAttribute("student", student);
		return "view";
	}
	
	@RequestMapping("/student/viewall")
	public String viewAll(Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewall";		
	}
	
	//Soal 1
	
	@RequestMapping(value = {"/student/view/", "/student/view/{npm}"})
	public String greetingPath(@PathVariable String npm, Model model) {
		StudentModel stdn = studentService.selectStudent(npm);
		if(stdn != null) {
			if (stdn.getNpm().equals(npm)) {	
				model.addAttribute("student", stdn);
				return "view";
			}else {			
				model.addAttribute("npm", "APAP");
				return "tidakDitemukan";
			}
		}else {
			return "tidakDitemukan";
		}			
	}
	
	//Soal 2
	@RequestMapping(value = {"/student/delete/", "/student/delete/{npm}"})
	public String delete(@PathVariable String npm, Model model) {	
		StudentModel stdn = studentService.selectStudent(npm);
		if(stdn != null){
			if (stdn.getNpm().equals(npm)) {	
				studentService.deleteStudent(npm);
				return "delete";
			}else {			
				model.addAttribute("npm", "APAP");
				return "tidakDitemukan";
			}
		}else {
			return "tidakDitemukan";
		}
				
	}
}
