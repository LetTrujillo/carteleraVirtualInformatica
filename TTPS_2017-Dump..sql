# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.16)
# Base de datos: TTPS
# Tiempo de Generación: 2017-02-09 22:37:10 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

# Volcado de tabla USUARIO
# ------------------------------------------------------------

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;

INSERT INTO `USUARIO` (`USUARIO_PERFIL`, `USUARIO_ID`, `APELLIDO`, `EMAIL`, `NOMBRE`, `USERNAME`, `password`)
VALUES
	('ALUMNO',2,NULL,NULL,NULL,'userAlum',NULL),
	('DOCENTE',5,'Docente','docente@mymail.com','User','docente','1234'),
	('ALUMNO',6,'Alumno','alumno@mymail.com','User','alumno','1234'),
	('ADMINISTRADOR',7,'Admin','admin@mymail.com','User','admin','1234'),
	('PUBLICADOR',19,'Publisher','publisher@mymail.com','User','publicador','1234');

/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla CARTELERA
# ------------------------------------------------------------

LOCK TABLES `CARTELERA` WRITE;
/*!40000 ALTER TABLE `CARTELERA` DISABLE KEYS */;

INSERT INTO `CARTELERA` (`CARTELERA_ID`, `activo`, `titulo`)
VALUES
	(2,1,'Cartelera de Novedades'),
	(3,1,'Proyectos Universitarios'),
	(4,1,'Docentes'),
	(5,1,'Aulas y Horarios'),
	(6,1,'Talleres'),
	(7,1,'Biblioteca'),
	(8,1,'Centro de Estudiantes'),
	(9,1,'Varios');

/*!40000 ALTER TABLE `CARTELERA` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla COMENTARIO
# ------------------------------------------------------------



# Volcado de tabla CONTENIDO_PUBLICACION
# ------------------------------------------------------------



# Volcado de tabla Docente_anios
# ------------------------------------------------------------



# Volcado de tabla PERMISO
# ------------------------------------------------------------

LOCK TABLES `PERMISO` WRITE;
/*!40000 ALTER TABLE `PERMISO` DISABLE KEYS */;

INSERT INTO `PERMISO` (`PERMISO_ID`, `puede_editar`, `puede_publicar`, `cartelera_CARTELERA_ID`, `usuario_USUARIO_ID`)
VALUES
	(1,1,1,2,5),
	(2,1,0,3,5),
	(3,0,1,4,5),
	(4,1,1,5,5),
	(5,0,1,2,6),
	(6,0,0,3,6),
	(7,0,1,4,6),
	(8,0,1,5,6),
	(9,1,1,2,7),
	(10,1,1,3,7),
	(11,1,1,4,7),
	(12,1,1,5,7),
	(13,1,1,2,19),
	(14,1,1,3,19),
	(15,1,1,4,19),
	(16,1,1,5,19);

/*!40000 ALTER TABLE `PERMISO` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla PUBLICACION
# ------------------------------------------------------------

LOCK TABLES `PUBLICACION` WRITE;
/*!40000 ALTER TABLE `PUBLICACION` DISABLE KEYS */;

INSERT INTO `PUBLICACION` (`PUBLICACION_ID`, `activo`, `descripcion`, `fechaPublicacion`, `titulo`, `cartelera_CARTELERA_ID`, `propietario_USUARIO_ID`)
VALUES
	(1,1,'Este es un nuevo comentario','2016-12-12 21:22:08','Título de la publicación',2,2);

/*!40000 ALTER TABLE `PUBLICACION` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla PUBLICACION_COMENTARIO
# ------------------------------------------------------------



# Volcado de tabla PUBLICACION_CONTENIDO_PUBLICACION
# ------------------------------------------------------------



# Volcado de tabla SUSCRIPCION
# ------------------------------------------------------------

LOCK TABLES `SUSCRIPCION` WRITE;
/*!40000 ALTER TABLE `SUSCRIPCION` DISABLE KEYS */;

INSERT INTO `SUSCRIPCION` (`SUSCRIPCION_ID`, `cartelera_CARTELERA_ID`, `suscriptor_USUARIO_ID`)
VALUES
	(1,2,2);

/*!40000 ALTER TABLE `SUSCRIPCION` ENABLE KEYS */;
UNLOCK TABLES;






/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
