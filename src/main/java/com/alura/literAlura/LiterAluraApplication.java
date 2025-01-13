package com.alura.literAlura;

import com.alura.literAlura.model.DadosLivro;
import com.alura.literAlura.model.DadosPessoa;
import com.alura.literAlura.repository.LivroRepository;
import com.alura.literAlura.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	private LivroRepository livroRepository;
	private Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {

		LivroService livroService = new LivroService();
		int opcao;

		do {
			exibirMenu();
			opcao = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (opcao) {
				case 1 -> {
					System.out.print("Digite o título do livro: ");
					String titulo = scanner.nextLine();
					buscarLivroPorTitulo(livroService, titulo);
				}
				case 2 -> listarLivrosRegistrados(livroService);
				case 3 -> listarAutoresRegistrados(livroService);
				case 4 -> {
					System.out.print("Digite o ano: ");
					int ano = scanner.nextInt();
					listarAutoresVivoEmAno(livroService, ano);
				}
				case 5 -> {
					System.out.print("Digite o idioma (código de duas letras): ");
					String idioma = scanner.nextLine();
					listarLivrosPorIdioma(livroService, idioma);
				}
				case 0 -> System.out.println("Saindo...");
				default -> System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 0);

		scanner.close();
	}

	private void exibirMenu() {
		System.out.println("Escolha o número de sua opção:");
		System.out.println("1 - Buscar livro pelo título");
		System.out.println("2 - Listar livros registrados");
		System.out.println("3 - Listar autores registrados");
		System.out.println("4 - Listar autores vivo em um determinado ano");
		System.out.println("5 - Listar livros em um determinado idioma");
		System.out.println("0 - Sair");
	}

	private void buscarLivroPorTitulo(LivroService livroService, String titulo) {
		List<DadosLivro> livros = livroService.getLivrosPorTitulo(titulo);
		for (DadosLivro livro : livros) {
			System.out.println("Id: " + livro.id());
			System.out.println("Título: " + livro.titulo());
			System.out.print("Autores: ");
			for (DadosPessoa autor : livro.autores()) {
				System.out.print(autor.nome() + " ");
			}
			System.out.println();
			System.out.println("Idiomas: " + String.join(", ", livro.idiomas()));
			System.out.println("-------------------------------");
		}

		if (!livros.isEmpty()){
			System.out.println("Digite o Id do livro que deseja registar ou 0 para cancelar: ");
			int id = scanner.nextInt();
			scanner.nextLine();

			if (id != 0){
				registrarLivro(livroService, id);
			} else {
				System.out.println("Registro cancelado!");
			}
		}
	}

	private void listarLivrosRegistrados(LivroService livroService) {
		List<DadosLivro> livros = livroService.getLivros();
		for (DadosLivro livro : livros) {
			System.out.println(livro);
		}
	}

	private void listarAutoresRegistrados(LivroService livroService) {
		List<DadosLivro> livros = livroService.getLivros();
		for (DadosLivro livro : livros) {
			for (DadosPessoa autor : livro.autores()) {
				System.out.println(autor.nome());
			}
		}
	}

	private void listarAutoresVivoEmAno(LivroService livroService, int ano) {
		List<DadosLivro> livros = livroService.getLivros();
		for (DadosLivro livro : livros) {
			for (DadosPessoa autor : livro.autores()) {
				if (autor.anoNascimento() <= ano && (autor.anoMorte() == null || autor.anoMorte() >= ano)) {
					System.out.println(autor.nome());
				}
			}
		}
	}

	private void listarLivrosPorIdioma(LivroService livroService, String idioma) {
		List<DadosLivro> livros = livroService.getLivros();
		for (DadosLivro livro : livros) {
			if (livro.idiomas().contains(idioma)) {
				System.out.println(livro);
			}
		}
	}

	private void registrarLivro(LivroService livroService, int id){
		Optional<DadosLivro> livroOptional = livroService.getLivrosById(id);
		if (livroOptional.isPresent()){
			DadosLivro livro = livroOptional.get();
			livroRepository.save(livro);
			System.out.println("Livro registrado com sucesso!");
		} else {
			System.out.println("Livro não encontrado");
		}
	}
}
