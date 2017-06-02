package teste;

import org.apache.log4j.Logger;

import br.edu.unoesc.exception.DAOException;
import br.mbsistemas.dao.AreaDAO;
import br.mbsistemas.dao.DispositivoDAO;
import br.mbsistemas.dao.EnderecoDAO;
import br.mbsistemas.dao.TipoDispositivoDAO;
import br.mbsistemas.model.Area;
import br.mbsistemas.model.Dispositivo;
import br.mbsistemas.model.Endereco;
import br.mbsistemas.model.TipoDispositivo;

public class Main {

	public static void main(String[] args) throws DAOException {
		EnderecoDAO enddao = new EnderecoDAO();
		System.out.println(enddao.buscar(Endereco.class, 1L));
		// CONFIGURA O PRIMEIRO ENDERECO
		/*
		 Endereco end = new Endereco(); 
		 end.setEndereco("192.168.1.1");
		 end.setStatus("L");
		 EnderecoDAO endDao = new EnderecoDAO(); endDao.salvar(end);
		*/
		 
		// DEVE RODAR > 1ENDERECO.SQL
		
		// LISTA DISPOSITIVOS CADASTRADOS
		/*EnderecoDAO endDao = new EnderecoDAO();
		for (Endereco end : endDao.listar(Endereco.class)) {
			System.out.println("C: "+end.getCodigo()+" E: "+end.getEndereco()+" S: "+end.getStatus());
		}*/
		
		// CONFIGURA O PRIMEIRO ENDERECO
		/*
		TipoDispositivo tipo = new TipoDispositivo();
		tipo.setTipo("COMPUTADOR");

		TipoDispositivoDAO tipoDao = new TipoDispositivoDAO();
		tipoDao.salvar(tipo);
		*/
		
		//DEVE RODAR > 1TIPODISPOSITIVO.SQL
		
		// CONFIGURA A PRIMEIRA AREA

		/*
		Area area = new Area();
		area.setNome("RAIO-X");
		
		AreaDAO areaDao = new AreaDAO();
		areaDao.salvar(area);
		*/
		
		//DEVE RODAR > 1AREA.SQL
		
		// CONFIGURA O PRIMEIRO DISPOSITIVO
		/*
		
		EnderecoDAO endDao = new EnderecoDAO();
		Endereco end = endDao.buscar(Endereco.class, 14L);
		end.setStatus("O");
		endDao.salvar(end);
		
		AreaDAO areaDao = new AreaDAO();
		Area area = areaDao.buscar(Area.class, 1L);
		
		TipoDispositivoDAO tpDao = new TipoDispositivoDAO();
		TipoDispositivo tp = tpDao.buscar(TipoDispositivo.class, 1L);
		
		Dispositivo disp = new Dispositivo();
		disp.setNome("RAIOX1");
		disp.setDescricao("");
		disp.setEndereco(end);
		disp.setArea(area);
		disp.setUsuario("Sabrina - Janela");
		disp.setRamal("781");
		disp.setTipoDispositivo(tp);
		
		DispositivoDAO dispDao = new DispositivoDAO();
		dispDao.salvar(disp);*/
		
	}
	
}
