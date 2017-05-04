package com.javen.testmybatis;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import com.zh.model.User;
import com.zh.service.DubboTestService;
import com.zh.service.IUserService;
import com.zh.util.Constants;
import com.zh.util.DataSourceHolder;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  

public class TestMyBatis {
//	private static Logger logger = Logger.getLogger(TestMyBatis.class);  
    //private ApplicationContext ac = null;  
	//@Autowired(Spring)与@Resource(J2EE)都可以用来装配bean. 都可以写在字段上,或写在setter方法上。@Resource比较优雅
    @Resource  
    private IUserService userService; 
//    @Resource
//    private DubboTestService dubboTestService;
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
//    @Test  
    public void test1() {  
        User user = userService.getUserByIdTest(1);  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
//        logger.info(JSON.toJSONString(user));  
    }  
    
//    @Test
    public void selectByPrimaryKey() throws Exception {
        DataSourceHolder.setDataSources(Constants.DATASOURCE_ONE);
        User user = userService.getUserByIdTest(1);
        System.out.println(user.getUserName());
    }
    
    @Test
    public void insert(){
    	DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
    	User user = new User();
    	user.setAge(12);
    	user.setPassword("123456");
    	user.setUserName("测试插入");
    	userService.insert(user);
    }

}
