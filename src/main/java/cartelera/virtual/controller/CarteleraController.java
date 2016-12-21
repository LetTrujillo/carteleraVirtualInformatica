package cartelera.virtual.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cartelera.virtual.bo.GenericBO;
import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.CarteleraDTO;
import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.exception.DeleteException;
import cartelera.virtual.exception.SaveException;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

@RestController
public class CarteleraController  {
	
	@Autowired
	private GenericBO<Cartelera> carteleraBO;
	
	private Mapper mapper;

	/**
	 * Recupera una cartelera por id
	 * @param id
	 * @return Cartelera
	 */
	@RequestMapping(value="/cartelera/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		try {
			Cartelera cartelera = this.getCarteleraBO().find(id);
			CarteleraDTO retval = mapper.map(cartelera, CarteleraDTO.class);
			return new ResponseEntity<CarteleraDTO>(retval, HttpStatus.OK);
			
		} catch (NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al recuperar la cartelera " + id);
			return new ResponseEntity<ResponseError>(error, HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al recuperar la cartelera " + id);
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/cartelera", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(){
		try {
			List<Cartelera> carteleraList = new ArrayList<Cartelera>();
			carteleraList = this.getCarteleraBO().getAll(Cartelera.class);
			
			List<CarteleraDTO> retval = new ArrayList<CarteleraDTO>();
			HttpStatus resultStatus = HttpStatus.OK;
			
			if(carteleraList == null || carteleraList.size() == 0){
				resultStatus = HttpStatus.NO_CONTENT;
			}else{
				for(Cartelera cart : carteleraList){
					retval.add(mapper.map(cart, CarteleraDTO.class));
				}
			}

			return new ResponseEntity<List<CarteleraDTO>>(retval,resultStatus);

		} catch (NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al recuperar la cartelera " );
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Crea una cartelera
	 * @param carteleraDTO
	 * @return Cartelera
	 */
	@RequestMapping(value = "/cartelera", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> save(@RequestBody CarteleraDTO carteleraDTO) {
		try {
			Cartelera cartelera = new Cartelera();
			cartelera.setTitulo(carteleraDTO.getTitulo());
			cartelera.setActivo(carteleraDTO.isActivo());
			
			cartelera = this.getCarteleraBO().save(cartelera);
			
			CarteleraDTO retval = mapper.map(cartelera, CarteleraDTO.class);
			
			return new ResponseEntity<CarteleraDTO>(retval, HttpStatus.OK);
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
	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> update(@RequestBody CarteleraDTO carteleraDTO) {
		try {
			Cartelera cartelera = new Cartelera();
			cartelera.setId(carteleraDTO.getId());
			cartelera.setTitulo(carteleraDTO.getTitulo());
			cartelera.setActivo(carteleraDTO.isActivo());
			
			cartelera = this.getCarteleraBO().update(cartelera);
			
			CarteleraDTO retval = mapper.map(cartelera, CarteleraDTO.class);
			
			return new ResponseEntity<CarteleraDTO>(retval, HttpStatus.OK);
			
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

	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.DELETE)
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

	public GenericBO<Cartelera> getCarteleraBO() {
		return carteleraBO;
	}

	public void setCarteleraBO(GenericBO<Cartelera> carteleraBO) {
		this.carteleraBO = carteleraBO;
	}
	
	public CarteleraController(){
		mapper = new DozerBeanMapper();
	}
}
