package com.poetracker.api.domain;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO toDTO(Item item){
        return new ItemDTO(
                item.getId(),
                item.getTitle(),
                item.getUrl(),
                item.getCreatedAt()
        );
    }
}
