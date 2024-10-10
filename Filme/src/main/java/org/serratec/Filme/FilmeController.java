package org.serratec.Filme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme")
public class FilmeController {
	@Autowired
	private FilmeRepository repositorio;
	
	@GetMapping // método GET para buscar todos os filmes via array list
	public List<Filme> obterTodos(){
		//List<Filme> filmes = repositorio.findAll();
		//System.out.println("Número de filmes encontrados: " + filmes.size());
		return repositorio.findAll();
		
	}
	@GetMapping("/{id}")
	public Filme obterPorId(@PathVariable Long id) {
		return repositorio.findById(id).orElse(null);
	}
	@PostMapping
	public Filme cadastrarFilme(@RequestBody Filme filme) {
		return repositorio.save(filme);
	}
	@DeleteMapping("/{id}")
	public void excluirFilme(@PathVariable Long id) {
		repositorio.deleteById(id);
	}
	@PutMapping("/{id}")
	public Filme alterarFilme(@PathVariable Long id, @RequestBody Filme filme) {
		if(repositorio.existsById(id)) {
			filme.setId(id);
			repositorio.save(filme);
			return filme;
		}
		return null;
	}
}
