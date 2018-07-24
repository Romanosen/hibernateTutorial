package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory= (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session=factory.getCurrentSession();
        try {


            //start transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents=session.createQuery(" from Student").list();

            //display students
            displayStudents(theStudents);

            //query students last name Doe

            theStudents=session.createQuery("from Student s where s.lastName='Doe'").list();

            //commit transaction
            System.out.println("\n\nStudents with last name Doe");
            displayStudents(theStudents);


            System.out.println("\n\nStudents with last name Doe OR first name 'daffy'");
            theStudents=session.createQuery("from Student s where"+
                    " s.lastName='Doe' or s.firstName='Duffy'").list();
            displayStudents(theStudents);


            System.out.println("\n\nStudents with email end with luv2code");
            theStudents=session.createQuery("from Student s where"+
                    " s.email like '%gmail.com'").list();
            displayStudents(theStudents);

            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }




    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent:theStudents)
        {
            System.out.println(tempStudent);
        }
    }
}
