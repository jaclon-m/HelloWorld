/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */

import com.jaclon.mybatisanalyse.bean.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * @Classname MybatisTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/12/10
 */
public class MybatisTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 查询数据库内容
            sqlSession = sqlSessionFactory.openSession();
            Blog blog = sqlSession.selectOne("com.jaclon.mybatisanalyse.bean.Blog.selectBlog",1);
            System.out.println(blog);

            // 插入数据库内容
            Blog b = new Blog();
            b.setTitle("Insert Value" + new Random().nextInt(1000));
            int row = sqlSession.insert("com.jaclon.mybatisanalyse.bean.Blog.insertBlog",b);
            System.out.println(row);
            sqlSession.commit();

            // 更新数据库内容
            b.setId(2);
            row = sqlSession.update("com.jaclon.mybatisanalyse.bean.Blog.updateBlog",b);
            System.out.println(row);
            sqlSession.commit();


            //删除数据库内容
            row = sqlSession.delete("com.jaclon.mybatisanalyse.bean.Blog.deleteBlog",1);
            System.out.println(row);
            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
