package com.sz.wechat.utils;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 批量工具
 * @author sway
 *
 */
public class SqlSessionFactoryUtil {

	private static SqlSessionFactory sqlSessionFactory;  
	     
	    public static SqlSessionFactory getSqlSessionFactory(){  
	        if(sqlSessionFactory==null){  
	            InputStream inputStream=null;  
	            try{  
	                inputStream=Resources.getResourceAsStream("spring-mybatis.xml");  
	                sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);  
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }  
	        }  
	        return sqlSessionFactory;  
	    }  
	      
	    public static SqlSession openSession(){  
	        return getSqlSessionFactory().openSession();  
	    }  
}
