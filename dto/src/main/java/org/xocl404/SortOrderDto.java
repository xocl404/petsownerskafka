package org.xocl404;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortOrderDto {
    private String property;
    private String direction;
}