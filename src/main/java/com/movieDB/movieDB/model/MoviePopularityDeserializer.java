package com.movieDB.movieDB.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class MoviePopularityDeserializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        String longString = parser.getText();
        if (longString.contains(".")) {
            longString = longString.replace(".", "");
        }
        return Long.valueOf(longString);
    }

}
