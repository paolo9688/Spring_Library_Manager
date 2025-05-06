package com.example.gestione_libreria.service;

import com.example.gestione_libreria.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(1L, "libro1", "autore1", "genere1", 2000, 5.0),
            new Book(2L, "libro2", "autore2", "genere2", 2001, 6.0),
            new Book(3L, "libro3", "autore3", "genere3", 2002, 7.0),
            new Book(4L, "libro4", "autore4", "genere4", 2003, 8.0),
            new Book(5L, "libro5", "autore5", "genere5", 2004, 9.0)
    ));

    // Metodo per la ricerca del libro da id:
    public Book findById(Long id) {
        Book bookFind = null;

        for (Book book : books) {
            if (book.getId().equals(id)) {
                bookFind = book;
            }
        }

        return bookFind;
    }

    // Metodo per la ricerca dei libri filtrando per genere e anno:
    public List<Book> findByGenreAndYear(String genre, Integer year) {
        List<Book> matchingBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getGenre().equals(genre) && book.getYear().equals(year)) {
                matchingBooks.add(book);
            }
        }

        return matchingBooks;
    }

    // Metodo per la ricerca dei libri filtrando per titolo e autore:
    public List<Book> findByTitleAndAuthor(String title, String author) {
        List<Book> matchingBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                matchingBooks.add(book);
            }
        }

        return matchingBooks;
    }

    // Metodo per la ricerca di libri all'interno di un determinato range di prezzo:
    public List<Book> findByPriceRange(Double minPrice, Double maxPrice) {
        List<Book> matchingBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getPrice() <= maxPrice && book.getPrice() >= minPrice) {
                matchingBooks.add(book);
            }
        }

        return matchingBooks;
    }

    // Metodo per aggiungere un libro al database:
    public Book addBook(Book book) {
        Book bookToAdd = book;
        this.books.add(book);
        return bookToAdd;
    }

    // Metodo per la ricerca di tutti i libri:
    public List<Book> findAllBooks() {
        List<Book> matchingBooks = this.books;
        return matchingBooks;
    }

    // Metodo per aggiornare un libro:
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = null;

        for (Book book : books) {
            if (book.getId().equals(id)) {
                existingBook = book;
                break;
            }
        }

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setYear(bookDetails.getYear());
        existingBook.setPrice(bookDetails.getPrice());

        return existingBook;
    }

    // Metodo per eliminare un libro dal database da id:
    public Book deleteBook(Long id) {
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

        return bookToDelete;
    }

    // Metodo per aggiungere una lista di libri:
    public List<Book> addBooks(List<Book> newBooks) {
        List<Book> booksToAdd = newBooks;
        this.books.addAll(booksToAdd);
        return this.books;
    }
}