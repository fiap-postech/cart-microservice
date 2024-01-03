package br.com.fiap.tech.challenge.adapter.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6946814991276139633L;

    private String id;
    private BigDecimal total;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private CustomerDTO customer;
    private List<CartItemDTO> items;
}
