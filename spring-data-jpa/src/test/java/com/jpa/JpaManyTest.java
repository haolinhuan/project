package com.jpa;

import com.dao.CustomerDao;
import com.dao.LinkManDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.entity.Customer;
import com.entity.LinkMan;
import com.entity.Role;
import com.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class JpaManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Autowired
    private RoleDao roleDao;


    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    @Rollback(false)
    public void save(){
        Customer customer = new Customer();
        customer.setCustName("TBD云集中心");
        customer.setCustLevel("VIP客户");
        customer.setCustSource("网络");
        customer.setCustIndustry("商业办公");
        customer.setCustAddress("昌平区北七家镇");
        customer.setCustPhone("010-84389340");


        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("TBD联系人");
        linkMan.setLkmGender("male");
        linkMan.setLkmMobile("13811111111");
        linkMan.setLkmPhone("010-34785348");
        linkMan.setLkmEmail("98354834@qq.com");
        linkMan.setLkmPosition("老师");
        linkMan.setLkmMemo("还行吧");

        linkMan.setLkmName("百度");
        /**
         * 配置了客户到联系人的关系
         *      从客户的角度上：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         * 由于我们配置了客户到联系人的关系：客户可以对外键进行维护
         */
//
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);
        customerDao.save(customer);
        //linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void testDelete() {
        Customer customer = customerDao.findOne(1L);
        customerDao.delete(customer);
    }

    /**
     * 需求：
     * 	保存用户和角色
     * 要求：
     * 	创建2个用户和3个角色
     * 	让1号用户具有1号和2号角色(双向的)
     * 	让2号用户具有2号和3号角色(双向的)
     *  保存用户和角色
     * 问题：
     *  在保存时，会出现主键重复的错误，因为都是要往中间表中保存数据造成的。
     * 解决办法：
     * 	让任意一方放弃维护关联关系的权利
     */
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void test1(){
        //创建对象
        User u1 = new User();
        u1.setUserName("用户1");
        Role r1 = new Role();
        r1.setRoleName("角色1");
        //建立关联关系
        u1.getRoles().add(r1);
        r1.getUsers().add(u1);
        //保存
        roleDao.save(r1);
        userDao.save(u1);
    }

    @Test
    public void testFind() {
        LinkMan linkMan = linkManDao.findOne(1l);
        Customer customer = linkMan.getCustomer(); //对象导航查询
        System.out.println(customer);
    }


}
