package kz.orynbay.springcourse.controllers;

import kz.orynbay.springcourse.dao.BookDAO;
import kz.orynbay.springcourse.dao.PersonDAO;
import kz.orynbay.springcourse.models.Person;
import kz.orynbay.springcourse.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }
/// GetMapping method that directs to page that shows all people from database
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    //GetMapping method that directs page to that shows person with id = {id} from database
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.getBooksByAuthor(id));
        return "people/show";
    }
    //GetMapping method that directs to page that shows sample of creation of new person
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return("/people/new");
    }
    // PostMapping method that creates new person and then redirect to main page(/people)
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";

    }
    // GetMapping method that directs to page that shows sample of editing person with id = {id}
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDAO.show(id));
        return "people/edit";
    }
    // PatchMapping method that updated person with id = {id} and then redirects to main page(/people)
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {


        if(bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }
    // DeleteMapping method that deletes person with id = {id} and then redirects to main page
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}