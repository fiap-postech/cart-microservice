package br.com.fiap.tech.challenge.driven.cart;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashSet;

import static java.util.Arrays.asList;

@Configuration
@ComponentScan(basePackages = "br.com.fiap.tech.challenge.driven.cart")
@EnableAsync
@AllArgsConstructor
public class CartProducerConfiguration {

    public static final DefaultAWSCredentialsProviderChain CREDENTIALS_PROVIDER = new DefaultAWSCredentialsProviderChain();

    private static final String DEFAULT_REGION = "us-east-1";
    private static final String DEFAULT_LOCAL_ENDPOINT = "http://localhost:4566";
    private static final String[] DEFAULT_LOCAL_PROFILES = new String[]{"local", "test"};
    private static final String AWS_REGION_KEY = "aws.region";
    private static final String AWS_LOCAL_ENDPOINT_KEY = "aws.local.endpoint";
    private static final String AWS_LOCAL_PROFILES_KEY = "aws.local.profiles";

    private final Environment env;

    @Bean
    @Primary
    public AmazonSNSAsync amazonSNSAsync() {
        var activeProfiles = env.getActiveProfiles();
        var localProfiles = env.getProperty(AWS_LOCAL_PROFILES_KEY, String[].class, DEFAULT_LOCAL_PROFILES);
        var isLocalProfile = new HashSet<>(asList(localProfiles)).containsAll(asList(activeProfiles));

        var region = env.getProperty(AWS_REGION_KEY, DEFAULT_REGION);

        var builder = AmazonSNSAsyncClientBuilder
                .standard()
                .withCredentials(CREDENTIALS_PROVIDER);

        if (isLocalProfile) {
            var localEndpoint = env.getProperty(AWS_LOCAL_ENDPOINT_KEY, DEFAULT_LOCAL_ENDPOINT);
            builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localEndpoint, region));
        } else {
           builder.withRegion(Regions.fromName(region));
        }

        return builder.build();
    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNSAsync amazonSNSAsync) {
        return new NotificationMessagingTemplate(amazonSNSAsync);
    }
}