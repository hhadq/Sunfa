package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest01 {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void  init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @Test
    public void testFindAll() {
        //5.执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindById(){
        //5.执行查询所有方法
        List<User> users = userDao.findById(41);
        for(User user : users){
            System.out.println(user);
        }
    }
    @Test
    public void testinsert(){
        User user=new User();
        user.setSex("男");
        user.setUsername("陈涛");
        user.setBirthday(new Date());
        user.setAddress("厦门市思明区");
        userDao.saveUser(user);
        sqlSession.commit();
    }
    @Test
    public void testupdate(){
        User user=new User();
        user.setId(49);
        user.setSex("男");
        user.setUsername("陈涛");
        user.setBirthday(new Date());
        user.setAddress("厦门市思明区");
        userDao.updateUser(user);
        sqlSession.commit();
    }
    @Test
    public void testdelete(){
        userDao.deleteUser(50);
        sqlSession.commit();
    }
}

