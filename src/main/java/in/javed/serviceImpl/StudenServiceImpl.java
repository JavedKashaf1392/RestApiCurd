package in.javed.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.javed.model.Student;
import in.javed.repo.StudentRepository;
import in.javed.service.StudentService;

@Service
public class StudenServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository repo;

	@Override
	public Integer saveStudent(Student s) {
		return repo.save(s).getStdId();
	}

	@Override
	public List<Student> findAllStudents() {
		return repo.findAll();
	}

	@Override
	public Student findOneStudent(Integer id) {
		Student s=null;
		Optional<Student> opt=repo.findById(id);
		if(opt.isPresent()) {
			s=opt.get();
		}
		return s;
	}

	@Override
	public void deleteStudent(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public void updateStudent(Student s) {
		repo.save(s);
	}

	@Override
	public boolean isStudentExist(Integer id) {
		boolean status=repo.existsById(id);
		return true;
	}

}
