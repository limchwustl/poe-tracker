package com.poetracker.api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;
    private final ItemMapper itemMapper;
    @Transactional(readOnly = true)
    public ItemsDTO getItems(Integer page) {
        int pageNo = page < 1 ? 0 : page -1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        Page<ItemDTO> itemPage = repository.findBy(pageable);
        return new ItemsDTO(itemPage);
    }

    @Transactional(readOnly = true)
    public ItemsDTO searchItems(String query, Integer page) {
        int pageNo = page < 1 ? 0 : page -1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        Page<ItemDTO> itemPage = repository.searchItems(query, pageable);
        return new ItemsDTO(itemPage);
    }

    public ItemDTO createItem(CreateItemRequest request){
        Item item = new Item(null, request.getLeague(), request.getType(), request.getItem_name(), Instant.now());
        Item savedItem = repository.save(item);
        return itemMapper.toDTO(savedItem);
    }
}
