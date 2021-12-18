package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class RateDeserializer extends StdScalarDeserializer<Double> {
	private static final long serialVersionUID = 1L;

	protected RateDeserializer() {
		super(Boolean.class);
	}

	@Override
	public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		return NumberUtils.toDouble(StringUtils.remove(p.getValueAsString(), '%'));
	}

}
