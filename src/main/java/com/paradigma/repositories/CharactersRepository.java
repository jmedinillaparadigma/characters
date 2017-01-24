package com.paradigma.repositories;

import java.util.List;

import com.paradigma.model.CharacterModel;

public interface CharactersRepository {

	/**
	 * This method retrieves all the available characters stored in the persistence layer
	 * @return The list containing all the available characters
	 */
	public List<CharacterModel> list();
}
