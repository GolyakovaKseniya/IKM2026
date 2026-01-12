package org.example.library.repository;
import org.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий для работы с сущностью {@link Book}.
 * Обеспечивает доступ к данным книг в базе данных.
 *
 * <p>Предоставляет как стандартные CRUD операции через {@link JpaRepository},
 * так и кастомные методы для поиска книг по автору.</p>
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    /**
     * Находит все книги, принадлежащие указанному автору.
     *
     * <p>Spring автоматически генерирует реализацию этого метода
     * на основе его имени. Синтаксис имени метода:
     * {@code findBy + [ИмяПоляСущности] + [ИмяПоляВложеннойСущности] + Id}</p>
     *
     * <p>Этот метод эквивалентен JPQL запросу:
     * {@code SELECT b FROM Book b WHERE b.author.id = :authorId}</p>
     *
     * @param authorId идентификатор автора, чьи книги нужно найти
     * @return список книг указанного автора
     */
    List<Book> findByAuthorId(Long authorId);
}
