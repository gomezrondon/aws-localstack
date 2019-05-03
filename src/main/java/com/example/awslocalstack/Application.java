package com.example.awslocalstack;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableConfigurationProperties({DynamoProperties.class,S3bucketProperties.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	ApplicationRunner applicationRunner(DynamoDbClient dbClient, S3AsyncClient s3Client){
		return args -> {

			System.out.println("-- insert item into dynamo table ---");
			Map<String, AttributeValue> itemMap = new HashMap<>();
			itemMap.put("id", AttributeValue.builder().s("13987482").build());
			itemMap.put("name", AttributeValue.builder().s("javier gomez").build());
			dbClient.putItem(PutItemRequest.builder()
					.tableName("producto")
					.item(itemMap)
					.build());

			System.out.println("-- dynamo tables ---");
			dbClient.listTables().tableNames().forEach(System.out::println);
			System.out.println("-- S3 Buckets ---");
			s3Client.listBuckets().get().buckets().forEach(System.out::println);
		};
	}

}
