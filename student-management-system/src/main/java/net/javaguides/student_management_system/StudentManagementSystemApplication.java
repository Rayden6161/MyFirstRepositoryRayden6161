package net.javaguides.student_management_system;

import net.javaguides.student_management_system.entity.Student;
import net.javaguides.student_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "net.javaguides.student_management_system")
public class StudentManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}
@Autowired
private StudentRepository studentRepository;
	@Override
	public void run(String... args) throws Exception {
/*		Student student1 = new Student("Marko","Kosovic","kosovicmarko61@gmail.com");
		studentRepository.save(student1);

		Student student2= new Student("Jelena","Borilovic","jelena@gmail.com");
		studentRepository.save(student2);

		Student student3 = new Student("Danka","Kosovic","Danka@gmail.com");
		studentRepository.save(student3);*/

	}
}
