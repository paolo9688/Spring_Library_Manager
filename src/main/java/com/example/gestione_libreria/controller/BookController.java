package com.example.gestione_libreria.controller;

import com.example.gestione_libreria.entity.Book;
import com.example.gestione_libreria.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 1. GET /api/books
    // Recupera tutti i libri con possibilità di filtrare per genere e anno
    @GetMapping("/find_book_by_genre_and_year")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String genre,
                                               @RequestParam(required = false) Integer year) {

        List<Book> booksFinded = bookService.findByGenreAndYear(genre, year);

        if(booksFinded == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(booksFinded);
        /*if (genre != null && year != null) {
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.getGenre().equals(genre) && book.getYear().equals(year)) {
                    matchingBooks.add(book);
                }
            }
            return ResponseEntity.ok(matchingBooks);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    // 2. GET /api/books/{id}
    // Recupera un libro specifico per ID
    @GetMapping("/find_book_by_id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        Book bookFinded = bookService.findById(id);

        if(bookFinded == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookFinded);
    }

    // 3. GET /api/books/search
    // Cerca libri per titolo o autore
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author) {

        List<Book> booksFinded = bookService.findByTitleAndAuthor(title, author);

        if(booksFinded == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(booksFinded);

        /*if (title != null && author != null) {
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                    matchingBooks.add(book);
                }
            }
            return ResponseEntity.ok(matchingBooks);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    // 4. GET /api/books/price-range/{minPrice}/{maxPrice}
    // Trova libri in un determinato range di prezzo
    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Book>> getBooksByPriceRange(@PathVariable Double minPrice,
                                                           @PathVariable Double maxPrice) {

        List<Book> booksFinded = bookService.findByPriceRange(minPrice, maxPrice);

        if(booksFinded == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(booksFinded);

        /*if (minPrice != null && maxPrice != null) {
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.getPrice() <= maxPrice && book.getPrice() >= minPrice) {
                    matchingBooks.add(book);
                }
            }
            return ResponseEntity.ok(matchingBooks);
        }
        return ResponseEntity.notFound().build();*/
    }

    // 5. POST /api/books
    // Aggiungi un nuovo libro
    @PostMapping("/add_book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        Book bookToAdd = bookService.addBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookToAdd);
    }

    // 6. GET /api/books
    // Ottenere tutti i libri
    @GetMapping("/select_all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> allBooks = bookService.findAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    // 7. PUT /api/books
    // Aggiornare un libro esistente
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book bookDetails) {
        Book existingBook = null;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                existingBook = book;
                break;
            }
        }

        if (existingBook == null) {
            return ResponseEntity.notFound().build();
        }

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setYear(bookDetails.getYear());
        existingBook.setPrice(bookDetails.getPrice());

        return ResponseEntity.ok(existingBook);
    }

    // 8. DELETE /api/books
    // Eliminare un libro
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Iterator<Book> iterator = books.iterator();
        Book bookToDelete = null;

        while (iterator.hasNext()) {
            Book currentBook = iterator.next();
            if (currentBook.getId().equals(id)) {
                bookToDelete = currentBook;
                iterator.remove();
                break;
            }
        }

        if (bookToDelete == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookToDelete);
    }

    // 9. POST /api/books
    // Aggiungi nuovi libri
    @PostMapping("/add_books")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> myBooks) {
        this.books.addAll(myBooks);
        return ResponseEntity.ok(books);
    }
}
