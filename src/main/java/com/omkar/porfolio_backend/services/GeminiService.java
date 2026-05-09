package com.omkar.porfolio_backend.services;



import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    public String chat(String userMessage, List<Map<String, String>> history) {
        try {

            List<Message> messages = new ArrayList<>();


            if (history != null) {
                for (Map<String, String> msg : history) {
                    if ("user".equals(msg.get("role"))) {
                        messages.add(new UserMessage(msg.get("text")));
                    } else {
                        messages.add(new AssistantMessage(msg.get("text")));
                    }
                }
            }


            messages.add(new UserMessage(userMessage));

            return chatClient.prompt()
                    .messages(messages)
                    .call()
                    .content();
        } catch (Exception e) {
            System.err.println("Groq error: " + e.getMessage());
            throw e;
        }
    }

}