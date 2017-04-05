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
 * ע�����������������ʵ����Ϊbean��
 * �����Ĳ���Ҳ������Spring�������ļ���ͨ��<bean id="" class=""/>��ʵ�֣�
 * Ч��һ�¡�����ע��ʵ�ֺ�����ʵ�����߲��ܹ��棬
 * ��Ϊ�����Spring������ͬʱ��������ͬ��������bean��
 * ���µĽ���������е�֪ͨ����ִ�����Σ�
 * ��Ϊ��������һ�������汻���뵽ҵ������С�**/
//@Component //ʵ����Ϊbean��������Spring��������
@Aspect //��������һ������
public class PutCacheAOP {
	
	   @Autowired //���������������Spring����ע��
	    private RedisTemplate<Serializable, Object> redisTemplate;
	    private RedisCache redisCache = new RedisCache();
	    
	    //���������
	    @Pointcut("@annotation(com.zh.redis.cache.PutCache)")  
	    public void putCache(){
	        System.out.println("����һ�������");  
	    }  
	    
	    /** 
	     * ����֪ͨ,�����б�ע@putCache�ĵط����� 
	     * @param joinPoint 
	     */
	    @Around("putCache()")
	    public Object beforeExec(ProceedingJoinPoint joinPoint){  
	        
	        //ǰ�ã���redis�в�ѯ����
	        System.out.println("���ô�redis�в�ѯ�ķ���...");
	        //redis��key��ʽ��id
	        String redisKey = getCacheKey(joinPoint);
	        Object object = null;
	        //��ȡ��redis�в�ѯ���Ķ���
	        Object objectFromRedis = redisCache.getDataFromRedis(redisKey);
	        //�����ѯ����
	        if(null != objectFromRedis){
	            System.out.println("��redis�в鵽����,��redis����ջ���");
	            //��ջ���
	            redisCache.delDataToRedis(redisKey);
	            try {
					object = joinPoint.proceed();//����Ŀ�귽��ִ��
					System.out.println("�������ݿ�...");
				} catch (Throwable e) {
					e.printStackTrace();
				}
	        }else{
	        	System.out.println("��redis��δ�鵽����...");
		        //û�в鵽����ôֱ�Ӳ������ݿ�
		        try {
		            object = joinPoint.proceed(); //����Ŀ�귽��ִ��
		        } catch (Throwable e) {
		            e.printStackTrace();
		        }
		        System.out.println("�������ݿ�...");
	        }

	        return object;
	    }
	
	    /**
	     * �����������������Ͳ���ֵ��ȡΨһ�Ļ����
	     * @return ��ʽΪ "����.����.������.��������.����ֵ"������ "your.package.SomeService.getById(int).123"
	     */
	    @SuppressWarnings("unused")
	    private String getCacheKey(ProceedingJoinPoint joinPoint) {
	        MethodSignature ms=(MethodSignature) joinPoint.getSignature();  
	        Method method=ms.getMethod();  
	        String ActionName = method.getAnnotation(PutCache.class).name();  
	        String fieldList = method.getAnnotation(PutCache.class).value();  
	        //System.out.println("ǩ����"+ms.toString());
	        for (String field:fieldList.split(","))   
	             ActionName +="."+field;
	    
	        //�Ȼ�ȡĿ�귽��������ȷ��redis-Key��Ψһ�ԣ��������ڸ��»��߲���ʱ��Ҫ������ͬ��key������棬�����ݲ�������
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
