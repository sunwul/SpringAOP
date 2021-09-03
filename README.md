#### SpringAOP的简单应用
> 使用两种方式实现SpringAOP

通用类

    |- 目标方法: controller.TestController
    |- 实体: entity.Test

注解使用AOP

    |- 切面: aop.TestAop
    |- 启动类: AopTestApp
    |- 前端: resources.static
    |- 配置: application.yml
    
XML配置使用AOP

    |- 切面实现: aopxml.AopXml
    |- 启动类: aopxml.AopXmlTest
    |- AOP配置: resources.aop.xml 
    
