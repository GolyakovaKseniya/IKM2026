package org.example.library.service;
import org.example.library.model.Book;
import org.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Сервисный слой для бизнес-логики работы с книгами.
 * Содержит операции управления книгами и обеспечивает взаимодействие
 * между контроллером и репозиторием.
 *
 * <p>Реализует логику работы с книгами, включая связи с авторами и комментариями.</p>
 */
@Service
@RequiredArgsConstructor
public class BookService {

    /**
     * Репозиторий для доступа к данным книг.
     * Используется final для неизменяемости после инъекции.
     */
    private final BookRepository bookRepository;

    /**
     * Получает список всех книг из базы данных.
     *
     * <p>Возвращает все книги, включая их авторов (ленивая загрузка).
     *
     * @return список всех книг (может быть пустым)
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    /**
     * Находит книгу по её идентификатору.
     *
     * @param id идентификатор книги для поиска
     * @return найденная книга или {@code null} если не найдена
     */
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Сохраняет (создает или обновляет) книгу в базе данных.
     *
     * <p>Делегирует операцию репозиторию.</p>
     *
     * @param book объект книги для сохранения
     * @return сохраненная книга (с присвоенным ID если это новая запись)
     */

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Удаляет книгу по идентификатору.
     *
     * <p>Удаляет книгу и все связанные комментарии (если cascade = ALL).
     *
     * @param id идентификатор книги для удаления
     */
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


    /**
     * Находит все книги указанного автора.
     *
     * <p>Использует кастомный метод репозитория для поиска по ID автора.
     *
     * @param authorId идентификатор автора
     * @return список книг указанного автора (может быть пустым)
     */
    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
