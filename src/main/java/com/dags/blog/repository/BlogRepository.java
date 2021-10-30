package com.dags.blog.repository;

import com.dags.blog.entity.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository  extends CrudRepository<Blog, Integer> {
}
