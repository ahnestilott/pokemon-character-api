package com.ahnesti.pokemon.repository;

import com.ahnesti.pokemon.entity.PokemonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PokemonRepository extends JpaRepository<PokemonCharacter, Long> {

    // Find Pokemon by region
    List<PokemonCharacter> findByRegionIgnoreCase(String region);

    // Search Pokemon by name (partial, case-insensitive)
    List<PokemonCharacter> findByNameContainingIgnoreCase(String name);
}