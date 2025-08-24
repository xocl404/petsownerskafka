package org.xocl404;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerDto {
    private Long id;

    @NotBlank
    private String name;

    @PastOrPresent
    private LocalDate birthDate;

    @Builder.Default
    private List<Long> petIds = new ArrayList<>();
}