package cn.sumeirui.controller;

import cn.sumeirui.entity.Testt;
import cn.sumeirui.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by sumei on 17/10/22.
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @RequestMapping("/save")
    public void save(){
        System.out.println("controller use");
        try {
            service.insert(new Testt(0,4,"秋水"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/del")
    public void del(){
        System.out.println("controller use");
        service.del(3);
    }
    @RequestMapping("/query")
    public void query(){
        System.out.println("controller use");
        Testt testt = service.queryById(Testt.class,7);
        System.out.println("test: " + testt);
    }
    @RequestMapping("/querypage")
    public void querypage(){
        System.out.println("controller use");
            List<Testt> testts = service.queryByPage( 0, 2);

            System.out.println(testts);
    }

    @RequestMapping("/update")
    public void update(){
        System.out.println("controller use");

            service.update(new Testt(2,100,"xiaoxb"));
        }


}
