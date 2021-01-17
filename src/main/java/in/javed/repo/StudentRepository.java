package in.javed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.javed.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
	

}
