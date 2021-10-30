package com.dags.blog.service;

import com.dags.blog.entity.Blog;
import com.dags.blog.exception.BlogAlreadyExistsException;
import com.dags.blog.exception.BlogNotFoundException;

import java.util.List;

public interface BlogService {

    Blog saveBlog(Blog blog) throws BlogAlreadyExistsException;
    List getAllBlogs() throws BlogNotFoundException;
    Blog getBlogById(int id) throws BlogNotFoundException;
}
