package org.xocl404.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.xocl404.*;
import org.xocl404.kafka.WebGatewayProducer;
import org.xocl404.service.ResponseStore;
import org.xocl404.PageableDto;
import org.xocl404.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {
    private final WebGatewayProducer webGatewayProducer;

    private final ResponseStore responseStore;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<PetDto> create(@Valid @RequestBody PetDto dto) throws InterruptedException, AccessDeniedException {
        userService.checkUser(dto.getOwnerId());
        webGatewayProducer.sendDto("create_pet", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseStore.getPetResponse());
    }

    @PutMapping
    public ResponseEntity<PetDto> update(@Valid @RequestBody PetDto dto) throws InterruptedException, AccessDeniedException {
        userService.checkUser(dto.getOwnerId());
        webGatewayProducer.sendDto("update_pet", dto);
        return ResponseEntity.ok(responseStore.getPetResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws AccessDeniedException{
        userService.checkAdmin();
        webGatewayProducer.sendId("delete_pet_by_id", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() throws AccessDeniedException{
        userService.checkAdmin();
        webGatewayProducer.sendNothing("delete_all_pets");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getById(@PathVariable Long id) throws InterruptedException {
        webGatewayProducer.sendId("get_pet_by_id", id);
        return ResponseEntity.ok(responseStore.getPetResponse());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<PetDto>> getByName(
            @PathVariable String name
            , Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendDto("get_pet_by_name", new StringDto(name, PageableDto.fromPageable(pageable)));
        return ResponseEntity.ok(responseStore.getPetPageResponse());
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<Page<PetDto>> getByBreed(
            @PathVariable String breed
            , Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendDto("get_pet_by_breed", new StringDto(breed, PageableDto.fromPageable(pageable)));
        return ResponseEntity.ok(responseStore.getPetPageResponse());
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Page<PetDto>> getByColor(
            @PathVariable String color
            , Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendDto("get_pet_by_color", new ColorDto(Color.valueOf(color), PageableDto.fromPageable(pageable)));
        return ResponseEntity.ok(responseStore.getPetPageResponse());
    }

    @GetMapping("/birthdate/{startDate}/{endDate}")
    public ResponseEntity<Page<PetDto>> getByBirthDateBetween(
            @PathVariable String startDate
            , @PathVariable String endDate
            , Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendDto("get_pet_by_birthdate_between"
                , new BirthDateDto(LocalDate.parse(startDate), LocalDate.parse(endDate), PageableDto.fromPageable(pageable)));
        return ResponseEntity.ok(responseStore.getPetPageResponse());
    }

    @GetMapping
    public ResponseEntity<Page<PetDto>> getAll(Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendPageable("get_all_pets", PageableDto.fromPageable(pageable));
        return ResponseEntity.ok(responseStore.getPetPageResponse());
    }
}
