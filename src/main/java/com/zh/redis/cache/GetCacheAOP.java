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
import org.springframework.stereotype.Component;

import com.zh.redis.util.RedisCache;



/**
 * GetCache��Ӧ������
 * @author zh
 *
 */

/**@Component
 * ע�����������������ʵ����Ϊbean��
 * �����Ĳ���Ҳ������Spring�������ļ���ͨ��<bean id="" class=""/>��ʵ�֣�
 * Ч��һ�¡�����ע��ʵ�ֺ�����ʵ�����߲��ܹ��棬
 * ��Ϊ�����Spring������ͬʱ��������ͬ��������bean��
 * ���µĽ���������е�֪ͨ����ִ�����Σ�
 * ��Ϊ��������һ�������汻���뵽ҵ������С�**/
//@Component //ʵ����Ϊbean��������Spring��������
@Aspect  //��������һ������
public class GetCacheAOP  {  
      
    @Autowired //���������������Spring����ע��
    private RedisTemplate<Serializable, Object> redisTemplate;
    private RedisCache redisCache = new RedisCache();
  
    //���������
    @Pointcut("@annotation(com.zh.redis.cache.GetCache)")  
    public void getCache(){
        System.out.println("����һ�������");  
    }  
    
    /** 
     * ����֪ͨ,�����б�ע@getCache�ĵط����� 
     * @param joinPoint 
     */
    @Around("getCache()")
    public Object beforeExec(ProceedingJoinPoint joinPoint){  
        
        //ǰ�ã���redis�в�ѯ����
        System.out.println("���ô�redis�в�ѯ�ķ���...");
        //redis��key��ʽ��id
        String redisKey = getCacheKey(joinPoint);
        //��ȡ��redis�в�ѯ���Ķ���
        Object objectFromRedis = redisCache.getDataFromRedis(redisKey);
        //�����ѯ����
        if(null != objectFromRedis){
            System.out.println("��redis�в�ѯ��������...����Ҫ��ѯ���ݿ�");
            return objectFromRedis;
        }
        System.out.println("û�д�redis�в鵽����...");
        //û�в鵽����ô��ѯ���ݿ�
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("�����ݿ��в�ѯ������...");
        //���ã������ݿ��в�ѯ�����ݷŵ�redis��
        System.out.println("���ð����ݿ��ѯ�����ݴ洢��redis�еķ���...");
        redisCache.setDataToRedis(redisKey, object);;
        System.out.println("redis�е�����..."+object.toString());
        //����ѯ�������ݷ���
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
        String ActionName = method.getAnnotation(GetCache.class).name();  
        String fieldList = method.getAnnotation(GetCache.class).value();  
        //System.out.println("ǩ����"+ms.toString());
        for (String field:fieldList.split(","))   
             ActionName +="."+field;
    
        //�Ȼ�ȡĿ�귽������
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