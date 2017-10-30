package com.zcc.repository;

import org.springframework.stereotype.Repository;

import com.zcc.dao.User;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Repository
public interface UserRepository {
	User findUserByUsername(String username);
}
