package com.improving;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameContext {
    private final ConcurrentHashMap<String, InputOutput> connections = new ConcurrentHashMap<>();

    public Map<String, InputOutput> getPlayerConnections() {
        return connections;
    }
}
