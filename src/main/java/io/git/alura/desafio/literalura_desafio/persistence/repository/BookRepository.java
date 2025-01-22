package io.git.alura.desafio.literalura_desafio.persistence.repository;

import io.git.alura.desafio.literalura_desafio.persistence.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguagesContaining(String language);
}
