package org.xocl404;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorDto {
    @NotNull
    private Color color;

    @NotBlank
    private PageableDto pageableDto;
}
