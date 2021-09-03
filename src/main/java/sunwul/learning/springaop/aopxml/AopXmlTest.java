package sunwul.learning.springaop.aopxml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sunwul.learning.springaop.controller.TestController;

/*****
 * @author sunwul
 * @date 2021/9/3 15:39
 * @description  使用XML配置AOP 测试
 */
public class AopXmlTest {

    public static void main(String[] args) {
        // 加载xml配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        TestController testController = context.getBean("TestController",TestController.class);
        testController.get();
    }
}
