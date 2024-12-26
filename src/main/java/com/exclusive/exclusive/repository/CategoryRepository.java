package com.exclusive.exclusive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exclusive.exclusive.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
