package sunwul.learning.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/*****
 * @author sunwul
 * @date 2021/9/2 9:34
 * @description aop切面  注解
 *
 * * @Aspect：声明本类为切面类
 * * @Order：指定切入执行顺序，数值越小，切面执行顺序越靠前，默认为 Integer.MAX_VALUE
 */
@Aspect
@Order(value = 1)
@Component
public class TestAop {

    private static final Logger log = LoggerFactory.getLogger(TestAop.class);


    /**
     * @Pointcut ：切入点声明，即切入到哪些目标方法。value 属性指定切入点表达式，默认为 ""。
     * 切入点被通知注解引用，这样通知注解只需要关联此切入点声明即可，无需再重复写切入点表达式
     * value 的 execution 可以有多个，使用 || 隔开.   execution() || execution()
     * 常用表达式:
     * 特定类中的任意方法:                * sunwul.learning.springaop.controller.TestController.*(..)
     * 特定包(不含子包)下任意类的任意方法:  * sunwul.learning.springaop.controller.*.*(..)
     * 特定包(含子包)下任意类的任意方法:    * sunwul.learning.springaop.controller..*.*(..)
     */
    @Pointcut(value = "execution(* sunwul.learning.springaop.controller.TestController.*(..))")
    private void aspectPointcut() {
    }


    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容。
     * value：绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     *
     * @param joinPoint 提供对连接点处可用状态和有关它的静态信息的反射访问
     *                  连接点的方法:
     *                  Object[] getArgs()：返回此连接点处（目标方法）的参数，目标方法无参数时，返回空数组
     *                  Signature getSignature()：返回连接点处的签名。
     *                  Object getTarget()：返回目标对象
     *                  Object getThis()：返回当前正在执行的对象
     *                  StaticPart getStaticPart()：返回一个封装此连接点的静态部分的对象。
     *                  SourceLocation getSourceLocation()：返回与连接点对应的源位置
     *                  String toLongString()：返回连接点的扩展字符串表示形式。
     *                  String toShortString()：返回连接点的缩写字符串表示形式。
     *                  String getKind()：返回表示连接点类型的字符串
     */
    @Before(value = "aspectPointcut()")
    public void aspectBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        String longString = joinPoint.toLongString();
        String shortString = joinPoint.toShortString();

        log.info("【前置通知】\r\n参数(args):{}\r\n连接点签名(signature):{}\r\n目标对象(target):{}\r\n" +
                        "当前正在执行的对象(This);{}\r\n封装此连接点静态部分的对象(staticPart):{}\r\n" +
                        "连接点对应的源位置(sourceLocation):{}\r\n连接点的扩展字符串表示形式(longString):{}\r\n" +
                        "连接点类型的字符串(shortString):{}"
                , Arrays.asList(args), signature, target, aThis, staticPart, sourceLocation, longString, shortString);
    }

    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管目标方法是否发生异常。
     * value：绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     */
    @After(value = "aspectPointcut()")
    public void aspectAfter(JoinPoint joinPoint) {
        log.info("【后置通知】\r\npointKind:{}", joinPoint.getKind());
    }

    /**
     * 返回通知：目标方法返回后执行以下代码
     * value     绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     * pointcut  绑定通知的切入点表达式，优先级高于 value，默认为 ""
     * returning 目标方法的返回值绑定到的参数的名称，默认为 ""
     *
     * @param joinPoint ：提供对连接点处可用状态和有关它的静态信息的反射访问
     * @param result    ：目标方法返回的值，参数名称与 returning 属性值一致。无返回值时，这里 result 会为 null.
     */
    @AfterReturning(pointcut = "aspectPointcut()", returning = "result")
    public void aspectAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("【返回通知】\r\n连接点类型的字符串(shortString):{},\r\n目标方法返回结果)result:{}", joinPoint.toShortString(), result.toString());
    }


    /**
     * 异常通知：目标方法发生异常的时候执行以下代码，此时返回通知不会再触发
     * value     绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     * pointcut  绑定通知的切入点表达式，优先级高于 value，默认为 ""
     * throwing  与方法中的异常参数名称一致，
     *
     * @param ex：捕获的异常对象，名称与 throwing 属性值一致
     */
    @AfterThrowing(pointcut = "aspectPointcut()", throwing = "ex")
    public void aspectAfterThrowing(JoinPoint jp, Exception ex) {
        String methodName = jp.getSignature().getName();
        log.info("【异常通知】\r\n" + methodName + "方法异常：" + ex.getMessage());
    }


    /**
     * 环绕通知
     * value   绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     * ProceedingJoinPoint.proceed(Object[] args) 继续下一个通知或目标方法调用，获得返回值，如果目标方法发生异常，则 proceed 会抛异常.
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     * 目标方法如果是控制层接口:
     * 1.此方法的异常捕获与否都不会影响目标方法的事务回滚
     * 2.此方法 try-catch 了异常后没有继续往外抛，则全局异常处理 @RestControllerAdvice 中不会再触发
     *
     * @param joinPoint 切入点
     * @return Object
     * @throws Throwable throwable
     */
    @Around(value = "aspectPointcut()")
    public Object aspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("【环绕通知】->开始\r\n方法:{}\r\n参数:{} ", joinPoint.getSignature(), Arrays.asList(joinPoint.getArgs()).toString());
        Object proceed = joinPoint.proceed(joinPoint.getArgs());

        stopWatch.stop();
        long watchTime = stopWatch.getTotalTimeMillis();
        log.info("【环绕通知】->结束\r\n方法:{}\r\n返回值:{}\r\n耗时={} (毫秒)", joinPoint.getSignature(), proceed.toString(), watchTime);
        //环绕通知方法最后一定要返回目标方法的执行结果
        return proceed;
    }

}
