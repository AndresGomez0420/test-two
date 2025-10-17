package com.example.demo.controller;

import com.example.demo.dto.RequestDTO;
import com.example.demo.entity.Character;
import com.example.demo.service.RickAndMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rick-and-morty/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RickAndMortyController {

    @Autowired
    private RickAndMortyService rickAndMortyService;

    @GetMapping("/character")
    public List<Character> getAllCharacters() {
        return rickAndMortyService.getAllCharacters();
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Integer id) {
        return rickAndMortyService.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save-user")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody RequestDTO requestDTO) {
        return rickAndMortyService.saveNewCharacter(requestDTO);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Integer id, @RequestBody Character dto) {
        return rickAndMortyService.updateCharacter(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Integer id) {
        boolean deleted = rickAndMortyService.deleteCharacter(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
