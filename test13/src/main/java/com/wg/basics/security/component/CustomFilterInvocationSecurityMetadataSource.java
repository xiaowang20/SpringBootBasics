//package com.wg.basics.security.component;
//
//import com.wg.basics.entity.Menu;
//import com.wg.basics.entity.Role;
//import com.wg.basics.mapper.MenuMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import javax.annotation.Resource;
//import java.util.Collection;
//import java.util.List;
//
///**
// * 动态配置权限
// * 自定义资源
// * 请求url需要哪些角色
// */
//@Component
//public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
////    final MenuMapper menuMapper;
////    public CustomFilterInvocationSecurityMetadataSource(MenuMapper menuMapper){
////        this.menuMapper=menuMapper;
////    }
//    /**
//     * @Autowired 注解默认优先按照类型去容器中寻找对应的组件.
//     * @Qualifier 按方法名找组件。
//     * @Resource :
//     *          ①如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常。
//     *          ②如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。
//     *          ③如果指定了type，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常
//     *          ④如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。
//     */
//
//         @Resource(name = "menuMapper1")
//         private MenuMapper menuMapper;
//
//    /**
//     * 实例化路径匹配器
//     */
//    AntPathMatcher antPathMatcher =new AntPathMatcher();
//    /**
//     *
//     * @param object
//     * @return Collection<ConfigAttribute>: 存储与安全系统相关的配置属性。此次返回的访问url所需要的角色（权限）
//     * @throws IllegalArgumentException
//     */
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        //FilterInvocation保存与 HTTP 过滤器关联的对象
//        String requestUrl = ((FilterInvocation) object).getRequestUrl();
//        //获取所有资源（url）
//        List<Menu> allMenus = menuMapper.getAllMenus();
//        for (Menu menu : allMenus){
//            /**
//             * 1.如果资源表中的路径与访问的路径一致返回true，否则返回false。
//             * 2.经过路径匹配器返回true,返回角色信息
//             * 3.经过路径匹配器返回false，假设该请求登录后访问。
//             */
//            if (antPathMatcher.match(menu.getPath(),requestUrl)){
//                List<Role> roles = menu.getRoles();
//                String[] roleArr = new String[roles.size()];
//                for (int i = 0; i < roleArr.length; i++) {
//                    roleArr[i] = roles.get(i).getName();
//                }
//                return SecurityConfig.createList(roleArr);
//            }
//
//        }
//        return SecurityConfig.createList("ROLE_LOGIN");
//    }
//
//    /**
//     * 来返回所有定义好的权限资源，
//     * @return
//     */
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    /**
//     * 方法返回类对象是否支持校验。
//     * @param clazz
//     * @return
//     */
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return FilterInvocation.class.isAssignableFrom(clazz);
//    }
//}
