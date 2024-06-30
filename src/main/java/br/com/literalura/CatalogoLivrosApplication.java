package br.com.literalura;

import br.com.literalura.model.Livro;
import br.com.literalura.principal.Principal;
import br.com.literalura.repository.AutorRepository;
import br.com.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoLivrosApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository repositorio;
	@Autowired
	private AutorRepository repositorioAutor;

	public static void main(String[] args) {
		SpringApplication.run(CatalogoLivrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio,repositorioAutor);
		principal.exibeMenu();
	}
}
