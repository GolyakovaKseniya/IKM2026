package org.example.library.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, представляющая автора в системе
 * Автор может быть связан с одной или несколькими книгами
 *
 * Entity - класс сущность, будет сохраняться в БД
 * Data - генерирует геттеры,сеттеры, конструктор
 */

@Entity
@Table(name = "authors")
@Data
public class Author {

    /**
     * Уникальный идентификатор автора.
     * Id - первичный ключ таблицы
     * БД сама генерирует его
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Имя автора. Должно быть уникальным в системе.
     * NotBlank - не null и не пробелы (на уровне приложения)
     * Column - свойства столбца (на уровне БД)
     */
    @NotBlank(message = "Имя автора не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s\\-]+$",
            message = "Имя автора может содержать только буквы, пробелы и дефисы")
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Список книг, написанных автором.
     * Используется связь "один ко многим".
     * Книги загружаются только при обращении к ним
     */

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<Book> books = new ArrayList<>();
}
