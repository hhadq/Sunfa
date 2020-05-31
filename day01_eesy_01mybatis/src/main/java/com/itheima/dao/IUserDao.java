package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/*
  用户的持久层接口
   */
public interface IUserDao {
      List<User> findAll();
      List<User> findById(int i);
      void saveUser (User user);
      void updateUser(User user);
      void deleteUser(int id);
}
