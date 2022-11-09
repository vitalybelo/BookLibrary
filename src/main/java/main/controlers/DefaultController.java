package main.controlers;

import main.repository.Book;
import main.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private BookRepository bookRepository;

    @Value("${someParameter.value}")
    private String someParameter;

    @RequestMapping("/")
    public String index(Model model) {

        Iterable<Book> bookIter = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        for (Book book : bookIter) {
            books.add(book);
        }

        model.addAttribute("books", books);
        model.addAttribute("booksCount", books.size());
        model.addAttribute("someParameter", someParameter);

        return "index";
    }
}
