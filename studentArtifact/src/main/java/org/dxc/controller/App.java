package org.dxc.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.dxc.Bean.Student;
import org.dxc.factorydesign.ServiceFactory;
import org.dxc.service.IStudentService;
import org.dxc.service.StudentServiceImplementation;
import org.hibernate.cfg.Configuration;



public class App {
	enum Student_TestCase
	{
		insert_record ,
		get_record,
		view_allRecord,
		delete_record,
		exit_app
	}
	static Scanner scan =  new Scanner(System.in);
	Student_TestCase c = Student_TestCase.view_allRecord;
	public void run()
	{
		Student s = new Student();
		IStudentService service = ServiceFactory.getService();
		boolean isNotStopping = true;
		while(isNotStopping)
		{
			System.out.println("1: Insert new student Record ");
			System.out.println("2: view 1 student record");
			System.out.println("3: view all student record");
			System.out.println("4: delete student");
			System.out.println("5: exit application");
			int x = scan.nextInt();
			int id ;
			String name;
			String addr;
			c =Student_TestCase.values()[x-1];
			
			switch (c) {
			case insert_record:
				
				System.out.println("type student id");
				id =  scan.nextInt();
				System.out.println("type student name");
				name = scan.next();
				System.out.println("type student addrress");
				addr = scan.next();
				
				s.setSid(id);
				s.setSname(name);
				s.setSaddr(addr);
				service.insert(s);
				break;
			case get_record:
	
				System.out.println("type student id to view ");
				id =  scan.nextInt();
				Student student = service.getById(id);
				printStudent(student);
				break;
				
			case delete_record:
				System.out.println("type student id to delete");
				id =  scan.nextInt();
				System.out.println("deleting " + id);
				service.deleteRecord(id);
				break;
			case exit_app:
				isNotStopping = false;
				System.out.println("attempting to end program");
				break;
			
			case view_allRecord:
				System.out.println("get all student");
				List<Student> list = service.getAllStudent();
				viewList(list);

				break;
			default:
				break;
	
			}
			
		}
		scan.close();
	}
	void printStudent(Student student)
	{
		System.out.println( "id"+ student.getSid());
		System.out.println( "name" + student.getSname());
		System.out.println( "add" +  student.getSaddr());
	}
	void viewList(List<Student> students)
	{
		  for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();){
	            Student student = (Student) iterator.next(); 
	            printStudent(student);
	         }
	}
}
