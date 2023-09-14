package com.jaclon.helloworld.elasticsearch.repository;

import com.jaclon.helloworld.elasticsearch.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/9/14 15:52
 */
public interface BlogRepository extends ElasticsearchRepository<Blog,Long> {
}
