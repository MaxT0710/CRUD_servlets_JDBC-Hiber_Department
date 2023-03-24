package repository;

import model.Department;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class EmpRepoHiber implements EmployeeRepository{


    @Override
    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("FROM Employee ")
                .list();
        return employees;
    }


    public Employee findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class,id);
    }
    public List<Employee> findByDepId(int depId) {
        return (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("SELECT e FROM  Employee e WHERE e.depId = :depId")
                .setParameter("depId" , depId)
                .list();
    }


    public int create(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(employee);
        tx1.commit();
        session.close();
        return 0;
    }

    public int delete(int employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employee);
        tx1.commit();
        session.close();
        return employee;
    }

    public int update(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(employee);
        tx1.commit();
        session.close();
        return 0;
    }


}
