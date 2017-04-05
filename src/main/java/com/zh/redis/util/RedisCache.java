package com.zh.redis.util;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisCache {
    @Autowired
    private JedisPool jedisPool = new JedisPool();

    //��redis�����в�ѯ�������л�
    public Object getDataFromRedis(String redisKey){
        //��ѯ
        Jedis jedis = jedisPool.getResource();
        byte[] result = jedis.get(redisKey.getBytes());
        
        //�����ѯû��Ϊ��
        if(null == result){
            return null;
        }
        
        //��ѯ���ˣ������л�
        return SerializeUtil.unSerialize(result);
    }
    
    //�����ݿ��в�ѯ�������ݷ���redis
    public void setDataToRedis(String redisKey, Object obj){
        
        //���л�
        byte[] bytes = SerializeUtil.serialize(obj);
        
        //����redis
        Jedis jedis = jedisPool.getResource();
        String success = jedis.set(redisKey.getBytes(), bytes);
        
        if("OK".equals(success)){
            System.out.println("���ݳɹ����浽redis...");
        }
    }
    
    //����redisKey�������
    public void delDataToRedis(String redisKey){
    	   Jedis jedis = jedisPool.getResource();
    	   jedis.del(redisKey);
    	   System.out.println("�������������...");
    }
}
