package cn.sumeirui.test;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sumei on 17/10/20.
 */
public class TestMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSession session = (SqlSession) con.getBean("sqlSession");


    }
}
