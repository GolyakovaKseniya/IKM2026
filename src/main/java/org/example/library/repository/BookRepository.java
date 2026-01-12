package org.example.library.repository;
import org.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByAuthorId(Long authorId);
}
