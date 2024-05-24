package com.jaclon.helloworld.elasticsearch.repository;

import com.jaclon.helloworld.elasticsearch.bean.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/9/14 09:32
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Long> {

    List<Product> findByName(String name);
}

