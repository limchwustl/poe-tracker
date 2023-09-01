package com.poetracker.api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select new com.poetracker.api.domain.ItemDTO(b.id, b.title, b.url, b.createdAt) from Item b")
    Page<ItemDTO> findItems(Pageable pageable);
}
