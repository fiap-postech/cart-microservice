package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import static br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisConfiguration.CACHE_REDIS_TTL;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { RedisCustomerMapper.class, RedisCartItemMapper.class })
public abstract class RedisCartMapper {

    @Autowired
    protected Environment env;

    @Mapping(target = "ttl", source = "cart", qualifiedByName = "getTtl")
    public abstract CartEntity toEntity(CartDTO cart);

    public abstract CartDTO toDTO(CartEntity entity);

    @Named("getTtl")
    Long getTtl(CartDTO cart){
        return env.getProperty(CACHE_REDIS_TTL, Long.class);
    }
}
