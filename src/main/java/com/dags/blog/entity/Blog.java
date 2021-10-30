package com.dags.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    private int blogId;
    private String blogTitle;
    private String blogCreator;
    private int yearOfPost;
}
