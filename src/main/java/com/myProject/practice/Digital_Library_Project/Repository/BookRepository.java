package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Entity.Author;
import com.myProject.practice.Digital_Library_Project.Entity.Book;
import com.myProject.practice.Digital_Library_Project.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("select b From Book b WHERE b.name =:name")
    public Book getBookByName(@Param("name") String name);


    @Transactional
    @Modifying
    @Query("update  Book b set b.student = ?2 where b.id = ?1")
    void updateBook(Integer bookId, Student student);


    @Query("select b From Book b WHERE (:name IS NULL OR b.name = :name) AND (:genre IS NULL OR b.genre = :genre) AND (:language IS NULL OR b.language = :language) AND (:author IS NULL OR b.author = :author)")
    List<Book> findBooksByFilters(@Param("name") String name,@Param("genre") String genre,@Param("language") String language, @Param("author") Author author);
}
