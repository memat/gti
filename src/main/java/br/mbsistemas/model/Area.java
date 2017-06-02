package br.mbsistemas.model;

import java.util.ArrayList;
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
@EqualsAndHashCode(of = "nome")
@ToString(of = { "codigo", "nome"})

@Entity
@NamedQueries({ @NamedQuery(name = Area.TODOS_AREA, query = "select a from Area a"),
				@NamedQuery(name = Area.AREA, query = "from Area a where a.nome = :area ")
	})
public class Area implements MinhaEntidade {
	public static final String TODOS_AREA = "TODOS_AREA";
	public static final String AREA = "AREA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String nome;
	
	@OneToMany(mappedBy = "area", targetEntity = Dispositivo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Dispositivo> dispositivos = new ArrayList<>();
	

}
