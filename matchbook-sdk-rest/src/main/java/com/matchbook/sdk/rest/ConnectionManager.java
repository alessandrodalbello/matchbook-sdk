package com.matchbook.sdk.rest;

import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.configs.wrappers.HttpClientWrapper;
import com.matchbook.sdk.rest.configs.wrappers.SerializerWrapper;

public final class ConnectionManager {

    private final ClientConfig clientConfig;
    private final HttpClient httpClient;
    private final Serializer serializer;

    public ConnectionManager(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;

        this.httpClient = new HttpClientWrapper(clientConfig.getHttpConfig());
        this.serializer = new SerializerWrapper();
    }

    public ClientConfig getClientConfig() {
        return clientConfig;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public Serializer getSerializer() {
        return serializer;
    }

}