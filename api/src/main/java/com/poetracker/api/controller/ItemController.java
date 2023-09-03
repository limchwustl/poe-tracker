package com.poetracker.api.controller;

import com.poetracker.api.domain.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController  {
    private final ItemService itemService;
    @GetMapping
    public ItemsDTO getItems(@RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "query", defaultValue = "") String query){
        if (query == null || query.trim().length() == 0) {
            return itemService.getItems(page);
        }
        return itemService.searchItems(query, page);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody @Valid CreateItemRequest request){
    return itemService.createItem(request);

    }


}
