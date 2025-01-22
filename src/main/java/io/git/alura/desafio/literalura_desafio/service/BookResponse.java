package io.git.alura.desafio.literalura_desafio.service;

import io.git.alura.desafio.literalura_desafio.persistence.models.Book;

import java.util.List;

public class BookResponse {

    private List<Book> results;

    // Getters e Setters

    public List<Book> getResults() {
        return results;
    }

    public void setResults(List<Book> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "results=" + results +
                '}';
    }
}
