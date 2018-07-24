package com.luv2code.hibernate.demo.stddemo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory= (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session=factory.getCurrentSession();
        try {


            int studentId=1;

            //now get a new session and start transaction
            session=factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on id: primary key
            System.out.println("\nGetting student with id: "+ studentId);
            //delete the student
//            Student myStudent=session.get(Student.class,studentId);
//            System.out.println("Student "+myStudent+" deleted");
//            session.delete(myStudent);

            //delete the student id=2
            session.createQuery("delete from Student where id=2").executeUpdate();
            System.out.println("Student id=2  deleted");








            session.getTransaction().commit();

            System.out.println("Done!");

        }
        finally {
            factory.close();
        }




    }
}
