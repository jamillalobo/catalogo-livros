package br.com.literalura.repository;

import br.com.literalura.model.Autor;
import br.com.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "SELECT * FROM livros l WHERE :idioma = ANY(l.idiomas)", nativeQuery = true)
    List<Livro> livroPorIdioma(String idioma);
}
