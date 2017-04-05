package com.zh.shiro;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zh.model.ShiroUser;
import com.zh.service.ShiroUserService;

import javax.annotation.Resource;
import java.util.Set;
/**
 * Shiro�Զ�����
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private ShiroUserService t_userService;
    /**
     * ���ڵ�Ȩ�޵���֤��
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        //�����û�����ȡ��ɫ����
        Set<String> roleName = t_userService.findRoles(username) ;
        //��ȡ��ɫ����Ӧ��Ȩ������
        Set<String> permissions = t_userService.findPermissions(username) ;
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }
    /**
     * ����ִ�������¼��֤
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //��ȡ�û��˺�
    	String username = token.getPrincipal().toString();
        ShiroUser user = t_userService.findUserByUsername(username);
        if (user != null){
            //���ｫ��ѯ�����û����������û�����Ľ���ƥ�䣬����ŵ� authenticationInfo���ں����Ȩ���жϡ���������������һ�������ˡ�
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),
                    "a") ;
            return authenticationInfo ;
        }else{
            return  null ;
        }
    }
}