package tech.bittercoffee.wechat.api.trade.models;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class CompactLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
	private static final long serialVersionUID = 1L;

	public CompactLocalDateTimeDeserializer() {
		super(DateTimeFormatter.ofPattern("uuuuMMddHHmmss"));
	}

}
