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

        // Keep image and imageUrl synchronized
        if (pokemon.getImageUrl() != null && !pokemon.getImageUrl().isBlank()) {
            pokemon.setImage(pokemon.getImageUrl());
        } else if (pokemon.getImage() != null && !pokemon.getImage().isBlank()) {
            pokemon.setImageUrl(pokemon.getImage());
        }

        return pokemonRepository.save(pokemon);
    }

    // Update an existing Pokemon
    public PokemonCharacter updatePokemon(Long id, PokemonCharacter updatedPokemon) {

        // Find the existing Pokemon
        PokemonCharacter pokemon = getPokemonById(id);

        // Update its information
        pokemon.setName(updatedPokemon.getName());
        pokemon.setRegion(updatedPokemon.getRegion());
        pokemon.setType(updatedPokemon.getType());
        pokemon.setDescription(updatedPokemon.getDescription());

        // Keep both image fields synchronized
        String imageUrl = updatedPokemon.getImageUrl();

        if (imageUrl == null) {
            imageUrl = updatedPokemon.getImage();
        }

        pokemon.setImageUrl(imageUrl);
        pokemon.setImage(imageUrl);

        // Save changes to the existing record
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

    // Search Pokemon by name and/or region
    public List<PokemonCharacter> searchPokemon(String name, String region) {

        List<PokemonCharacter> pokemon = pokemonRepository.findAll();

        if (name != null && !name.isBlank()) {
            pokemon = pokemon.stream()
                    .filter(p -> p.getName().toLowerCase()
                            .contains(name.trim().toLowerCase()))
                    .toList();
        }

        if (region != null && !region.isBlank()) {
            pokemon = pokemon.stream()
                    .filter(p -> p.getRegion().equalsIgnoreCase(region.trim()))
                    .toList();
        }

        return pokemon;
    }
}