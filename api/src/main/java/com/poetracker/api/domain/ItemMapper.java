package com.poetracker.api.domain;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO toDTO(Item item){
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setUrl(item.getUrl());
        dto.setCreatedAt(item.getCreatedAt());
        return dto;
    }
}
