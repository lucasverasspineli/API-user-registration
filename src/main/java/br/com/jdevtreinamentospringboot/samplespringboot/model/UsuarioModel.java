package br.com.jdevtreinamentospringboot.samplespringboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
/*ele dá o nome, diz que o sequenceName vai receber o nome que foi criado
 * allocationSize é de quanto vai incrementar Ex: de 1 em 1, ou 4 em 4 ...
 * initValue diz que ele começa com valor inicial
 * */
@SequenceGenerator(name="seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
@Table(name="tb_usuario")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;
	
	private String nome;
	
	private int idade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
}
