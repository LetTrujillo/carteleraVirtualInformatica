Notas importantes
==================

+ La API Rest de Guaraní se encuentra bajo las urls /guarani-api siguiendo la especificación de la práctica 10.
+ Para generar datos para probar la aplicación se pueden llamar a los servicios crearAlumnosDePrueba y crearDocentesDePrueba, que serán encargados de crear y guardar en la base de datos configurada los alumnos o docentes para poder consultar y probar el login.

+CRUD: Se realizaron CRUD para usuario y cartelera. Los mismo están bajo las url /usuario y /cartelera.
Algunos ejemplos para realizar las operaciones:

*Cartelera:

Crear cartelera, POST:
http://{host}/CarteleraVirtualInformatica/cartelera
Espera recibir un json, por ejemplo:
{
	"titulo": "Cartelera de Ofertas Laborales",
	"activo": true,
	"publicaciones": null
}

Recuperar cartelera, GET:
http://{host}/CarteleraVirtualInformatica/cartelera/{idCartelera}

Recuperar todas las carteleras, GET:
http://{host}/CarteleraVirtualInformatica/cartelera

Modificar cartelera, POST:
http://{host}/CarteleraVirtualInformatica/cartelera/update
Espera recibir un json, por ejemplo:
{
"id": 4,
"titulo": "Cartelera de Ofertas Laborales",
"activo": false,
"publicaciones": null
}

Eliminar cartelera, DELETE:
http://{host}/CarteleraVirtualInformatica/cartelera/delete/{idCartelera}

---------------------------------------
*Usuario:

Crear usuario alumno, POST:
http://{host}/CarteleraVirtualInformatica/usuario/alumno
Espera recibir un Json, por ejemplo:
{
	"nombreUsuario": "Alumno7",
	"permisos": null
}

Recuperar usuario, GET:
http://{host}/CarteleraVirtualInformatica/usuario/{IdUsuario}

Actualizar usuario, POST:
http://{host}/CarteleraVirtualInformatica/usuario/updateAlumno
Espera recibir un Json, por ejemplo:
{
"id": 7,
"nombreUsuario": "Alumno número 7",
"permisos": null
}

Eliminar usuario, DELETE:
http://{host}/CarteleraVirtualInformatica/usuario/delete/{IdUsuario}






