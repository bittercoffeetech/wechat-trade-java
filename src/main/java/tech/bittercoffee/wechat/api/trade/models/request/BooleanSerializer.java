package tech.bittercoffee.wechat.api.trade.models.request;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class BooleanSerializer extends StdScalarSerializer<Boolean> {
	private static final long serialVersionUID = 1L;

	protected BooleanSerializer() {
		super(Boolean.class);
	}

	@Override
	public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Boolean.TRUE.equals(value)) {
			gen.writeString("Y");
		} else if (Boolean.FALSE.equals(value)) {
			gen.writeString("N");
		}
	}
}
