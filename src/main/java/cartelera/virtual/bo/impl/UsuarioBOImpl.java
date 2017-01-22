package cartelera.virtual.bo.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cartelera.virtual.bo.UsuarioBO;
import cartelera.virtual.dao.UsuarioDAO;
import cartelera.virtual.dto.UsuarioDTO;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.FindException;

@Service
public class UsuarioBOImpl extends GenericBOImpl<Usuario> implements UsuarioBO {
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	private Mapper mapper;
	
	public UsuarioBOImpl(){
		mapper = new DozerBeanMapper();
	}
	
	public UsuarioDTO login(String username, String password) throws FindException{
		Usuario loginUser = usuarioDAO.checkLogin(username, password);
		return mapper.map(loginUser, UsuarioDTO.class);
	}
	
	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}
