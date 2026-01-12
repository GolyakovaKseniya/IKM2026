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

/**
 * Контроллер для управления авторами в системе.
 * Обрабатывает HTTP-запросы, связанные с операциями (создание, чтение, обновление, удаление)
 * для сущности {@link Author}.
 *
 * <p>Основные функции контроллера:</p>
 * <ul>
 *   <li>Отображение списка всех авторов</li>
 *   <li>Добавление новых авторов с валидацией данных</li>
 *   <li>Редактирование существующих авторов</li>
 *   <li>Удаление авторов</li>
 * </ul>
 * Controller - класс является контроллером, spring создает экземпляр и регистрирует, методы могут
 * возвращать имена представлений
 * RequestMapping("/authors") - задает корневой URL-путь для методов,все обработчики будут иметь префикс
 * RequiredArgsConstructor - генерирует конструктор final полей, для внедрения зависимостей
 * <p>Все методы контроллера используют шаблоны Thymeleaf для рендеринга HTML-страниц.</p> */

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    /**
     * Обрабатывает GET-запрос для отображения списка всех авторов.
     *
     * <p>Метод получает список всех авторов из сервисного слоя и добавляет его в модель
     * для отображения на HTML-странице.</p>
     * Model: контейнер для передачи данных в представление
     * @param model объект {@link Model}, используемый для передачи данных в представление
     * @return имя шаблона Thymeleaf для отображения списка авторов ("author/list")
     */

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/list";
    }

    /**
     * Отображает форму для добавления нового автора.
     *
     * <p>Метод создает новый экземпляр {@link Author} и добавляет его в модель,
     * чтобы форма могла быть корректно заполнена и отправлена.</p>
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя шаблона Thymeleaf для формы добавления автора ("author/add")
     */

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    /**
     * Обрабатывает POST-запрос для сохранения нового автора.
     *
     * <p>Метод выполняет следующие действия:</p>
     * <ol>
     *   <li>Проверяет данные автора на соответствие ограничениям валидации</li>
     *   <li>При наличии ошибок возвращает форму для их исправления</li>
     *   <li>При успешной валидации сохраняет автора через сервис</li>
     *   <li>Обрабатывает исключения нарушения целостности данных (дублирование имени)</li>
     * </ol>
     * ModelAttribute - связывание данных,spring берет параметры из запроса и заполняет поля автора
     * @param author объект {@link Author}, заполненный данными из формы
     * @param bindingResult объект {@link BindingResult}, содержащий результаты валидации
     * @param model объект {@link Model} для передачи данных в представление
     * @return перенаправление на список авторов при успешном сохранении или возврат формы при ошибках
     */

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

    /**
     * Отображает форму для редактирования существующего автора.
     *
     * <p>Метод находит автора по его идентификатору и добавляет его в модель,
     * чтобы форма редактирования могла быть предзаполнена текущими данными.</p>
     * PathVariable - извлекает значение из сегмента URL-пути
     * @param id идентификатор редактируемого автора
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя шаблона Thymeleaf для формы редактирования автора ("author/edit")
     */

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author/edit";
    }

    /**
     * Обрабатывает POST-запрос для обновления данных автора.
     *
     * <p>Метод выполняет следующие действия:</p>
     * <ol>
     *   <li>Проверяет обновленные данные автора на соответствие ограничениям валидации</li>
     *   <li>При наличии ошибок возвращает форму для их исправления</li>
     *   <li>Устанавливает идентификатор автора из URL-пути</li>
     *   <li>Сохраняет обновленного автора через сервис</li>
     *   <li>Обрабатывает исключения нарушения целостности данных (дублирование имени)</li>
     * </ol>
     *
     * @param id идентификатор редактируемого автора из URL-пути
     * @param author объект {@link Author} с обновленными данными из формы
     * @param bindingResult объект {@link BindingResult}, содержащий результаты валидации
     * @param model объект {@link Model} для передачи данных в представление
     * @return перенаправление на список авторов при успешном обновлении или возврат формы при ошибках
     */

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

    /**
     * Обрабатывает GET-запрос для удаления автора по его идентификатору.
     *
     * <p>Метод вызывает сервис для удаления автора с указанным ID,
     * после чего перенаправляет пользователя на страницу со списком всех авторов.</p>
     *
     * @param id идентификатор автора, которого необходимо удалить
     * @return перенаправление на список авторов после удаления
     */

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
