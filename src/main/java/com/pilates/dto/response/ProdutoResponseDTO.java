package com.pilates.dto.response;

import com.pilates.models.Produto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProdutoResponseDTO {

    private String name;
    private Double price;

    public ProdutoResponseDTO(Produto produto) {
        this.name = produto.getName();
        this.price = produto.getPrice();
    }
}
