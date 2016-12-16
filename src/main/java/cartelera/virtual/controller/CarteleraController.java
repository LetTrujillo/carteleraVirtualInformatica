package cartelera.virtual.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.CarteleraDTO;
import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.exception.DeleteException;
import cartelera.virtual.exception.SaveException;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

@RestController
@RequestMapping("/cartelera")
public class CarteleraController extends AbstractController {

	/**
	 * Recupera una cartelera por id
	 * @param id
	 * @return Cartelera
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		
		Cartelera cartelera =  null;
		try {
			cartelera = this.getCarteleraBO().find(id);
			return new ResponseEntity<Cartelera>(cartelera, HttpStatus.OK);
		} catch (NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al recuperar la cartelera " + id);
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Crea una cartelera
	 * @param carteleraDTO
	 * @return Cartelera
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> save(@RequestBody CarteleraDTO carteleraDTO) {
		Cartelera cartelera = new Cartelera();
		cartelera.setTitulo(carteleraDTO.getTitulo());
		cartelera.setActivo(carteleraDTO.isActivo());
		try {
			return new ResponseEntity<Cartelera>(this.getCarteleraBO().save(cartelera), HttpStatus.OK);
		} catch (SaveException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al intentar guardar la cartelera " + carteleraDTO.getId());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Actualiza una cartelera
	 * @param carteleraDTO
	 * @return Cartelera
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> update(@RequestBody CarteleraDTO carteleraDTO) {
		Cartelera cartelera = new Cartelera();
		cartelera.setId(carteleraDTO.getId());
		cartelera.setTitulo(carteleraDTO.getTitulo());
		cartelera.setActivo(carteleraDTO.isActivo());
		try {
			return new ResponseEntity<Cartelera>(this.getCarteleraBO().update(cartelera), HttpStatus.OK);
		} catch (UpdateException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al intentar actualizar la cartelera " + carteleraDTO.getId());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Elimina una cartelera
	 * @param id
	 * @return Cartelera
	 */

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") Long id){

		try {
			this.getCarteleraBO().remove(id);
			return new ResponseEntity<String>("La cartelera fue eliminada", HttpStatus.OK);
		} catch (DeleteException | NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al intentar eliminar la cartelera " + id);
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
