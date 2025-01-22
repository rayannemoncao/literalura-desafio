package io.git.alura.desafio.literalura_desafio.service;

import io.git.alura.desafio.literalura_desafio.persistence.models.Author;
import io.git.alura.desafio.literalura_desafio.persistence.models.Book;
import io.git.alura.desafio.literalura_desafio.persistence.repository.AuthorRepository;
import io.git.alura.desafio.literalura_desafio.persistence.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @Transactional
    public Book searchAndSaveBookByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gutendex.com/books?title=" + title;
        BookResponse response = restTemplate.getForObject(url, BookResponse.class);

        if (response != null && !response.getResults().isEmpty()) {
            Book book = response.getResults().get(0);
            Book saved = bookRepository.save(book);
            for (Author author: book.getAuthors()) {
                author.setBook(saved);
                Author save = authorRepository.save(author);
                System.out.println(save);
            }
            return saved;
        }
        return null;
    }
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsByYear(int year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguagesContaining(language);
    }
}
