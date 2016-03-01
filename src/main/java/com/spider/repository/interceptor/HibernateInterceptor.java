package com.spider.repository.interceptor;//package com.spider.repository.interceptor;
//
//import java.io.Serializable;
//import java.lang.reflect.Field;
//import java.util.Date;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.EmptyInterceptor;
//import org.hibernate.type.Type;
//
//public class HibernateInterceptor extends EmptyInterceptor {
//
//    private static final long serialVersionUID = 943016542661445325L;
//
//    public static ThreadLocal<String> local = new ThreadLocal<String>();
//
//    @Override
//    public synchronized boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//
////        for (String propertyName : propertyNames) {
////            if (StringUtils.endsWithIgnoreCase(propertyName, "updateTime")) {
////                Field updateTimeField;
////                try {
////                    updateTimeField = entity.getClass().getDeclaredField(propertyName);
////                    updateTimeField.setAccessible(true);
////                    updateTimeField.set(entity, new Date());
////                } catch (Exception e) {
////                    // ignored
////                }
////            }
////        }
////        return false;
//        return super.onSave(entity, id, state, propertyNames, types);
//    }
//}
