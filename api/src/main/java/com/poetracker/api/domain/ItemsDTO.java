package com.poetracker.api.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
@Setter
@Getter
public class ItemsDTO {
    private List<ItemDTO> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirst")
    private boolean isFirst;
    @JsonProperty("isLast")
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public ItemsDTO(Page<ItemDTO> itemPage){
        this.setData(itemPage.getContent());
        this.setTotalElements(itemPage.getTotalElements());
        this.setTotalPages(itemPage.getTotalPages());
        this.setCurrentPage(itemPage.getNumber() + 1);
        this.setFirst(itemPage.isFirst());
        this.setLast(itemPage.isLast());
        this.setHasNext(itemPage.hasNext());
        this.setHasPrevious(itemPage.hasPrevious());
    }

}
