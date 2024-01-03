package br.com.fiap.tech.challenge.rest.util;

import br.com.fiap.tech.challenge.application.util.Page;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pages {

    public static Page of(Pageable pageable){
        return new Page(pageable.getPageNumber(), pageable.getPageSize());
    }

}
