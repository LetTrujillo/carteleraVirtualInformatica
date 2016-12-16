package cartelera.virtual.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.SuscripcionDAO;
import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.entidades.Suscripcion;
import cartelera.virtual.entidades.Usuario;
import javassist.NotFoundException;

@Repository("suscripcionDAO")
@Transactional
public class SuscripcionDAOImpl extends GenericDAOImpl<Suscripcion> implements SuscripcionDAO {

	public SuscripcionDAOImpl(){
		super(Suscripcion.class);
	}

	@Override
	public List<Usuario> findSuscriptors(Cartelera cartelera) throws NotFoundException {
		if (cartelera != null) {
			EntityManager em = getEntityManager(); 
			try {
				CriteriaBuilder cb = em.getCriteriaBuilder();

				CriteriaQuery<Suscripcion> q = cb.createQuery(Suscripcion.class);
				Root<Suscripcion> c = q.from(Suscripcion.class);
				ParameterExpression<Long> p = cb.parameter(Long.class);
				q.select(c).where(cb.equal(c.get("cartelera").get("id"), p));

				q.select(c);

				Query theQuery = em.createQuery(q);
				theQuery.setParameter(p, cartelera.getId());
				List<Usuario> suscriptores = new ArrayList<Usuario>();
				for(Object s : theQuery.getResultList()){
					suscriptores.add(((Suscripcion)s).getSuscriptor());
				}
				
				return suscriptores;
			}
			catch (RuntimeException e) {
				throw new NotFoundException("Ocurrió un error al buscar suscriptores");
			}
			finally {
				em.close();
			}
		}else{
			throw new NotFoundException("No se encontró la entidad con la cartelera especificada");
		}
	}
}
