package org.xocl404;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Data
public class PageDto<T> {
    private List<T> content;
    private PageableDto pageable;
    private long totalElements;

    public static <T> PageDto<T> fromPage(Page<T> page) {
        PageDto<T> dto = new PageDto<>();
        dto.setContent(page.getContent());
        dto.setPageable(PageableDto.fromPageable(page.getPageable()));
        dto.setTotalElements(page.getTotalElements());
        return dto;
    }

    public Page<T> toPage() {
        return new PageImpl<>(
                content,
                pageable.toPageable(),
                totalElements
        );
    }
}
