package br.mbsistemas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@NamedQueries({ @NamedQuery(name = Dispositivo.TODOS_DISPOSITIVO, query = "select d from Dispositivo d")})
public class Dispositivo implements MinhaEntidade {
	public static final String TODOS_DISPOSITIVO = "TODOS_DISPOSITIVO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String usuario;
	
	@Column
	private String ramal;
	
	@ManyToOne
	@JoinColumn(name="endereco_codigo")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name="area_codigo")
	private Area area;
	
	@ManyToOne
	@JoinColumn(name="tipodispositivo_codigo")
	private TipoDispositivo tipoDispositivo;
	
}
