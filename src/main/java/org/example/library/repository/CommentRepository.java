package org.example.library.repository;
import org.example.library.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий для работы с сущностью {@link Comment}.
 * Обеспечивает доступ к данным комментариев/отзывов в базе данных.
 *
 * <p>Предоставляет стандартные CRUD операции и специальные методы
 * для поиска комментариев по связанной книге.</p>
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>  {
    /**
     * Находит все комментарии, относящиеся к указанной книге.
     *
     * <p>Spring автоматически генерирует реализацию этого метода
     * на основе имени.
     *
     * <p>Эквивалентный запрос:
     * {@code SELECT c FROM Comment c WHERE c.book.id = :bookId}</p>
     *
     * @param bookId идентификатор книги, для которой нужно найти комментарии
     * @return список комментариев для указанной книги (может быть пустым)
     */
    List<Comment> findByBookId(Long bookId);
}
