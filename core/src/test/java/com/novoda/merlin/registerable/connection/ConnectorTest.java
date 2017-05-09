package com.novoda.merlin.registerable.connection;

import com.novoda.merlin.registerable.MerlinConnector;
import com.novoda.merlin.registerable.MerlinRegisterer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConnectorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private MerlinConnector<Connectable> merlinConnector;

    private Connector connector;

    @Before
    public void setUp() throws Exception {
        merlinConnector = new MerlinRegisterer<>();
        connector = new Connector(merlinConnector);
    }

    @Test
    public void callbackRegisteredConnectables() throws Exception {
        Connectable connectable = mock(Connectable.class);
        merlinConnector.register(connectable);

        connector.onConnect();

        verify(connectable).onConnect();
    }

    @Test
    public void callbackAllRegistered() throws Exception {
        List<Connectable> connectables = createListOfConnectables();

        registerListOfConnectables(connectables);

        connector.onConnect();

        for (Connectable connectable : connectables) {
            verify(connectable).onConnect();
        }
    }

    private List<Connectable> createListOfConnectables() {
        List<Connectable> connectables = new ArrayList<>();
        initListOfConnectables(connectables);
        return connectables;
    }

    private void initListOfConnectables(List<Connectable> connectables) {
        for (int i = 0; i < 3; i++) {
            connectables.add(mock(Connectable.class));
        }
    }

    private void registerListOfConnectables(List<Connectable> connectables) {
        for (Connectable connectable : connectables) {
            merlinConnector.register(connectable);
        }
    }

}
