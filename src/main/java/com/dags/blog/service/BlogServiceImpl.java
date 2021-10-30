package com.dags.blog.service;

import com.dags.blog.entity.Blog;
import com.dags.blog.exception.BlogAlreadyExistsException;
import com.dags.blog.exception.BlogNotFoundException;
import com.dags.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog saveBlog(Blog blog){
        if(blogRepository.existsById(blog.getBlogId())){
             throw new BlogAlreadyExistsException();
        }
        Blog saveBlog = blogRepository.save(blog);
        return saveBlog;
    }

    @Override
    public List getAllBlogs(){
        return (List) blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(int id) {
        Blog blog;
        if(blogRepository.findById(id).isEmpty()){
            throw new BlogNotFoundException();
        } else{
            blog = blogRepository.findById(id).get();
        }
        return blog;
    }
}
