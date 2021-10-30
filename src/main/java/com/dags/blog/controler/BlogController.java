package com.dags.blog.controler;

import com.dags.blog.entity.Blog;
import com.dags.blog.exception.BlogAlreadyExistsException;
import com.dags.blog.exception.BlogNotFoundException;
import com.dags.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BlogController {
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(value="/blog", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveBlog(@RequestBody Blog blog) throws BlogAlreadyExistsException {
        Blog savedBlog = blogService.saveBlog(blog);
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    }

    @GetMapping("/blogs")
    public ResponseEntity<List> getAllBlogs()throws BlogNotFoundException{
        return new ResponseEntity<List>((List) blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity getBlogById(@PathVariable("id") int id) throws BlogNotFoundException{
        try{
            return new ResponseEntity(blogService.getBlogById(id), HttpStatus.OK);
        }
        catch (BlogNotFoundException blogNotFoundException){
            return new ResponseEntity(blogNotFoundException.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ExceptionHandler(value = BlogAlreadyExistsException.class)
    public ResponseEntity handleBlogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException){
        return new ResponseEntity("Blog already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = BlogNotFoundException.class)
    public ResponseEntity handleBlogNotFoundException(BlogNotFoundException blogNotFoundException){
        return new ResponseEntity("Blog Not Found", HttpStatus.NO_CONTENT);
    }
}
