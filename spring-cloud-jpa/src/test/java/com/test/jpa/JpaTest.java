package com.test.jpa;

import com.entity.Customer;
import com.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    @Test
    public void saveData(){

        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("郝林欢");
        customer.setCustIndustry("老师");
        manager.persist(customer);
        tx.commit();
        manager.close();
    }

    @Test
    public void testFind(){

        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = manager.find(Customer.class,1L);
        System.out.println(customer);
        tx.commit();
        manager.close();
    }

    @Test
    public void testReference(){

        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = manager.getReference(Customer.class,1L);
        System.out.println(customer);
        tx.commit();
        manager.close();
    }

    @Test
    public void testDelete(){

        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = manager.find(Customer.class,1L);
        manager.remove(customer);
        tx.commit();
        manager.close();
    }

    @Test
    public void testUpdate(){

        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustId(2L);
        customer.setCustAddress("平顶山");
        manager.merge(customer);
        tx.commit();
        manager.close();
    }
}
