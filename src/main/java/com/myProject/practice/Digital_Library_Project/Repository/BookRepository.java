package com.myProject.practice.Digital_Library_Project.Repository;

import com.myProject.practice.Digital_Library_Project.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
//    @Query("Select b From Book b where b.isAvailable =:isAvailable and b.Author in (Select a from Author where a.Name =:author")
//    List<Book> finBookByAuthor(@Param("author") String author, @Param("isAvailable") Boolean isAvailable);
//    @Query("Select b From Book b where b.isAvailable =:isAvailable")
//    List<Book>findBooksByAvailability(@Param("isAvailable") Boolean isAvailable);

//    @Modifying
//    @Query("UPDATE Book b SET b.name = :#{#newBook.name}, b.number_Of_Pages = :#{#newBook.numberOfPages},b.language = :#{#newBook.language}, b.isAvailable = :#{#newBook.isAvailable}, b.genre = :#{#newBook.genre}, b.ISBN = :#{#newBook.isbn}, b.published_Date = :#{#newBook.publishedDate}, b.updatedOn = CURRENT_TIMESTAMP WHERE b.id = :#{#newBook.bookId}")
//    void updateBook(@Param("newBook") Book newBook);


}
