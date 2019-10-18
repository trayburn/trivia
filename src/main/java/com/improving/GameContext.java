package com.improving;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameContext {
    private final ConcurrentHashMap<String, InputOutput> connections = new ConcurrentHashMap<>();

    public Map<String, InputOutput> getPlayerConnections() {
        return connections;
    }
}
