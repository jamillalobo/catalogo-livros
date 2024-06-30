package br.com.literalura.repository;

import br.com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query("SELECT a from Autor a WHERE a.dataNascimento <= :anoAutorVivo AND a.dataMorte >= :anoAutorVivo")
    List<Autor> autoresVivos(int anoAutorVivo);
}
