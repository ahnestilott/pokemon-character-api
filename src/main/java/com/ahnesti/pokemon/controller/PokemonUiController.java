package com.ahnesti.pokemon.controller;

import com.ahnesti.pokemon.entity.PokemonCharacter;
import com.ahnesti.pokemon.service.PokemonService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PokemonUiController {

    private final PokemonService pokemonService;

    public PokemonUiController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // Display all Pokemon
    @GetMapping("/")
    public String showGallery(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String region,
            Model model) {

        model.addAttribute("characters", pokemonService.searchPokemon(name, region));

        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("region", region == null ? "" : region);

        return "index";
    }

    // Display one Pokemon
    @GetMapping("/characters/{id}")
    public String showDetails(@PathVariable Long id, Model model) {

        try {
            PokemonCharacter pokemon = pokemonService.getPokemonById(id);

            model.addAttribute("pokemon", pokemon);

            return "details";

        } catch (ResponseStatusException e) {

            if (e.getStatusCode().value() != 404) {
                throw e;
            }

            model.addAttribute("errorMessage", "Sorry! That Pokémon could not be found.");

            return "error";
        }
    }
}