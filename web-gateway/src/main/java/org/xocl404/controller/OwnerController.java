package org.xocl404.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.xocl404.BirthDateDto;
import org.xocl404.OwnerDto;
import org.xocl404.PageableDto;
import org.xocl404.StringDto;
import org.xocl404.kafka.WebGatewayProducer;
import org.xocl404.service.ResponseStore;
import org.xocl404.service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final WebGatewayProducer webGatewayProducer;

    private final ResponseStore responseStore;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<OwnerDto> create(@Valid @RequestBody OwnerDto dto) throws InterruptedException, AccessDeniedException {
        userService.checkAdmin();
        webGatewayProducer.sendDto("create_owner", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseStore.getOwnerResponse());
    }

    @PutMapping
    public ResponseEntity<OwnerDto> update(@Valid @RequestBody OwnerDto dto) throws InterruptedException, AccessDeniedException {
        userService.checkUser(dto.getId());
        webGatewayProducer.sendDto("update_owner", dto);
        return ResponseEntity.ok(responseStore.getOwnerResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws AccessDeniedException {
        userService.checkAdmin();
        webGatewayProducer.sendId("delete_owner_by_id", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() throws AccessDeniedException {
        userService.checkAdmin();
        webGatewayProducer.sendNothing("delete_all_owners");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable Long id) throws InterruptedException {
        webGatewayProducer.sendId("get_owner_by_id", id);
        return ResponseEntity.ok(responseStore.getOwnerResponse());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<OwnerDto>> getByName(
            @PathVariable String name
            , Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendDto("get_owner_by_name", new StringDto(name, PageableDto.fromPageable(pageable)));
        return ResponseEntity.ok(responseStore.getOwnerPageResponse());
    }

    @GetMapping("/birthdate/{startDate}/{endDate}")
    public ResponseEntity<Page<OwnerDto>> getByBirthDateBetween(
            @PathVariable String startDate
            , @PathVariable String endDate
            , Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendDto("get_owner_by_birthdate_between"
                , new BirthDateDto(LocalDate.parse(startDate), LocalDate.parse(endDate), PageableDto.fromPageable(pageable)));
        return ResponseEntity.ok(responseStore.getOwnerPageResponse());
    }

    @GetMapping
    public ResponseEntity<Page<OwnerDto>> getAll(Pageable pageable) throws InterruptedException {
        webGatewayProducer.sendPageable("get_all_owners", PageableDto.fromPageable(pageable));
        return ResponseEntity.ok(responseStore.getOwnerPageResponse());
    }
}
