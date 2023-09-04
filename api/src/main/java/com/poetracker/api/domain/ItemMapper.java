package com.poetracker.api.domain;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO toDTO(Item item){
        return new ItemDTO(
                item.getId(),
                item.getLeague(),
                item.getType(),
                item.getItem_name(),
                item.getCreatedAt()
        );
    }
}
