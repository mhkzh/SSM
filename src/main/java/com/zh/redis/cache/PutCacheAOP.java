package com.zh.redis.cache;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.zh.redis.util.RedisCache;

/**@Component
 * 注解用来将这个切面类实例化为bean，
 * 这样的操作也可以在Spring的配置文件中通过<bean id="" class=""/>来实现，
 * 效果一致。但是注解实现和配置实现两者不能共存，
 * 因为共存后Spring容器会同时管理两个同样的切面bean，
 * 导致的结果将是所有的通知都会执行两次，
 * 因为会有两个一样的切面被切入到业务代码中。**/
//@Component //实例化为bean，并交由Spring容器管理
@Aspect //声明这是一个切面
public class PutCacheAOP {
	
	   @Autowired //声明这个变量将由Spring容器注入
	    private RedisTemplate<Serializable, Object> redisTemplate;
	    private RedisCache redisCache = new RedisCache();
	    
	    //定义切入点
	    @Pointcut("@annotation(com.zh.redis.cache.PutCache)")  
	    public void putCache(){
	        System.out.println("我是一个切入点");  
	    }  
	    
	    /** 
	     * 环绕通知,在所有标注@putCache的地方切入 
	     * @param joinPoint 
	     */
	    @Around("putCache()")
	    public Object beforeExec(ProceedingJoinPoint joinPoint){  
	        
	        //前置：到redis中查询缓存
	        System.out.println("调用从redis中查询的方法...");
	        //redis中key格式：id
	        String redisKey = getCacheKey(joinPoint);
	        Object object = null;
	        //获取从redis中查询到的对象
	        Object objectFromRedis = redisCache.getDataFromRedis(redisKey);
	        //如果查询到了
	        if(null != objectFromRedis){
	            System.out.println("从redis中查到数据,从redis中清空缓存");
	            //清空缓存
	            redisCache.delDataToRedis(redisKey);
	            try {
					object = joinPoint.proceed();//启动目标方法执行
					System.out.println("操作数据库...");
				} catch (Throwable e) {
					e.printStackTrace();
				}
	        }else{
	        	System.out.println("从redis中未查到数据...");
		        //没有查到，那么直接操作数据库
		        try {
		            object = joinPoint.proceed(); //启动目标方法执行
		        } catch (Throwable e) {
		            e.printStackTrace();
		        }
		        System.out.println("操作数据库...");
	        }

	        return object;
	    }
	
	    /**
	     * 根据类名、方法名和参数值获取唯一的缓存键
	     * @return 格式为 "包名.类名.方法名.参数类型.参数值"，类似 "your.package.SomeService.getById(int).123"
	     */
	    @SuppressWarnings("unused")
	    private String getCacheKey(ProceedingJoinPoint joinPoint) {
	        MethodSignature ms=(MethodSignature) joinPoint.getSignature();  
	        Method method=ms.getMethod();  
	        String ActionName = method.getAnnotation(PutCache.class).name();  
	        String fieldList = method.getAnnotation(PutCache.class).value();  
	        //System.out.println("签名是"+ms.toString());
	        for (String field:fieldList.split(","))   
	             ActionName +="."+field;
	    
	        //先获取目标方法参数，确保redis-Key的唯一性，由于我在更新或者插入时需要根据相同的key清除缓存，这里暂不做处理。
	        //String id = null;
	        //Object[] args = joinPoint.getArgs();
	        //if (args != null && args.length > 0) {
	        //    id = String.valueOf(args[0]);
	        //}
	        
	        //ActionName += "="+id;
	        //String redisKey = ms+"."+ActionName;
	        return ActionName;
	    }
	    
	    
	    public void setRedisTemplate(  
	            RedisTemplate<Serializable, Object> redisTemplate) {  
	        this.redisTemplate = redisTemplate;  
	    }

}
