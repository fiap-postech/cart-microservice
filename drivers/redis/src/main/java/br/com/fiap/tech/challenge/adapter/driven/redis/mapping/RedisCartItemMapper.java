package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ComboProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.dto.CartItemDTO;
import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class RedisCartItemMapper {

    @Autowired
    private RedisSingleProductMapper singleProductMapper;

    @Autowired
    private RedisComboProductMapper comboProductMapper;

    @Mapping(target = "product", source = "source", qualifiedByName = "getProductEntity")
    @Mapping(target = "price", source = "total")
    abstract CartItemEntity toEntity(CartItemDTO source);


    @Mapping(target = "product", source = "source", qualifiedByName = "getProductDTO")
    @Mapping(target = "total", source = "price")
    @Mapping(target = "subtotal", ignore = true)
    abstract CartItemDTO toDTO(CartItemEntity source);


    @Named("getProductEntity")
    ProductEntity getProductEntity(CartItemDTO item) {
        var product = item.getProduct();

        if (isNull(product)) return null;

        if (product instanceof ComboDTO comboDTO) {
            return comboProductMapper.toEntity(comboDTO);
        }

        return singleProductMapper.toEntity(product);
    }

    @Named("getProductDTO")
    ProductDTO getProductDTO(CartItemEntity entity) {
        var product = entity.getProduct();

        if (isNull(product)) return null;

        if (product instanceof ComboProductEntity comboEntity) {
            return comboProductMapper.toDTO(comboEntity);
        }

        return singleProductMapper.toDTO(product);
    }

}
