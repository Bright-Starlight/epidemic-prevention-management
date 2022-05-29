package com.parachute.main.config;
import com.parachute.main.realm.JdbcRealm;
import java.util.ArrayList;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置
 *
 * @author machi
 * @date 2022/04/25
 */
@Configuration
public class ShiroConfig {


    /**
     * shiro过滤器工厂bean
     *
     * @param manager 经理
     * @return {@link ShiroFilterFactoryBean}
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("manager") SecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        factoryBean.setLoginUrl("");
        factoryBean.setSuccessUrl("");
        factoryBean.setUnauthorizedUrl("");
        factoryBean.setFilters(new HashMap<>(3));
        factoryBean.setFilterChainDefinitionMap(new HashMap<>(3));
        factoryBean.setFilterChainDefinitions("");
        factoryBean.setGlobalFilters(new ArrayList<>());
        factoryBean.setSecurityManager(manager);
        Map<String,String> map = new HashMap<>(16);
        factoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面
        factoryBean.setLoginUrl("/login");
        //未授权页面
        factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
    }


    /**
     * shiro管理
     *
     * @param jdbcRealm jdbc域
     * @return {@link DefaultWebSecurityManager}
     */
    @Bean
    public DefaultWebSecurityManager manager(@Qualifier("jdbcRealm") JdbcRealm jdbcRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(jdbcRealm);
        return manager;
    }

    /**
     * jdbc域
     *
     * @return {@link JdbcRealm}
     */
    @Bean
    public JdbcRealm jdbcRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setCredentialsMatcher(matcher);
        return jdbcRealm;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }
    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("manager")DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}