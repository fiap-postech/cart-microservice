package br.com.fiap.tech.challenge.adapter.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3016537574158332062L;

    private ProductDTO product;
    private int quantity;
    private BigDecimal total;
    private BigDecimal subtotal;
    private BigDecimal discount;
}
