package com.example.validationspringboot.util;

import com.example.validationspringboot.entity.User;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomDeserializer extends StdDeserializer<User> {
    protected CustomDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        User user = new User();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("groups").asText();
        user.setName(name);
        return user;
    }
}
