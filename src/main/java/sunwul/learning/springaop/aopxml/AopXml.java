package sunwul.learning.springaop.aopxml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import sunwul.learning.springaop.aop.TestAop;

import java.util.Arrays;

/*****
 * @author sunwul
 * @date 2021/9/3 15:18
 * @description  使用XML配置AOP
 */
public class AopXml {

    private static final Logger log = LoggerFactory.getLogger(TestAop.class);


    public void aspectBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        String longString = joinPoint.toLongString();
        String shortString = joinPoint.toShortString();

        log.info("XML - 【前置通知】\r\n参数(args):{}\r\n连接点签名(signature):{}\r\n目标对象(target):{}\r\n" +
                        "当前正在执行的对象(This);{}\r\n封装此连接点静态部分的对象(staticPart):{}\r\n" +
                        "连接点对应的源位置(sourceLocation):{}\r\n连接点的扩展字符串表示形式(longString):{}\r\n" +
                        "连接点类型的字符串(shortString):{}"
                , Arrays.asList(args), signature, target, aThis, staticPart, sourceLocation, longString, shortString);
    }


    public void aspectAfter(JoinPoint joinPoint) {
        log.info("【后置通知】\r\npointKind:{}", joinPoint.getKind());
    }


    public void aspectAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("XML - 【返回通知】\r\n连接点类型的字符串(shortString):{},\r\n目标方法返回结果)result:{}", joinPoint.toShortString(), result.toString());
    }


    public void aspectAfterThrowing(JoinPoint jp, Exception ex) {
        String methodName = jp.getSignature().getName();
        log.info("XML - 【异常通知】\r\n" + methodName + "方法异常：" + ex.getMessage());
    }


    public Object aspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("XML - 【环绕通知】->开始\r\n方法:{}\r\n参数:{} ", joinPoint.getSignature(), Arrays.asList(joinPoint.getArgs()).toString());
        Object proceed = joinPoint.proceed(joinPoint.getArgs());

        stopWatch.stop();
        long watchTime = stopWatch.getTotalTimeMillis();
        log.info("XML - 【环绕通知】->结束\r\n方法:{}\r\n返回值:{}\r\n耗时={} (毫秒)", joinPoint.getSignature(), proceed.toString(), watchTime);
        //环绕通知方法最后一定要返回目标方法的执行结果
        return proceed;
    }
}
