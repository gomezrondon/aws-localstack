package com.example.awslocalstack;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.net.URI;

@ConfigurationProperties("s3")
public class S3bucketProperties {
    private URI endpointURI;

    public URI getEndpointURI() {
        return endpointURI;
    }

    public void setEndpointURI(URI endpointURI) {
        this.endpointURI = endpointURI;
    }
}
