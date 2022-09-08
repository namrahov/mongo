package com.example.demo.dao.repository;

import com.example.demo.model.LightStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LightStudentRedisRepo {

    public static final String HASH_KEY = "LightStudent";
    @Autowired
    private RedisTemplate template;

    public LightStudent save(LightStudent lightStudent) {
        template.opsForHash().put(HASH_KEY, lightStudent.getId(), lightStudent);
        return lightStudent;
    }

    public List<LightStudent> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public LightStudent findProductById(int id) {
        return (LightStudent) template.opsForHash().get(HASH_KEY, id);
    }


    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product removed !!";
    }
}
