package com.example.awslocalstack;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableConfigurationProperties({DynamoProperties.class,S3bucketProperties.class})
public class AwsLocalstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsLocalstackApplication.class, args);
	}


	@Bean
	ApplicationRunner applicationRunner(DynamoDbClient dbClient, S3AsyncClient s3Client){
		return args -> {
			System.out.println("-- dynamo tables ---");
			dbClient.listTables().tableNames().forEach(System.out::println);
			System.out.println("-- S3 Buckets ---");
			s3Client.listBuckets().get().buckets().forEach(System.out::println);
		};
	}

}
