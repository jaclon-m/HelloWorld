package com.jaclon.helloworld.elasticsearch.controller;

import com.jaclon.helloworld.elasticsearch.bean.Blog;
import com.jaclon.helloworld.elasticsearch.repository.BlogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/9/14 15:54
 */
@RequestMapping("/crud")
@RestController
@Api(tags = "增删改查（文档）")
public class CrudController {
    @Resource
    private BlogRepository blogRepository;

    @ApiOperation("添加单个文档")
    @PostMapping("/addDocument")
    public Blog addDocument() {
        Long id = 1L;
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setTitle("Spring Data ElasticSearch学习教程" + id);
        blog.setContent("这是添加单个文档的实例" + id);
        blog.setAuthor("Tony");
        blog.setCategory("ElasticSearch");
        blog.setCreateTime(new Date());
        blog.setStatus(1);
        blog.setSerialNum(id.toString());

        return blogRepository.save(blog);
    }

    @ApiOperation("添加多个文档")
    @PostMapping("/addDocuments")
    public Object addDocuments(Integer count) {
        List<Blog> blogs = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Long id = (long) i;
            Blog blog = new Blog();
            blog.setBlogId(id);
            blog.setTitle("Spring Data ElasticSearch学习教程" + id);
            blog.setContent("这是添加多个文档的实例" + id);
            blog.setAuthor("Tony");
            blog.setCategory("ElasticSearch");
            blog.setCreateTime(new Date());
            blog.setStatus(1);
            blog.setSerialNum(id.toString());
            blogs.add(blog);
        }

        return blogRepository.saveAll(blogs);
    }

    @ApiOperation("修改单个文档")
    @PostMapping("/editDocument")
    public Blog editDocument() {
        Long id = 1L;
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setTitle("Spring Data ElasticSearch学习教程" + id);
        blog.setContent("这是修改单个文档的实例" + id);
        // blog.setAuthor("Tony");
        // blog.setCategory("ElasticSearch");
        // blog.setCreateTime(new Date());
        // blog.setStatus(1);
        // blog.setSerialNum(id.toString());

        return blogRepository.save(blog);
    }

    @ApiOperation("查找单个文档")
    @GetMapping("/findById")
    public Blog findById(Long id) {
        return blogRepository.findById(id).get();
    }

    @ApiOperation("删除单个文档")
    @PostMapping("deleteDocument")
    public String deleteDocument(Long id) {
        blogRepository.deleteById(id);
        return "success";
    }

    @ApiOperation("删除所有文档")
    @PostMapping("/deleteDocumentAll")
    public String deleteDocumentAll() {
        blogRepository.deleteAll();
        return "success";
    }
}
