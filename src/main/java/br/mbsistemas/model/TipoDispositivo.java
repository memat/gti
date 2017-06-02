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
@EqualsAndHashCode(of = "tipo")
@ToString(of = { "codigo", "tipo"})

@Entity
@NamedQueries({ @NamedQuery(name = TipoDispositivo.TODOS_TIPO_DISPOSITIVO, query = "select t from TipoDispositivo t")})
public class TipoDispositivo implements MinhaEntidade{
public static final String TODOS_TIPO_DISPOSITIVO = "TODOS_TIPODISPOSITIVO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String tipo;
	
	@OneToMany(mappedBy = "tipoDispositivo", targetEntity = Dispositivo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Dispositivo> dispositivos = new ArrayList<>();
	
}
