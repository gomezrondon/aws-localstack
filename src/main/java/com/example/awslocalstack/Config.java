package com.example.awslocalstack;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Configuration
public class Config {

    // --------------- S3 Bucket -------------
    @Bean
    @Profile("default")
    S3AsyncClient S3ClientProd(){
        return S3AsyncClient.create();

    }

    @Bean
    @Profile("sandbox")
    @ConditionalOnMissingBean(name = "S3ClientProd")
    S3AsyncClient S3Client(S3bucketProperties properties){
        return S3AsyncClient.builder()
                .endpointOverride(properties.getEndpointURI())
                .build();
    }


    // --------------- dynamoDB ----------------
    @Bean
    @Profile("default")
    DynamoDbClient dynamoDbClientProd(){
        	return DynamoDbClient.create();

    }


    @Bean
    @Profile("sandbox")
    @ConditionalOnMissingBean(name = "dynamoDbClientProd")
    DynamoDbClient dynamoDbClient(DynamoProperties properties){
         return DynamoDbClient.builder()
                .endpointOverride(properties.getEndpointURI())
                .build();
    }

}
