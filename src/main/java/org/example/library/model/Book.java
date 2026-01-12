package org.example.library.model;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;
@Entity
@Table(name = "books")
@Data

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название книги не может быть пустым")
    @Size(min = 1, max = 200, message = "Название книги должно быть от 1 до 200 символов")
    @Column(nullable = false)
    private String name;

    @Size(max = 100, message = "Жанр не может превышать 100 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s\\-]*$",
            message = "Жанр может содержать только буквы, пробелы и дефисы")
    private String genre;

    @NotNull(message = "Автор должен быть указан")
    @ManyToOne
    @JoinColumn(name = "authors_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<Comment> comments = new ArrayList<>();
}
