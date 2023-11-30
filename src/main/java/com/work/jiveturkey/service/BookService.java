package com.work.jiveturkey.service;

import com.work.jiveturkey.model.Book;
import com.work.jiveturkey.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByName(String name) {
        return bookRepository.findBooksByName(name);
    }

    public List<Book> getBooksBySku(String sku) {
        return  bookRepository.findBooksBySku(sku);
    }

    public void updateBook(Book book, Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if(existingBook.isPresent()) {
            existingBook.get().setName(book.getName());
            existingBook.get().setDescription(book.getDescription());
            existingBook.get().setSku(book.getSku());
            existingBook.get().setImage(book.getImage());
            existingBook.get().setPrice(book.getPrice());
            existingBook.get().setStock(book.getStock());
            existingBook.get().setCategory(book.getCategory());
            bookRepository.save(existingBook.get());
        } else {
            throw new NoSuchElementException("Book with id " + id + " not found");
        }
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> getAllBooksByCategory(Long categoryId) {
        return bookRepository.findBooksByCategoryId(categoryId);
    }
 }
