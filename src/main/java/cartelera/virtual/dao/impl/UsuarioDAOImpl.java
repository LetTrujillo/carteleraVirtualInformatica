package cartelera.virtual.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.UsuarioDAO;
import cartelera.virtual.entidades.Alumno;
import cartelera.virtual.entidades.Docente;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.FindException;

@Repository("usuarioDAO")
@Transactional
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO {

	public UsuarioDAOImpl(){
		super(Usuario.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Alumno> findAllAlumnos() throws FindException{
		EntityManager em = getEntityManager(); 
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Alumno> q = cb.createQuery(Alumno.class);
			Root<Alumno> c = q.from(Alumno.class);
			q.where(cb.equal(c.type(), Alumno.class));
			Query theQuery = em.createQuery(q);
			
			List<Alumno> usuarios = (List<Alumno>) theQuery.getResultList();
			
			return usuarios;
		}catch(Exception e){
			throw new FindException("Ocurrió un error al tratar de realizar la búsqueda.");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Docente> findAllDocentes() throws FindException{
		EntityManager em = getEntityManager(); 
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Docente> q = cb.createQuery(Docente.class);
			Root<Docente> c = q.from(Docente.class);
			q.where(cb.equal(c.type(), Docente.class));
			Query theQuery = em.createQuery(q);
			
			List<Docente> usuarios = (List<Docente>) theQuery.getResultList();
			
			return usuarios;
		}catch(Exception e){
			throw new FindException("Ocurrió un error al tratar de realizar la búsqueda.");
		}
	}
	
	@Override
	public Alumno checkLoginAlumno(String username, String password) throws FindException{
		EntityManager em = getEntityManager(); 
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Alumno> q = cb.createQuery(Alumno.class);
			Root<Alumno> c = q.from(Alumno.class);
			q.where(cb.and(cb.equal(c.type(), Alumno.class),
					cb.equal(c.get("nombreUsuario"), username),
					cb.equal(c.get("password"), password)));
			Query theQuery = em.createQuery(q);
			
			return (Alumno)theQuery.getSingleResult();
		}catch(Exception e){
			throw new FindException("Ocurrió un error al tratar de realizar la búsqueda.");
		}
	}
	
	@Override
	public Docente checkLoginDocente(String username, String password) throws FindException{
		EntityManager em = getEntityManager(); 
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Docente> q = cb.createQuery(Docente.class);
			Root<Docente> c = q.from(Docente.class);
			q.where(cb.and(cb.equal(c.type(), Docente.class), 
					cb.equal(c.get("nombreUsuario"), username),
					cb.equal(c.get("password"), password)));
			Query theQuery = em.createQuery(q);
			
			return (Docente)theQuery.getSingleResult();
		}catch(Exception e){
			throw new FindException("Ocurrió un error al tratar de realizar la búsqueda.");
		}
	}
	
}
