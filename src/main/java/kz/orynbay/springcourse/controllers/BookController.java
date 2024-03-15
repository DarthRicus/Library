package kz.orynbay.springcourse.controllers;

import kz.orynbay.springcourse.dao.BookDAO;
import kz.orynbay.springcourse.dao.PersonDAO;
import kz.orynbay.springcourse.models.Book;
import kz.orynbay.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    /// GetMapping method that directs to page that shows all books from database
    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    //GetMapping method that directs page to that shows book with id = {id} from database
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,@ModelAttribute("personToAppoint") Person personToAppoint){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("person", personDAO.show(bookDAO.show(id).getPersonId()));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }
    //GetMapping method that directs to page that shows sample of creation of new book
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book){
        return("/books/new");
    }
    // PostMapping method that creates new book and then redirect to main page(/books)
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";

    }
    // GetMapping method that directs to page that shows sample of editing book with id = {id}
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }
    // PatchMapping method that updates book with id = {id} and then redirects to main page(/books)
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {


        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(book, id);
        return "redirect:/books";
    }
    // DeleteMapping method that deletes person with id = {id} and then redirects to main page
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    // PatchMapping method that frees book with id = {id} from Person
    @PatchMapping("/{id}/freeFromPerson")
    public String update(@PathVariable("id") int id) {

        bookDAO.freeFromPerson(id);
        return "redirect:/books";
    }
    //PostMapping method to appoint a person to a book
    @PostMapping("/{id}/appointPerson")
    public String appointPerson(@PathVariable("id") int id, @ModelAttribute("personToAppoint")Person person){
        bookDAO.setPersonId(id, person.getPersonId());
        return "redirect:/books";
    }
}
