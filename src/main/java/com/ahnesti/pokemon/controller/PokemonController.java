package com.ahnesti.pokemon.controller;

import com.ahnesti.pokemon.entity.PokemonCharacter;
import com.ahnesti.pokemon.service.PokemonService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // GET all Pokemon, filter by region, or search by name
    @GetMapping
    public ResponseEntity<List<PokemonCharacter>> getAllPokemon(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String name) {

        if (name != null) {
            return ResponseEntity.ok(pokemonService.searchPokemonByName(name));
        }

        if (region != null) {
            return ResponseEntity.ok(pokemonService.getPokemonByRegion(region));
        }

        return ResponseEntity.ok(pokemonService.getAllPokemon());
    }

    // GET Pokemon by ID
    @GetMapping("/{id}")
    public ResponseEntity<PokemonCharacter> getPokemonById(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    // POST new Pokemon
    @PostMapping
    public ResponseEntity<PokemonCharacter> addPokemon(
            @Valid @RequestBody PokemonCharacter pokemon) {

        PokemonCharacter savedPokemon = pokemonService.addPokemon(pokemon);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemon);
    }

    // PUT update existing Pokemon
    @PutMapping("/{id}")
    public ResponseEntity<PokemonCharacter> updatePokemon(
            @PathVariable Long id,
            @Valid @RequestBody PokemonCharacter pokemon) {

        return ResponseEntity.ok(pokemonService.updatePokemon(id, pokemon));
    }

    // DELETE Pokemon
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {

        pokemonService.deletePokemon(id);

        return ResponseEntity.noContent().build();
    }
}