package main;

import main.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.model.Book;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books/")
    public List<Book> list() {
        Iterable<Book> bookIter = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        for (Book book : bookIter) {
            books.add(book);
        }
        return books;
    }
    @GetMapping("/books/{id}")
    public ResponseEntity get(@PathVariable int id) {

        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return new ResponseEntity(optionalBook.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data record not found");
    }

    @PostMapping("/books/")
    public int add(Book book) {
        Book b = bookRepository.save(book);
        return b.getId();
    }

    @DeleteMapping("/books/{id}")
    public int delete (@PathVariable int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.delete(optionalBook.get());
            return id;
        }
        return -1;
    }

    @DeleteMapping("/books/")
    public void deleteAll () {
        bookRepository.deleteAll();
    }

    @PutMapping("/books/")
    public int edit(Book book) {
        int id = book.getId();
        if (id <= 0) return -1;     // ?id=? не был передан в запросе

        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book b = optionalBook.get();
            if (book.getName() == null) {
                book.setName(b.getName());  // &name= название не было передано
            }
            if (book.getYear() == 0) {
                book.setYear(b.getYear());  // &year= год не был переан в запросе
            }
            bookRepository.save(book);
            return id;
        }
        return -1;
    }


}
