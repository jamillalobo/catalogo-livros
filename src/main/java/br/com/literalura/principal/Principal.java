package br.com.literalura.principal;

import br.com.literalura.model.*;
import br.com.literalura.repository.AutorRepository;
import br.com.literalura.repository.LivroRepository;
import br.com.literalura.service.ConsumoApi;
import br.com.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private List<Autor> autoresRegistrados;
    private LivroRepository repositorio;
    private AutorRepository repositorioAutor;
    private List<Livro> livrosRegistrados;

    public Principal(LivroRepository repositorio, AutorRepository repositorioAutor) {
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ------------------------------------------------------
                    Escolha o número de sua opção:
                    1- buscar livro pelo título
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    0 - Sair
                    ------------------------------------------------------
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private DadosLivro getDados() {
        System.out.println("Digite o nome do livro que deseja procurar:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+").toLowerCase());
        var dados = conversor.obterDados(json, Dados.class);
        DadosLivro livros = dados.resultados().getFirst();
        return livros;

    }

    private void buscarLivroPorTitulo() {
        DadosLivro dados = getDados();
        for (DadosAutor dadosAutor : dados.autor()) {
            Optional<Autor> autorOptional = repositorioAutor.findByNome(dadosAutor.nome());
            Autor autor;

            if (autorOptional.isPresent()) {
                autor = autorOptional.get();
            } else {
                autor = new Autor(dadosAutor);
                repositorioAutor.save(autor);
            }

            Livro livro = new Livro(dados, autor);
            autor.getLivros().add(livro);
            repositorio.save(livro);
        }
    }

    private void listarLivros() {
        livrosRegistrados = repositorio.findAll();
        livrosRegistrados.forEach(System.out::println);

    }

    private void listarAutores() {
        autoresRegistrados = repositorioAutor.findAll();
        autoresRegistrados.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Escolha um ano para buscar o autor vivo: ");
        var anoAutor = leitura.nextInt();
        leitura.nextLine();

        List<Autor> filtroAutor = repositorioAutor.autoresVivos(anoAutor);
        System.out.println("*** Autores vivos ***");
        filtroAutor.forEach(System.out::println);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
            Insira o codigo do idioma para realizar a busca:
            en- ingles;
            pt- portugues;
            fr- frances;
            es-espanhol;\s
           \s""");
        var idioma = leitura.nextLine();

        List<Livro> filtroIdioma = repositorio.livroPorIdioma(idioma);
        filtroIdioma.forEach(System.out::println);
    }


}
