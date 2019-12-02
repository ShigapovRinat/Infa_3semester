package ru.javalab.protocol;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String command;
    private String token;
    private Map<String, String> payload;

    public Request() {
        this.payload = new HashMap<>();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, String> payload) {
        this.payload = payload;
    }

    public String getParameter(String name) {
        return payload.get(name);
    }

    public void setPayload(String key, String arg) {
        payload.put(key, arg);
    }

}
