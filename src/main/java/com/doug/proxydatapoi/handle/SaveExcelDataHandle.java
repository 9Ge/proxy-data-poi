package com.doug.proxydatapoi.handle;

import com.doug.proxydatapoi.service.BusinessDataService;
import com.doug.proxydatapoi.util.SpringContextUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2021/9/7 16:07<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Component
@Slf4j
@SuppressWarnings({"all"})
public class SaveExcelDataHandle {
    @Autowired
    private BusinessDataService businessDataService;

    /**
     * 获取session
     * @return
     */
    public SqlSession  getSqlSession(){
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextUtil.getBean("sqlSessionFactory");
        return  sqlSessionFactory.openSession(ExecutorType.BATCH);
    }

    /**
     * 获取mapper代理
     * @param interfaceImpl
     * @param sqlSession
     * @return
     */
    public Object getMapper(Class interfaceImpl, SqlSession sqlSession){
        return Proxy.newProxyInstance(
                interfaceImpl.getClassLoader(),
                new Class[]{interfaceImpl},
                new MapperProxy(sqlSession.getMapper(interfaceImpl))
        );
    }


    /**
     * 保存Excel的数据
     * @param mapperClazz
     * @param saveList
     * @return
     */
    public String saveExcelData(@NonNull Class mapperClazz, List<Object> saveList) {
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            Object  instance = getMapper(mapperClazz,sqlSession);
            Method method = instance.getClass().getMethod("save", Object.class);
            for (Object object:saveList) {
                Class<?> aClass = object.getClass();
                businessDataService.saveBusiness(object,aClass.getDeclaredFields());
                try {
                    method.invoke(instance,object);
                } catch (Exception e) {
                   continue;
                }
            }
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            log.info("{}",e);
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }

        return "ok";
    }

    /**
     * 查询导出的Excel数据
     * @param mapperClazz
     * @param beanClazz
     * @return
     */
    public List queryExcelData(Class mapperClazz,Class beanClazz){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            Object  instance = getMapper(mapperClazz,sqlSession);
            Method method = instance.getClass().getMethod("selectByExample", Object.class);

            Example example = new Example(beanClazz);
            Example.Criteria criteria = example.createCriteria();
            List<Object> objectList = (List<Object>) method.invoke(instance, example);
            for (Object object:objectList) {
                Class<?> clazz = object.getClass();
                businessDataService.queryBusiness(object,clazz.getDeclaredFields());
            }
            return  objectList;
        } catch (Exception e) {
            log.info("{}",e);
        }
        return new ArrayList();
    }

}
