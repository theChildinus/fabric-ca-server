/*
 * Copyright 2019 IBM All Rights Reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.gateway.impl;

import org.hyperledger.fabric.gateway.GatewayException;
import org.hyperledger.fabric.gateway.TestUtils;
import org.hyperledger.fabric.gateway.spi.Query;
import org.hyperledger.fabric.gateway.spi.QueryHandler;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RoundRobinQueryHandlerTest {
    private final TestUtils testUtils = TestUtils.getInstance();
    private Peer peer1;
    private Peer peer2;
    private ProposalResponse successfulResponse;
    private ProposalResponse failureResponse;

    @BeforeEach
    public void beforeEach() {
        peer1 = testUtils.newMockPeer("peer1");
        peer2 = testUtils.newMockPeer("peer2");
        successfulResponse = testUtils.newSuccessfulProposalResponse(new byte[0]);
        failureResponse = testUtils.newFailureProposalResponse("Epic fail");
    }

    @Test
    public void throws_if_no_peers_supplied() {
        assertThatThrownBy(() -> new RoundRobinQueryHandler(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void returns_successful_peer_response() throws GatewayException {
        Query query = mock(Query.class);
        when(query.evaluate(peer1)).thenReturn(successfulResponse);

        QueryHandler handler = new RoundRobinQueryHandler(Arrays.asList(peer1));
        ProposalResponse result = handler.evaluate(query);

        assertThat(result).isEqualTo(successfulResponse);
    }

    @Test
    public void throws_if_all_peers_fail() throws GatewayException {
        Query query = mock(Query.class);
        when(query.evaluate(peer1)).thenReturn(failureResponse);

        QueryHandler handler = new RoundRobinQueryHandler(Arrays.asList(peer1));
        assertThatThrownBy(() -> handler.evaluate(query))
                .isInstanceOf(GatewayException.class)
                .hasMessageContaining(failureResponse.getMessage());
    }

    @Test
    public void different_peers_for_two_successful_queries() throws GatewayException {
        Query query = mock(Query.class);
        when(query.evaluate(any(Peer.class))).thenReturn(successfulResponse);

        QueryHandler handler = new RoundRobinQueryHandler(Arrays.asList(peer1, peer2));
        handler.evaluate(query);
        handler.evaluate(query);

        ArgumentCaptor<Peer> argument = ArgumentCaptor.forClass(Peer.class);
        verify(query, times(2)).evaluate(argument.capture());
        Peer arg1 = argument.getAllValues().get(0);
        Peer arg2 = argument.getAllValues().get(1);
        assertThat(arg1).isNotSameAs(arg2);
    }

    @Test
    public void failover_on_peer_failure() throws GatewayException {
        Query query = mock(Query.class);
        when(query.evaluate(any(Peer.class)))
                .thenReturn(failureResponse)
                .thenReturn(successfulResponse);

        QueryHandler handler = new RoundRobinQueryHandler(Arrays.asList(peer1, peer2));
        handler.evaluate(query);

        verify(query, times(2)).evaluate(any(Peer.class));
    }
}