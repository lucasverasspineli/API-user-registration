package br.com.jdevtreinamentospringboot.samplespringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdevtreinamentospringboot.samplespringboot.model.UsuarioModel;
import br.com.jdevtreinamentospringboot.samplespringboot.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/{nome}")
	public String ola(@PathVariable String nome) {
		return "Olá, " + nome;
	}
	
	
	@RequestMapping(value="/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setNome(nome);
		usuarioRepository.save(usuario);
		return "OLÁ MUNDO GRAVANDO;";
	}
	
	
	@GetMapping(value="listatodos")
	@ResponseBody /*Retorna os dados para o corpo da resposta!*/
	public ResponseEntity<List<UsuarioModel>> listaUsuario(){
		
		List<UsuarioModel> usuarios = usuarioRepository.findAll();//Executa a consulta no banco de Dados!
		
		return new ResponseEntity<List<UsuarioModel>>(usuarios, HttpStatus.OK);/*Retorna a lista em JSON*/
		
	}
	
	/*Post sem retorno Não funciona ele SEMPRE PELO VISTO TEM QUE RETORNAR UM VALOR NO POST*/
//	@PostMapping("/AddsemRetorno")
//	public void adicionandoSemRetorno( Usuario usu) {
//		usuarioRepository.save(usu);
//	}
	
	@PostMapping(value="salvar")//Mapeamento
	@ResponseBody //Descrição da resposta
	public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel user ){
		UsuarioModel usuario =   usuarioRepository.save(user);
		return new ResponseEntity<UsuarioModel>(usuario, HttpStatus.CREATED);
	}
	
	/*Uma API ela não pode depender do navegador nem está amarrado a nada
	 * ele tem que passar no corpo da requisição! */
	@DeleteMapping(value="delete")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam Long idTest){
		usuarioRepository.deleteById(idTest);
		return new ResponseEntity<String>("Usuario deletado com sucesso!", HttpStatus.OK);
		
	}
	
	@GetMapping(value="buscarUsuario")
	@ResponseBody
	public ResponseEntity<UsuarioModel> buscarUsuarioId(@RequestParam(name = "idUsuario") Long idUsuario){/*Recebe dados para consultar*/
		/*Pode usar no final o .get() que ele retorna a própria classe
		 * sem usar o Optional<>, porém pode haver problema se os campos
		 * da tabela estiverem nulos, MAS pode resolver deixando os campo
		 * NOT NULL*/
		UsuarioModel user = usuarioRepository.findById(idUsuario).get();
		return new ResponseEntity<UsuarioModel>(user, HttpStatus.OK);
		
	}
	
	
	@PutMapping(value = "Atualizar")
	@ResponseBody
	/*Quando tem ? no meio de < > é porque o RETORNO É GENÉRICO, ou seja não sabe qual o tipo de retorno*/
	public ResponseEntity<?> atualizarUsuario(@RequestBody UsuarioModel user){
	 if(user.getId() == null) {
		 return new ResponseEntity<String>("Id não existe, não tem como Atualizar!", HttpStatus.OK);
	 }
	 UsuarioModel use = usuarioRepository.saveAndFlush(user);
		return new ResponseEntity<UsuarioModel>(use, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "buscarnome")
	@ResponseBody
	public ResponseEntity<List<UsuarioModel>> buscarNome(@RequestParam(name = "nome") String nome) {
		/* trim() é para tirar os espaços que podem haver!
		* o toUpperCase() serve para deixar tudo Maúsculo, ou seja tirando o Case Sensitive 
		**/
		List<UsuarioModel> users = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<UsuarioModel>>(users, HttpStatus.OK);
		
	}
	
	
}
