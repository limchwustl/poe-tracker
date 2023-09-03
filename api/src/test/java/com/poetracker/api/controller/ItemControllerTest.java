package com.poetracker.api.controller;

import com.poetracker.api.domain.Item;
import com.poetracker.api.domain.ItemRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class ItemControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ItemRepository itemRepository;

    private List<Item> items;

    @BeforeEach
    void setUp() {
        itemRepository.deleteAllInBatch();
        items = new ArrayList<>();

        items.add(new Item(null, "SivaLabs", "https://sivalabs.in", Instant.now()));
        items.add(new Item(null, "SpringBlog", "https://spring.io/blog", Instant.now()));
        items.add(new Item(null, "Quarkus", "https://quarkus.io/", Instant.now()));
        items.add(new Item(null, "Micronaut", "https://micronaut.io/", Instant.now()));
        items.add(new Item(null, "JOOQ", "https://www.jooq.org/", Instant.now()));
        items.add(new Item(null, "VladMihalcea", "https://vladmihalcea.com", Instant.now()));
        items.add(new Item(null, "Thoughts On Java", "https://thorben-janssen.com/", Instant.now()));
        items.add(new Item(null, "DZone", "https://dzone.com/", Instant.now()));
        items.add(new Item(null, "DevOpsitems", "http://www.devopsitems.com/", Instant.now()));
        items.add(new Item(null, "Kubernetes docs", "https://kubernetes.io/docs/home/", Instant.now()));
        items.add(new Item(null, "Expressjs", "https://expressjs.com/", Instant.now()));
        items.add(new Item(null, "Marcobehler", "https://www.marcobehler.com", Instant.now()));
        items.add(new Item(null, "baeldung", "https://www.baeldung.com", Instant.now()));
        items.add(new Item(null, "devglan", "https://www.devglan.com", Instant.now()));
        items.add(new Item(null, "linuxize", "https://linuxize.com", Instant.now()));

        itemRepository.saveAll(items);
    }

    @ParameterizedTest
    @CsvSource({
            "1,15,2,1,true,false,true,false",
            "2,15,2,2,false,true,false,true"
    })
    void shouldGetItems(int pageNo, int totalElements, int totalPages, int currentPage,
                        boolean isFirst, boolean isLast,
                        boolean hasNext, boolean hasPrevious) throws Exception{
        mvc.perform(get("/api/items?page="+pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)));
    }

    @Test
    void shouldCreateItemSuccessfully() throws Exception {
        this.mvc.perform(
                        post("/api/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
            {
                "title": "SivaLabs Blog",
                "url": "https://sivalabs.in"
            }
            """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("SivaLabs Blog")))
                .andExpect(jsonPath("$.url", is("https://sivalabs.in")));
    }

}