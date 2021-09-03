package sunwul.learning.springaop.controller;

import org.springframework.web.bind.annotation.*;
import sunwul.learning.springaop.entity.Test;

import java.util.*;

/*****
 * @author sunwul
 * @date 2021/9/2 9:25
 * @description
 */
@RestController
public class TestController {

    List<Test> tests = new ArrayList<>();

    {
        Test test1 = new Test(1,"a","192.168.1.1",false,7F);
        Test test2 = new Test(2,"b","192.168.1.2",true,6F);
        Test test3 = new Test(3,"c","192.168.1.3",false,5F);

        tests.add(test1);
        tests.add(test2);
        tests.add(test3);
    }

    @GetMapping("/get")
    public String get(){
        return "getTest";
    }

    @PostMapping("/post")
    public List<Test> post(){
        return tests;
    }

    @PutMapping("/put")
    public Test put(@RequestParam("name") String name, @RequestParam("isLock") boolean isLock){
        return new Test(1,name,"192.168.1.1",isLock,4F);
    }

    @DeleteMapping("/delete/{rmId}")
    public List<Test> delete(@PathVariable int rmId){
        tests.remove(rmId);
        return tests;
    }
}
