package com.myProject.practice.Digital_Library_Project.Services;

import com.myProject.practice.Digital_Library_Project.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BookCacheService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private String Prefix = "BOOK::";

    public void setBookInCache(Book book) {
        String Key = Prefix + book.getName();
        redisTemplate.opsForValue().set(Key, book, 120, TimeUnit.SECONDS);
    }

    public Book getBookFromCache(String bookName) {
        String Key = Prefix + bookName;
        return (Book) redisTemplate.opsForValue().get(Key);
    }
}
