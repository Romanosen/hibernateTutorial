package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory= (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session=factory.getCurrentSession();
        try {

            //create student object
            System.out.println("Create new student obj");
            Student student=new Student("Duffy","Duck","duffy@luv2code.com");
            //start transaction
                session.beginTransaction();
            //save the student object

            System.out.println("Save the student");
            System.out.println(student);
            session.save(student);
            //commit transaction
            session.getTransaction().commit();


            //Retrieving the object
            System.out.println("saved student generated id"+student.getId());

            //now get a new session and start transaction
            session=factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on id: primary key
            System.out.println("\nGetting student with id: "+ student.getId());

            Student student1=session.get(Student.class,student.getId());

            System.out.println("Get complete: "+student1);

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }
        finally {
            factory.close();
        }




    }
}
