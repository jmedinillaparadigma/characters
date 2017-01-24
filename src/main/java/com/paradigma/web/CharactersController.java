package com.paradigma.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paradigma.beans.response.Character;
import com.paradigma.model.CharacterModel;
import com.paradigma.services.CharactersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller to handle the http request to this service
 * @author jmedinilla
 */
@Api(value = "Characters management service")
@RequestMapping("/characters")
@RestController
@Slf4j
public class CharactersController {

	@Autowired
	private CharactersService service;
	
	
	/**
	 * This method retrieves all the available characters stored in the persistence layer
	 * @return The list containing all the available characters
	 */
	@ApiOperation(value = "Retrieves all the available characters")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The characters has been retrieved successfully", response = String.class),
			@ApiResponse(code = 424, message = "Failed external dependecy"),
			@ApiResponse(code = 500, message = "Server error") })
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Character>> retrieveAvailableCharacters() {

		log.info("GET -> /characters");
		List<CharacterModel> serviceResult = service.list();
		
		List<Character> result = new ArrayList<>();
		serviceResult.stream().forEach(c -> result.add(transformCharacter(c)));
		
		return ResponseEntity.ok(result);
	}

	//////////////////////////////
	// Private functions
	//////////////////////////////
	
	private Character transformCharacter(CharacterModel characterModel) {
		Character character = new Character();
		BeanUtils.copyProperties(characterModel, character);
		return character;
	}

	
}
