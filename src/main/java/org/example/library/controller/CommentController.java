package org.example.library.controller;
import org.example.library.model.Comment;
import org.example.library.service.BookService;
import org.example.library.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BookService bookService;

    @GetMapping
    public String listComments(Model model) {
        model.addAttribute("comments", commentService.findAll());
        return "comment/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("books", bookService.findAll());
        return "comment/add";
    }

    @PostMapping("/add")
    public String addComment(@Valid @ModelAttribute Comment comment, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("books", bookService.findAll());
            return "comment/add";
        }
        commentService.save(comment);
        return "redirect:/comments";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("comment", commentService.findById(id));
        model.addAttribute("books", bookService.findAll());
        return "comment/edit";
    }

    @PostMapping("/edit/{id}")
    public String editComment(@PathVariable Long id,@Valid @ModelAttribute Comment comment,BindingResult bindingResult, Model model ) {
        if(bindingResult.hasErrors()){
            model.addAttribute("books", bookService.findAll());
            return "comment/edit";
        }
        comment.setId(id);
        commentService.save(comment);
        return "redirect:/comments";
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return "redirect:/comments";
    }
}
