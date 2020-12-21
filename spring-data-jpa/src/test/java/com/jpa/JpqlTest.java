package com.jpa;

import com.dao.CustomerDao;
import com.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void  testFindJPQL() {
        Customer customer = customerDao.findJpql("郝林欢");
        System.out.println(customer);
    }

    @Test
    public void testFindCustNameAndId() {
        // Customer customer =  customerDao.findCustNameAndId("传智播客",1l);
        Customer customer =  customerDao.findCustNameAndId(2l,"郝林欢");
        System.out.println(customer);
    }

    /**
     * 测试jpql的更新操作
     *  * springDataJpa中使用jpql完成 更新/删除操作
     *         * 需要手动添加事务的支持
     *         * 默认会执行结束之后，回滚事务
     *   @Rollback : 设置是否自动回滚
     *          false | true
     */
    @Test
    @Transactional //添加事务的支持
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updateCustomer(4l,"李三","河南省平顶山市");
    }

    //测试sql查询
    @Test
    public void testFindSql() {
        List<Customer> list = customerDao.findSql("%黑马%");
        for(Customer obj : list) {
            System.out.println(obj);
        }
    }

    //测试方法命名规则的查询
    @Test
    public void testNaming() {
//        Customer customer = customerDao.findByCustName("黑马程序员");
        Customer customer = customerDao.findByCustPhone("13823659452");
        System.out.println(customer);
    }

    //测试方法命名规则的查询
    @Test
    public void testFindByCustNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("黑马程序员%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    //测试方法命名规则的查询
    @Test
    public void testFindByCustPhoneIsNull() {
        List<Customer> list = customerDao.findByCustPhoneIsNull();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    //测试方法命名规则的查询
    @Test
    public void testFindByCustNameLikeAndCustIndustry() {
        List<Customer> customerList = customerDao.findByCustNameLikeAndCustIndustry("黑马%", "it教育");
        if(!CollectionUtils.isEmpty(customerList)){
            for (Customer customer : customerList) {
                System.out.println(customer);
            }
        }
    }

}
