package tech.bittercoffee.wechat.api.trade.models;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class BooleanDeserializer extends StdScalarDeserializer<Boolean> {
	private static final long serialVersionUID = 1L;

	protected BooleanDeserializer() {
		super(Boolean.class);
	}

	@Override
	public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		if ("Y".equals(p.getValueAsString())) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}
