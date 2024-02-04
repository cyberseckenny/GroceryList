package me.grocery.grocerylist.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;

public class JsonChatCompletionRequest extends ChatCompletionRequest {
    @JsonProperty("response_format")
    String responseFormat = "json_object";
}
