package br.com.java.projeto.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	//Converte um objeto, para que o java possa trabalhar com os valores nele armazenado, ao inves de sua posição na memoria
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getCodigo());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GenericDomain that = (GenericDomain) o;
		return Objects.equals(codigo, that.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
}
