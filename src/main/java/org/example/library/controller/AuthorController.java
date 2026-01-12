package org.example.library.controller;
import org.example.library.model.Author;
import jakarta.validation.Valid;
import org.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid @ModelAttribute Author author, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "author/add";
        }
        try{
            authorService.save(author);
            return "redirect:/authors";
        }catch (DataIntegrityViolationException e){
            model.addAttribute("error", "Автор с именем '" + author.getName() + "' уже существует!");
            return "author/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author/edit";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id,@Valid @ModelAttribute Author author,BindingResult bindingResult, Model model ) {
        if(bindingResult.hasErrors()){
            return  "author/edit";
        }
        try{
            author.setId(id);
            authorService.save(author);
            return "redirect:/authors";
        }catch (DataIntegrityViolationException e){
            model.addAttribute("error", "Автор с именем '" + author.getName() + "' уже существует!");
            return "author/edit";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
