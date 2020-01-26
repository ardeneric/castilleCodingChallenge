package com.coding.utils;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
	@Override
	public OffsetDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {
		return OffsetDateTime.parse(arg0.getText());
	}
}
