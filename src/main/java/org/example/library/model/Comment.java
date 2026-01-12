package org.example.library.model;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Сущность, представляющая комментарий/отзыв к книге.
 * Каждый комментарий связан с одной книгой и одним читателем.
 */
@Entity
@Table(name = "comments")
@Data
public class Comment {

    /**
     * Уникальный идентификатор комментария.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Имя читателя, оставившего комментарий.
     */
    @NotBlank(message = "Имя читателя не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя читателя должно быть от 2 до 100 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s\\-]+$",
            message = "Имя читателя может содержать только буквы, пробелы и дефисы")
    @Column(name = "reader", nullable = false)
    private String reader;


    /**
     * Оценка книги от 1 до 5.
     */
    @NotNull(message = "Оценка не может быть пустой")
    @Min(value = 1, message = "Оценка не может быть меньше 1")
    @Max(value = 5, message = "Оценка не может быть больше 5")
    @Column(nullable = false)
    private Integer rating;


    /**
     * Книга, к которой оставлен комментарий.
     */
    @NotNull(message = "Книга должна быть указана")
    @ManyToOne
    @JoinColumn(name = "books_id", nullable = false)
    private Book book;
}
