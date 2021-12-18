package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class BooleanDeserializer extends StdScalarDeserializer<Boolean> {
	private static final long serialVersionUID = 1L;

	protected BooleanDeserializer() {
		super(Boolean.class);
	}

	@Override
	public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if ("Y".equals(p.getValueAsString())) {
			return Boolean.TRUE;
		} else if ("N".equals(p.getValueAsString())) {
			return Boolean.FALSE;
		} else {
			return null;
		}
	}

}
