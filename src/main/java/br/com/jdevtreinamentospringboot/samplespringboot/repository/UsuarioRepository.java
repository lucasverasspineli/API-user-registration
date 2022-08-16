package br.com.jdevtreinamentospringboot.samplespringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jdevtreinamentospringboot.samplespringboot.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	
	/*JPQL -> select nomeAlías from nomeClasse alías condição parametro da tabela na classe 
	 * OBS: %?1% -> o % são os WildCard/Coringa do SQL, a ? quer dizer que o valor vai está
	 * no lugar da ?, porém o 1 depois da interrogação serve para dizer que ele vai passar
	 * apenas Um parametro!
	 * o trim(u.nome) e para tirar os espaços que possam haver
	 * o upper() e para deixar tudo maiúsculo no banco, quando ele for consultar tirando 
	 * o Case Sensitive */
	@Query(value = "select u from UsuarioModel u where upper(trim(u.nome)) like %?1%")
	List<UsuarioModel> buscarPorNome(String nome);
	
}
