package com.poetracker.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String league;
    private String type;
    private String item_name;
    private Instant createdAt;
}
