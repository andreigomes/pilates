package com.pilates.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProdutoRequestDTO {

    private String name;
    private Double price;
}
