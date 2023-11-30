package com.work.jiveturkey.repository;

import com.work.jiveturkey.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByName(String name);

    List<Book> findBooksBySku(String sku);

    @Query(value = "SELECT * FROM book b WHERE b.category_id = :categoryId", nativeQuery = true)
    List<Book> findBooksByCategoryId(Long categoryId);

}
