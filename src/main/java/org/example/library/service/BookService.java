package org.example.library.service;
import org.example.library.model.Book;
import org.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
//        if (book.getId() == null) {
//            List<Book> authorBooks = bookRepository.findByAuthorId(book.getAuthor().getId());
//
//            for (Book b : authorBooks) {
//                if (b.getName().equalsIgnoreCase(book.getName())) {
//                    throw new RuntimeException("Книга '" + book.getName() + "' уже есть у этого автора");
//                }
//            }
//        }
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
