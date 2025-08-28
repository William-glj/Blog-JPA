package com.blogJPA.localtest.repository;

import com.blogJPA.localtest.entity.BlogClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<BlogClass,Integer> {

}
