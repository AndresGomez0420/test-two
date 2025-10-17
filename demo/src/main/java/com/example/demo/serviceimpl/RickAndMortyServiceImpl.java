package com.example.demo.serviceimpl;

import com.example.demo.dto.RequestDTO;
import com.example.demo.entity.Character;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.service.RickAndMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {

    private final CharacterRepository characterRepository;

    @Override
    public List<Character> getAllCharacters() {
        try {
            return characterRepository.findAll();
        } catch (DataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener todos los characters: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Character> getCharacterById(Integer id) {
        try {
            return characterRepository.findById(id);
        } catch (DataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener character con ID " + id + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveNewCharacter(RequestDTO requestDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (requestDTO.getName() == null || requestDTO.getName().isBlank()) {
                response.put("error", "El nombre del personaje es obligatorio");
                return ResponseEntity.badRequest().body(response);
            }

            // Crear la entidad a partir del DTO
            Character character = new Character();
            character.setName(requestDTO.getName());
            character.setStatus(requestDTO.getStatus());
            character.setSpecies(requestDTO.getSpecies());
            character.setType(requestDTO.getType());
            character.setGender(requestDTO.getGender());
            character.setOrigin(requestDTO.getOrigin());
            character.setLocation(requestDTO.getLocation());
            character.setImage(requestDTO.getImage());
            character.setEpisode(requestDTO.getEpisode());

            Character savedCharacter = characterRepository.save(character);

            response.put("message", "Character created successfully");
            response.put("character", savedCharacter);

            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public Optional<Character> updateCharacter(Integer id, Character dto) {
        try {
            return characterRepository.findById(id).map(character -> {
                character.setName(dto.getName());
                character.setStatus(dto.getStatus());
                character.setSpecies(dto.getSpecies());
                character.setType(dto.getType());
                character.setGender(dto.getGender());
                character.setOrigin(dto.getOrigin());
                character.setLocation(dto.getLocation());
                character.setImage(dto.getImage());
                character.setEpisode(dto.getEpisode());
                return characterRepository.save(character);
            });
        } catch (DataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteCharacter(Integer id) {
        try {
            return characterRepository.findById(id).map(character -> {
                characterRepository.delete(character);
                return true;
            }).orElse(false);
        } catch (DataAccessException e) {
            System.err.println("Error de acceso a la base de datos al eliminar el character: " + e.getMostSpecificCause().getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al eliminar el character con ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
