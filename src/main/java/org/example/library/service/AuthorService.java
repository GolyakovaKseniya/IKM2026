package org.example.library.service;
import org.example.library.model.Author;
import org.example.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Сервисный слой для логики работы с авторами.
 * Инкапсулирует операции с сущностью {@link Author} и обеспечивает
 * взаимодействие между контроллером и репозиторием.
 *
 * Содержит бизнес-логику, валидацию и обработку исключений
 * Service - класс сервисный компонент, класс содержит логику, для инъекции
 * зависимостей в другие компоненты
 */
@Service
@RequiredArgsConstructor
public class AuthorService {
    /**
     * Репозиторий для доступа к данным авторов.
     * Используется final, чтобы гарантировать инициализацию через конструктор
     * и предотвратить изменение ссылки
     */
    private final AuthorRepository authorRepository;

    /**
     * Получает список всех авторов из базы данных.
     *
     * <p>Делегирует вызов репозиторию. Не содержит дополнительной бизнес-логики</p>
     *
     * @return список всех авторов
     */
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    /**
     * Находит автора по его идентификатору.
     *
     * @param id идентификатор автора для поиска
     * @return найденный автор или {@code null} если не найден
     */

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    /**
     * Сохраняет (создает или обновляет) автора в базе данных.
     *
     * <p>Делегирует операцию репозиторию
     *
     * @param author объект автора для сохранения
     * @return сохраненный автор (с присвоенным ID если это новая запись)
     */

    public Author save(Author author) {
        return authorRepository.save(author);
    }


    /**
     * Удаляет автора по идентификатору.
     *
     * @param id идентификатор автора для удаления
     */
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
