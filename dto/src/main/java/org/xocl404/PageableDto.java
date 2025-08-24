package org.xocl404;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PageableDto {
    private int page;
    private int size;
    private List<SortOrderDto> sort;

    public Pageable toPageable() {
        if (sort == null || sort.isEmpty()) {
            return PageRequest.of(page, size);
        }

        return PageRequest.of(
                page,
                size,
                Sort.by(sort.stream()
                        .map(order -> new Sort.Order(
                                Sort.Direction.fromString(order.getDirection()),
                                order.getProperty()))
                        .collect(Collectors.toList()))
        );
    }

    public static PageableDto fromPageable(Pageable pageable) {
        PageableDto dto = new PageableDto();
        dto.setPage(pageable.getPageNumber());
        dto.setSize(pageable.getPageSize());

        if (pageable.getSort().isSorted()) {
            dto.setSort(pageable.getSort().stream()
                    .map(order -> new SortOrderDto(
                            order.getProperty(),
                            order.getDirection().name()))
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}