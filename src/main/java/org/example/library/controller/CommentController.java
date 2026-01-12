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

/**
 * Контроллер для управления комментариями в системе.
 * Обрабатывает HTTP-запросы для операций CRUD с сущностью {@link Comment}.
 */
@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BookService bookService;

    /**
     * Отображает список всех комментариев.
     *
     * @param model объект для передачи данных в представление
     * @return имя шаблона списка комментариев
     */

    @GetMapping
    public String listComments(Model model) {
        model.addAttribute("comments", commentService.findAll());
        return "comment/list";
    }

    /**
     * Отображает форму для добавления нового комментария.
     *
     * @param model объект для передачи данных в представление
     * @return имя шаблона формы добавления
     */

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("books", bookService.findAll());
        return "comment/add";
    }

    /**
     * Обрабатывает отправку формы добавления комментария.
     *
     * @param comment объект Comment с данными из формы
     * @param bindingResult результаты валидации
     * @param model объект для передачи данных в представление
     * @return перенаправление на список или возврат формы с ошибками
     */

    @PostMapping("/add")
    public String addComment(@Valid @ModelAttribute Comment comment, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("books", bookService.findAll());
            return "comment/add";
        }
        commentService.save(comment);
        return "redirect:/comments";
    }

    /**
     * Отображает форму для редактирования существующего комментария.
     *
     * @param id ID комментария для редактирования
     * @param model объект для передачи данных в представление
     * @return имя шаблона формы редактирования
     */

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("comment", commentService.findById(id));
        model.addAttribute("books", bookService.findAll());
        return "comment/edit";
    }

    /**
     * Обрабатывает отправку формы редактирования комментария.
     *
     * @param id ID редактируемого комментария
     * @param comment объект Comment с обновленными данными
     * @param bindingResult результаты валидации
     * @param model объект для передачи данных в представление
     * @return перенаправление на список или возврат формы с ошибками
     */

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

    /**
     * Удаляет комментарий по ID.
     *
     * @param id ID комментария для удаления
     * @return перенаправление на список комментариев
     */

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return "redirect:/comments";
    }
}
