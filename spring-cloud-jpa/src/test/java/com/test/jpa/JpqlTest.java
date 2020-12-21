package com.test.jpa;

import com.entity.Customer;
import com.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {

    @Test
    public void testFindAll(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        String sql = "from com.entity.Customer";
        Query query = manager.createQuery(sql);
        List<Customer> list = query.getResultList();
        for(Customer customer:list){
            System.out.println(customer);
        }
        tx.commit();
        manager.close();

    }
}
