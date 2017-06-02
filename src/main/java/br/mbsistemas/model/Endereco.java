package br.mbsistemas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "endereco")
@ToString(of = { "codigo", "endereco", "status"})

@Entity
@NamedQueries({ @NamedQuery(name = Endereco.TODOS_ENDERECO, query = "select e from Endereco e"),
	@NamedQuery(name = Endereco.ENDERECO, query = "from Endereco a where a.endereco = :endereco ")
})
public class Endereco implements MinhaEntidade {
	public static final String TODOS_ENDERECO = "TODOS_ENDERECO";
	public static final String ENDERECO = "ENDERECO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String endereco;
	
	@Column(length=2)
	private String status;
	
	@OneToMany(mappedBy = "endereco", targetEntity = Dispositivo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Dispositivo> dispositivos = new ArrayList<>();
	
}
