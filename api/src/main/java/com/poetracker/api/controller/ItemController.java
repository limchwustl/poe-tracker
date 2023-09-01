package com.poetracker.api.controller;

import com.poetracker.api.domain.Item;
import com.poetracker.api.domain.ItemService;
import com.poetracker.api.domain.ItemsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController  {
    private final ItemService itemService;
    @GetMapping
    public ItemsDTO getItems(@RequestParam(name = "page", defaultValue = "1") Integer page){
        return itemService.getItems(page);

    }


}
