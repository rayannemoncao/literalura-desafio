package io.git.alura.desafio.literalura_desafio;

import io.git.alura.desafio.literalura_desafio.persistence.models.Book;
import io.git.alura.desafio.literalura_desafio.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraDesafioApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraDesafioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Buscar livro pelo título");
            System.out.println("2. Listar livros registrados");
            System.out.println("3. Listar nossos autores");
            System.out.println("4. Listar autores em determinado ano");
            System.out.println("5. Listar livros em determinado idioma");
            System.out.println("0. Sair");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String title = scanner.nextLine();
                    Book book = bookService.searchAndSaveBookByTitle(title);
                    if (book != null) {
                        System.out.println("Livro encontrado e salvo no banco de dados: " + book.getTitle());
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 2:
                    bookService.listAllBooks().forEach(System.out::println);
                    break;
                case 3:
                    bookService.listAllAuthors().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Digite o ano:");
                    int year = scanner.nextInt();
                    bookService.listAuthorsByYear(year).forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Digite o idioma (ex: en, pt, fr):");
                    String language = scanner.nextLine();
                    bookService.listBooksByLanguage(language).forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

}
