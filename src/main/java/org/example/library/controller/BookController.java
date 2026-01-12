package org.example.library.controller;
import org.example.library.model.Book;
import org.example.library.service.AuthorService;
import org.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

/**
 * Контроллер для управления книгами в системе.
 * Обрабатывает HTTP-запросы для операций CRUD с сущностью {@link Book}.
 *
 * <p>Все методы используют шаблоны Thymeleaf для рендеринга HTML-страниц.</p>
 */

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    /**
     * Обрабатывает GET-запрос для отображения списка всех книг.
     *
     * @param model объект Spring Model для передачи данных в представление
     * @return имя шаблона Thymeleaf для отображения списка книг
     */

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/list";
    }

    /**
     * Отображает форму для добавления новой книги.
     *
     * @param model объект Model для передачи данных в представление
     * @return имя шаблона Thymeleaf для формы добавления книги
     */

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "book/add";
    }

    /**
     * Обрабатывает отправку формы добавления новой книги.
     *
     * @param book объект Book, заполненный данными из формы
     * @param bindingResult объект с результатами валидации
     * @param model объект Model для передачи данных в представление
     * @return перенаправление на список книг или возврат формы с ошибками
     */

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("authors", authorService.findAll());
            return "book/add";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    /**
     * Отображает форму для редактирования существующей книги.
     *
     * @param id идентификатор редактируемой книги
     * @param model объект Model для передачи данных в представление
     * @return имя шаблона Thymeleaf для формы редактирования книги
     */

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book/edit";
    }

    /**
     * Обрабатывает отправку формы редактирования книги.
     *
     * @param id идентификатор редактируемой книги
     * @param book объект Book с обновленными данными из формы
     * @param bindingResult объект с результатами валидации
     * @param model объект Model для передачи данных в представление
     * @return перенаправление на список книг или возврат формы с ошибками
     */

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,@Valid @ModelAttribute Book book, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("authors", authorService.findAll());
            return "book/edit";
        }
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    /**
     * Удаляет книгу по идентификатору.
     *
     * @param id идентификатор удаляемой книги
     * @return перенаправление на список книг
     */

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
