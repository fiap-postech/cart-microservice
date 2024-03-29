package br.com.fiap.tech.challenge.driven.cart;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.repository.CloseCartRepository;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class CloseCartRepositoryImpl implements CloseCartRepository {

    private static final String CLOSE_CART_SNS_NAME_KEY = "aws.sns.close-cart.name";

    private final SnsTemplate snsTemplate;
    private final Environment env;

    @Override
    public void close(CartDTO dto) {
        snsTemplate.convertAndSend(requireNonNull(env.getProperty(CLOSE_CART_SNS_NAME_KEY)), dto);
    }
}