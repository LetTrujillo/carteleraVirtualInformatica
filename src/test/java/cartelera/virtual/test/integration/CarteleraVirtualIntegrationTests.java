package cartelera.virtual.test.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cartelera.virtual.dao.CarteleraDAO;
import cartelera.virtual.dao.PermisoDAO;
import cartelera.virtual.dao.PublicacionDAO;
import cartelera.virtual.dao.SuscripcionDAO;
import cartelera.virtual.dao.UsuarioDAO;
import cartelera.virtual.entidades.Alumno;
import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.entidades.Permiso;
import cartelera.virtual.entidades.Publicacion;
import cartelera.virtual.entidades.Suscripcion;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.DeleteException;
import cartelera.virtual.exception.SaveException;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

public class CarteleraVirtualIntegrationTests{

	final static Logger logger = Logger.getLogger(CarteleraVirtualIntegrationTests.class);
	private static ClassPathXmlApplicationContext ctx;

	public static void main(String[] args) {
		AllTestsCases();
	}


	public static void AllTestsCases(){
		//Dirty but works fine
		ConfigureLogger();
		
		//Configure Spring application context
		ConfigureCtx();

		//CRUD usuario
		UserCRUDTests();

		//CRUD cartelera
		CarteleraCRUDTests();
	}


	private static void ConfigureCtx() {
		ctx = new ClassPathXmlApplicationContext("app-ctx.xml");
	}


	private static void ConfigureLogger() {
		org.apache.log4j.BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.ERROR);
		Logger.getRootLogger().setLevel(Level.INFO);
	}

	public static void UserCRUDTests(){
		Alumno alumno = new Alumno();
		alumno.setNombreUsuario("userAlum");


		Cartelera cartelera = new Cartelera();
		cartelera.setTitulo("Cartelera de Novedades");
		cartelera.setActivo(true);

		List<Permiso> permisos = new ArrayList<Permiso>();
		Permiso permiso = new Permiso();
		permiso.setPuede_editar(true);
		permiso.setPuede_publicar(true);
		permiso.setUsuario(alumno);
		permiso.setCartelera(cartelera);
		permisos.add(permiso);

		alumno.setPermisos(permisos);

		try{
			CarteleraDAO carteleraDao = ctx.getBean("carteleraDAO", CarteleraDAO.class);
			carteleraDao.save(cartelera);
			logger.info("----------CARTELERA GUARDADA----------");

			UsuarioDAO userDao = ctx.getBean("usuarioDAO", UsuarioDAO.class);
			Usuario savedAlum = userDao.save(alumno);
			long theId = savedAlum.getId();
			logger.info("----------ALUMNO GUARDADO CON EL ID: "+theId+"----------");

			savedAlum.setNombreUsuario("updatedUserName");
			Usuario updatedAlum = userDao.update(savedAlum);
			logger.info("----------ALUMNO ACTUALIZADO CON EL NOMBRE: "+updatedAlum.getNombreUsuario()+"----------");

			PermisoDAO permisoDao = ctx.getBean("permisoDAO", PermisoDAO.class);
			userDao.remove(updatedAlum.getId());
			carteleraDao.remove(cartelera.getId());
			permisoDao.remove(permiso.getId());

			userDao.find(theId);
		}catch (SaveException e) {
			e.printStackTrace();
			logger.error("----------ERROR GUARDANDO.----------");
		}catch (UpdateException e) {
			e.printStackTrace();
			logger.error("----------ERROR ACTUALIZANDO.----------");
		}catch (DeleteException e) {
			e.printStackTrace();
			logger.error("----------ERROR ELIMINANDO.----------");
		}catch (NotFoundException e){
			e.printStackTrace();
			logger.info("----------SE TIRÓ UNA EXCEPTION PORQUE NO SE ENCONTRÓ EL USUARIO. LA ELIMINACIÓN FUE EXITOSA.----------");
		}
	}

	public static void CarteleraCRUDTests(){
		Alumno alumno = new Alumno();
		alumno.setNombreUsuario("userAlum");

		Cartelera cartelera = new Cartelera();
		cartelera.setTitulo("Cartelera de Novedades");
		cartelera.setActivo(true);

		List<Permiso> permisos = new ArrayList<Permiso>();
		Permiso permiso = new Permiso();
		permiso.setPuede_editar(true);
		permiso.setPuede_publicar(true);
		permiso.setUsuario(alumno);
		permiso.setCartelera(cartelera);
		permisos.add(permiso);

		alumno.setPermisos(permisos);

		Suscripcion suscripcion = new Suscripcion();
		suscripcion.setCartelera(cartelera);
		suscripcion.setSuscriptor(alumno);

		Publicacion pub = new Publicacion();
		pub.setActivo(true);
		pub.setCartelera(cartelera);
		pub.setDescripcion("Este es un nuevo comentario");
		pub.setFechaPublicacion(new Date());
		pub.setPropietario(alumno);
		pub.setTitulo("Título de la publicación");
		
		try{
			CarteleraDAO carteleraDao = ctx.getBean("carteleraDAO", CarteleraDAO.class);
			carteleraDao.save(cartelera);
			logger.info("----------CARTELERA GUARDADA----------");

			UsuarioDAO userDao = ctx.getBean("usuarioDAO", UsuarioDAO.class);
			Usuario savedAlum = userDao.save(alumno);
			long theId = savedAlum.getId();
			logger.info("----------ALUMNO GUARDADO CON EL ID: "+theId+"----------");

			SuscripcionDAO suscripcionDao = ctx.getBean("suscripcionDAO", SuscripcionDAO.class);
			suscripcionDao.save(suscripcion);
			logger.info("----------SUSCRIPCIÓN GUARDADA----------");

			List<Usuario> suscriptores = suscripcionDao.findSuscriptors(cartelera);
			logger.info("----------SUSCRIPTORES DE LA CARTELERA "+cartelera.getTitulo()+": "+suscriptores.size()+"----------");
			
			PublicacionDAO publicacionDao = ctx.getBean("publicacionDAO", PublicacionDAO.class);
			publicacionDao.save(pub);
			logger.info("----------PUBLICACIÓN GUARDADA----------");
			
			Cartelera carteleraUpdated = carteleraDao.find(cartelera.getId());
			logger.info("----------PUBLICACIONES EN LA CARTELERA: "+carteleraUpdated.getPublicaciones().size()+"----------");
		}catch (SaveException e) {
			e.printStackTrace();
			logger.error("----------ERROR GUARDANDO.----------");
		}catch (NotFoundException e){
			e.printStackTrace();
			logger.error("----------NO SE ENCONTRARON LAS ENTIDADES BUSCADAS----------");
		}
	}
}
