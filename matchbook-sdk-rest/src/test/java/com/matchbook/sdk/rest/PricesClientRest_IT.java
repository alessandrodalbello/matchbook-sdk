package com.matchbook.sdk.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesRequest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class PricesClientRest_IT extends MatchbookSDKClientRest_IT<PricesClientRest> {

    @Override
    protected PricesClientRest newClientRest(ConnectionManager connectionManager) {
        return new PricesClientRest(connectionManager);
    }

    @Test
    void getPricesTest() throws InterruptedException {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010/runners/395729860800010/prices";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/prices/getPricesSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        PricesRequest pricesRequest = new PricesRequest
                .Builder(395729780570010L, 395729860260010L, 395729860800010L)
                .build();

        clientRest.getPrices(pricesRequest, new StreamObserver<Price>() {

            @Override
            public void onNext(Price price) {
                assertNotNull(price);
                assertNotNull(price.getOddsType());
                assertNotNull(price.getOdds());
                countDownLatch.countDown();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

}