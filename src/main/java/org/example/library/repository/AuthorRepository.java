package org.example.library.repository;
import org.example.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий для работы с сущностью {@link Author}.
 * Обеспечивает доступ к данным авторов в базе данных.
 * Repository - помечает как компонент доступа к данным,spring создает реализацию
 * во время выполнения
 * <p>Расширяет {@link JpaRepository}, предоставляя стандартные CRUD операции
 * и методы для постраничного чтения, сортировки</p>
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    /**
     * Проверяет, существует ли автор с указанным именем.
     *
     * <p>Spring автоматически реализует этот метод на основе его имени.
     * Имя метода разбирается и преобразуется в запрос.</p>
     *
     * <p>Эквивалентный запрос:
     * {@code SELECT COUNT(a) > 0 FROM Author a WHERE a.name = :name}</p>
     *
     * @param name имя автора для проверки
     * @return {@code true} если автор с таким именем существует, {@code false} в противном случае
     */
    boolean existsByName(String name);
}