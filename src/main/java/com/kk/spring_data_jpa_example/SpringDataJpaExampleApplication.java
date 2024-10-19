package com.kk.spring_data_jpa_example;

import com.kk.spring_data_jpa_example.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringDataJpaExampleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringDataJpaExampleApplication.class, args);

		StudentRepo studentRepo = context.getBean(StudentRepo.class);

		Student s1 = context.getBean(Student.class);
		Student s2 = context.getBean(Student.class);
		Student s3 = context.getBean(Student.class);

		s1.setRollno(1);
		s1.setName("Abcxyz");
		s1.setMarks(50);

		s2.setRollno(2);
		s2.setName("Def");
		s2.setMarks(60);

		s3.setRollno(3);
		s3.setName("Ghi");
		s3.setMarks(70);

		studentRepo.save(s1);
		studentRepo.save(s2);
		studentRepo.save(s3);

		System.out.println("studentRepo.findAll() = " + studentRepo.findAll());

		Student student = studentRepo.findById(11).orElse(new Student());
		System.out.println("student = " + student);

		List<Student> studentsListFromDb = studentRepo.findByName("Abc");
		System.out.println("studentsListFromDb = " + studentsListFromDb);

		List<Student> studentsListByMarks = studentRepo.findByMarks(50);
		System.out.println("studentsListByMarks = " + studentsListByMarks);


		List<Student> studentsByMarksGreaterThan = studentRepo.findByMarksGreaterThan(50);
		System.out.println("studentsByMarksGreaterThan = " + studentsByMarksGreaterThan);

		s3.setMarks(99);
		studentRepo.save(s3);
		System.out.println("after updating, studentRepo.findAll() = " + studentRepo.findAll());

		studentRepo.delete(s1);
		System.out.println("after deleting, studentRepo.findAll() = " + studentRepo.findAll());
	}
}
