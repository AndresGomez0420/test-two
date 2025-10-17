package com.example.demo.service;

import com.example.demo.dto.RequestDTO;
import com.example.demo.entity.Character;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RickAndMortyService {
    List<Character> getAllCharacters();

    Optional<Character> getCharacterById(Integer id);

    ResponseEntity<Map<String, Object>> saveNewCharacter(RequestDTO requestDTO);

    Optional<Character> updateCharacter(Integer id, Character dto);

    boolean deleteCharacter(Integer id);
}
