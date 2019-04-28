package com.example.awslocalstack;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties("dynamo")
public class DynamoProperties {
    private URI endpointURI;

    public URI getEndpointURI() {
        return endpointURI;
    }

    public void setEndpointURI(URI endpointURI) {
        this.endpointURI = endpointURI;
    }
}
