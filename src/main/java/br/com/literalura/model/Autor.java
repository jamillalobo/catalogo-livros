package br.com.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer dataNascimento;
    private Integer dataMorte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(DadosAutor dadosAutor) {
        this.dataMorte = dadosAutor.dataMorte();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.nome = dadosAutor.nome();
    }

    public Autor() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(Integer dataMorte) {
        this.dataMorte = dataMorte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        String titulos = livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", "));

        return "-----Autor-----\n" +
                "nome: " + nome + '\n' +
                "dataNascimento: " + dataNascimento + '\n' +
                "dataMorte: " + dataMorte + '\n' +
                "livros: [" + titulos + "]\n" +
                "---------------";
    }
}
