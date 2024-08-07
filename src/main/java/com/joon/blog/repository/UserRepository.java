package com.joon.blog.repository;

import com.joon.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// Bean 등록은 할 필요 없다. JpaRepository가 Bean 등록되어 있기 대문
public interface UserRepository extends JpaRepository<User, Integer> {

}
