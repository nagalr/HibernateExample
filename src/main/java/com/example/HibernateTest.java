package com.example;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Department department = new Department("java");
        session.save(department);

        session.save(new Employee("Jakab Gipsz", department));
        session.save(new Employee("Captain Nemo", department));

        session.getTransaction().commit();

        System.out.println("************************* just before the Employee query *******************");

        Query q = session.createQuery("From Employee "); // HQL syntax

        List<Employee> resultList = q.list();
        System.out.println("\nnum of employees: " + resultList.size() + "\n");
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }

        System.out.println("\n************************* just before the Department query *******************\n");

        Query q2 = session.createQuery("From Department ");

        List<Department> resultList2 = q2.list();

        System.out.println("\nnum of Departments: " + resultList2.size() + "\n");

        for (Department dept : resultList2) {
            System.out.println("next Department: " + dept);
        }

        System.out.println("\n************************ just before the Department specific query **************\n");

        Query q3 = session.createQuery("select name from Department ");

        List<String> resultList3 = q3.list();

        for (String st : resultList3) {
            System.out.println("\nDepartment name: " + st);
        }

        session.close();
    }
}
