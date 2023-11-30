package com.work.jiveturkey.controller;

import com.work.jiveturkey.model.Book;
import com.work.jiveturkey.service.BookService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        LOGGER.info("Creating book: {}", book);
        ResponseEntity<?> response = new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
        LOGGER.info("Book created successfully");
        return response;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        LOGGER.info("Fetching book by ID: {}", bookId);
        Optional<Book> book = bookService.getBookById(bookId);
        ResponseEntity<?> response;
        if(book.isPresent()) {
            response = new ResponseEntity<>(book, HttpStatus.OK);
            LOGGER.info("Book found: {}", book.get());
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LOGGER.warn("No book found for ID: {}", bookId);
        }
        return response;
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long bookId) {
        LOGGER.info("Deleting book by ID: {}", bookId);
        bookService.deleteBookById(bookId);
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        LOGGER.info("Book deleted successfully");
        return response;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getAllBooksByCategoryId(@PathVariable Long categoryId) {
        LOGGER.info("Fetching books by category ID: {}", categoryId);
        List<Book> books = bookService.getAllBooksByCategory(categoryId);
        if(books.isEmpty()) {
            LOGGER.error("No books exist with a category id of: " + categoryId);
            throw new NoSuchElementException();
        }
        ResponseEntity<?> response = new ResponseEntity<>(bookService.getAllBooksByCategory(categoryId), HttpStatus.OK);
        LOGGER.info("Books fetched successfully");
        return response;
    }
}

