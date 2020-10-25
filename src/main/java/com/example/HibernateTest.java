package com.example;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

        System.out.println("************************* inside main *******************");

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Department department = new Department("java");
        session.save(department);

        session.save(new Employee("Jakab Gipsz", department));
        session.save(new Employee("Captain Nemo", department));

        session.getTransaction().commit();

        Query q = session.createQuery("From Employee "); // HQL syntax

        List<Employee> resultList = q.list();
        System.out.println("\nnum of employees: " + resultList.size() + "\n");
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }

        session.close();

    }
}
