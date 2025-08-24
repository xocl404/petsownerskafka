package org.xocl404;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDto {
    private Long id;

    @NotBlank
    private String name;

    @PastOrPresent
    private LocalDate birthDate;

    @NotBlank
    private String breed;

    @NotNull
    private Color color;

    private Long ownerId;

    @Builder.Default
    private List<Long> friendIds = new ArrayList<>();
}