package com.omkar.porfolio_backend.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String message;
    private List<Map<String, String>> history;

    // getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<Map<String, String>> getHistory() { return history; }
    public void setHistory(List<Map<String, String>> history) { this.history = history; }
}