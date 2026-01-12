package org.example.library.repository;
import org.example.library.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>  {
    List<Comment> findByBookId(Long bookId);
}
