package com.ahnesti.pokemon.controller;

import com.ahnesti.pokemon.service.PokemonService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PokemonUiController {

    private final PokemonService pokemonService;

    public PokemonUiController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // Display the Pokemon Gallery
    @GetMapping("/")
    public String showGallery(Model model) {

        model.addAttribute("characters", pokemonService.getAllPokemon());

        return "index";
    }
}