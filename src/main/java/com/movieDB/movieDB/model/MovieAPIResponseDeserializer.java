package com.movieDB.movieDB.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class MovieAPIResponseDeserializer extends JsonDeserializer<MovieAPIResponse> {

    @Override
    public MovieAPIResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new MovieAPIResponse(
                node.get("popularity").asLong(),
                node.get("voteAverage").asDouble(),
                node.get("voteCount").asInt());
    }
}
