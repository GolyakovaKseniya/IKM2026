package org.example.library.service;
import org.example.library.model.Comment;
import org.example.library.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Сервисный слой для бизнес-логики работы с комментариями/отзывами.
 * Управляет операциями с комментариями, включая валидацию,
 * проверку ограничений и взаимодействие с книгами.
 *
 * <p>Обрабатывает логику оценки книг и обратную связь от читателей.</p>
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    /**
     * Репозиторий для доступа к данным комментариев.
     * Final гарантирует инициализацию через конструктор и предотвращает изменение ссылки.
     */
    private final CommentRepository commentRepository;


    /**
     * Получает список всех комментариев из базы данных.
     *
     * <p>Возвращает все комментарии, включая связанные книги.
     *
     * @return список всех комментариев (может быть пустым)
     */
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    /**
     * Находит комментарий по его идентификатору.
     *
     * @param id идентификатор комментария для поиска
     * @return найденный комментарий или {@code null} если не найден
     */
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }


    /**
     * Сохраняет (создает или обновляет) комментарий в базе данных.
     *
     * <p>Делегирует операцию репозиторию.</p>
     *
     * @param comment объект комментария для сохранения
     * @return сохраненный комментарий (с присвоенным ID если новая запись)
     */
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Удаляет комментарий по идентификатору.
     * @param id идентификатор комментария для удаления
     */
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    /**
     * Находит все комментарии для указанной книги.
     *
     * <p>Использует кастомный метод репозитория</p>
     *
     * @param bookId идентификатор книги
     * @return список комментариев для книги (может быть пустым)
     */
    public List<Comment> findByBookId(Long bookId) {
        return commentRepository.findByBookId(bookId);
    }
}
