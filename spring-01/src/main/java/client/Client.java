package client;

import com.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        com.bean.IAccountService ac = (com.bean.IAccountService)app.getBean("accountService");
        ac.saveAccount();
        Student s = (Student)app.getBean("student");
        System.out.println(s.getAge());
        app.close();
    }
}
