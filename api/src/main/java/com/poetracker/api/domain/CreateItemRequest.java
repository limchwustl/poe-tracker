package com.poetracker.api.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateItemRequest {
    @NotEmpty(message = "League should not be empty")
    private String league;
    @NotEmpty(message = "Type should not be empty")
    private String type;
    @NotEmpty(message = "ItemName should not be empty")
    private String item_name;
}
