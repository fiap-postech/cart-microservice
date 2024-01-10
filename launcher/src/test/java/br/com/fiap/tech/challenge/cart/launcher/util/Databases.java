package br.com.fiap.tech.challenge.cart.launcher.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Databases {

    public static String replaceDockerHost(String url) {
        return url.replace("localhost", "host.docker.internal");
    }

}
