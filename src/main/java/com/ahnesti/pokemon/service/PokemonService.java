package com.ahnesti.pokemon.service;

import com.ahnesti.pokemon.entity.PokemonCharacter;
import com.ahnesti.pokemon.repository.PokemonRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    // Constructor
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    // Get all Pokemon
    public List<PokemonCharacter> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    // Get Pokemon by ID
    public PokemonCharacter getPokemonById(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pokemon not found"));
    }

    // Add a new Pokemon
    public PokemonCharacter addPokemon(PokemonCharacter pokemon) {
        pokemon.setCharacterId(null);
        return pokemonRepository.save(pokemon);
    }

    // Update an existing Pokemon
    public PokemonCharacter updatePokemon(Long id, PokemonCharacter updatedPokemon) {

        PokemonCharacter pokemon = getPokemonById(id);

        pokemon.setName(updatedPokemon.getName());
        pokemon.setRegion(updatedPokemon.getRegion());
        pokemon.setType(updatedPokemon.getType());
        pokemon.setImage(updatedPokemon.getImage());
        pokemon.setDescription(updatedPokemon.getDescription());

        return pokemonRepository.save(pokemon);
    }

    // Delete Pokemon
    public void deletePokemon(Long id) {
        PokemonCharacter pokemon = getPokemonById(id);
        pokemonRepository.delete(pokemon);
    }

    // Filter Pokemon by region
    public List<PokemonCharacter> getPokemonByRegion(String region) {
        return pokemonRepository.findByRegionIgnoreCase(region);
    }

    // Search Pokemon by name
    public List<PokemonCharacter> searchPokemonByName(String name) {
        return pokemonRepository.findByNameContainingIgnoreCase(name);
    }
}