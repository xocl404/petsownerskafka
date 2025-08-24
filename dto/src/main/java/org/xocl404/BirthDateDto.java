package org.xocl404;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirthDateDto {
    @PastOrPresent
    private LocalDate startDate;

    @PastOrPresent
    private LocalDate endDate;

    @NotBlank
    private PageableDto pageableDto;
}
