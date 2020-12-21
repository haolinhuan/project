package com.jpa;

import com.dao.CustomerDao;
import com.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerJpaTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne(){

        Specification<Customer> specification= new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = criteriaBuilder.equal(custName,"黑马程序员");
                return predicate;
            }
        };
       Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     */
    @Test
    public void testSpec(){

        Specification<Customer> specification= new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate predicate1 = criteriaBuilder.equal(custName,"黑马程序员");
                Predicate predicate2 = criteriaBuilder.equal(custIndustry,"it教育");
                //将多个查询条件组合到一起
//                Predicate predicate = criteriaBuilder.and(predicate1,predicate2);
                Predicate predicate = criteriaBuilder.or(predicate1,predicate2);
                return predicate;
            }
        };
        List<Customer> customer = customerDao.findAll(specification);
        System.out.println(customer);
    }

    /**
     * 模糊查询
     */
    @Test
    public void testSpec2(){

        Specification<Customer> specification= new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate predicate1 = criteriaBuilder.like(custName.as(String.class),"黑马程序员%");
                Predicate predicate2 = criteriaBuilder.like(custIndustry.as(String.class),"it教育");
                //将多个查询条件组合到一起
//                Predicate predicate = criteriaBuilder.and(predicate1,predicate2);
                Predicate predicate = criteriaBuilder.or(predicate1,predicate2);
                return predicate;
            }
        };
        List<Customer> customer = customerDao.findAll(specification);
        System.out.println(customer);
    }


    /**
     * 分页查询
     */
    @Test
    public void testSpec3(){

        Pageable pageable = new PageRequest(0,2);

        Specification<Customer> specification= new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate predicate1 = criteriaBuilder.like(custName.as(String.class),"黑马程序员%");
                Predicate predicate2 = criteriaBuilder.like(custIndustry.as(String.class),"it教育");
                //将多个查询条件组合到一起
//                Predicate predicate = criteriaBuilder.and(predicate1,predicate2);
                Predicate predicate = criteriaBuilder.or(predicate1,predicate2);
                return predicate;
            }
        };
        Page<Customer> customer = customerDao.findAll(specification,pageable);
        System.out.println(customer.getContent());
        System.out.println(customer.getTotalPages());
        System.out.println(customer.getTotalElements());
    }
}
