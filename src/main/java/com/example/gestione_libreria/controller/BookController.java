package com.example.gestione_libreria.controller;

import com.example.gestione_libreria.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(1L, "libro1", "autore1", "genere1", 2000, 5.0),
            new Book(2L, "libro2", "autore2", "genere2", 2001, 6.0),
            new Book(3L, "libro3", "autore3", "genere3", 2002, 7.0),
            new Book(4L, "libro4", "autore4", "genere4", 2003, 8.0),
            new Book(5L, "libro5", "autore5", "genere5", 2004, 9.0)
            ));

    // 1. GET /api/books
    // Recupera tutti i libri con possibilità di filtrare per genere e anno
    @GetMapping("/find_book_by_genre_and_year")
    public List<Book> getBooks(@RequestParam(required = false) String genre,
                               @RequestParam(required = false) Integer year) {

        if (genre != null && year != null) {
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.getGenre().equals(genre) && book.getYear().equals(year)) {
                    matchingBooks.add(book);
                }
            }
            return matchingBooks;
        } else {
            return new ArrayList<>();
        }
    }

    // 2. GET /api/books/{id}
    // Recupera un libro specifico per ID
    @GetMapping("/find_book_by_id/{id}")
    public Book getBookById(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return new Book();
    }

    // 3. GET /api/books/search
    // Cerca libri per titolo o autore
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {

        if (title != null && author != null) {
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                    matchingBooks.add(book);
                }
            }
            return matchingBooks;
        } else {
            return new ArrayList<>();
        }
    }

    // 4. GET /api/books/price-range/{minPrice}/{maxPrice}
    // Trova libri in un determinato range di prezzo
    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public List<Book> getBooksByPriceRange(@PathVariable double minPrice,
                                           @PathVariable double maxPrice) {

        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() <= maxPrice && book.getPrice() >= minPrice) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
}
