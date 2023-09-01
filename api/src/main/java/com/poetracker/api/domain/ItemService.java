package com.poetracker.api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        Page<ItemDTO> itemPage = repository.findItems(pageable);
        return new ItemsDTO(itemPage);
    }
}
