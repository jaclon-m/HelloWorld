package com.jaclon.mongo.dao;

import com.jaclon.mongo.model.MongoUser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Term;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/6/2 15:32
 */
@Repository
public class UserDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public void save(MongoUser mongoUser){
        mongoTemplate.save(mongoUser);
    }

    public MongoUser findByName(String name){
        Query query = new Query(Criteria.where("name").is(name));
        MongoUser mongoUser = mongoTemplate.findOne(query, MongoUser.class);
        return mongoUser;
    }

    public void updateUser(MongoUser mongoUser){
        Query query = new Query(Criteria.where("id").is(mongoUser.getId()));
        Update update = new Update().set("age", mongoUser.getAge())
                .set("name", mongoUser.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,MongoUser.class);
        //更新查询返回结果集的所有
//        mongoTemplate.updateMulti(query,update,MongoUser.class);
    }

    public void deleteById(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,MongoUser.class);
    }
}
