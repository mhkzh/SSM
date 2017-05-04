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

@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  

public class TestMyBatis {
//	private static Logger logger = Logger.getLogger(TestMyBatis.class);  
    //private ApplicationContext ac = null;  
	//@Autowired(Spring)��@Resource(J2EE)����������װ��bean. ������д���ֶ���,��д��setter�����ϡ�@Resource�Ƚ�����
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
        // logger.info("ֵ��"+user.getUserName());  
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
    	user.setUserName("���Բ���");
    	userService.insert(user);
    }

}
