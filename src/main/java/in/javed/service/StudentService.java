package in.javed.service;

import java.util.List;

import in.javed.model.Student;

public interface StudentService {
	
	Integer saveStudent(Student s);
	List<Student> findAllStudents();
	Student findOneStudent(Integer id);
	void deleteStudent(Integer id);
	void updateStudent(Student s);
	boolean isStudentExist(Integer id);

}
