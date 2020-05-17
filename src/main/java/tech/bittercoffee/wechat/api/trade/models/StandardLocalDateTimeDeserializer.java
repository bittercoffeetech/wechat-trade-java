package tech.bittercoffee.wechat.api.trade.models;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class StandardLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
	private static final long serialVersionUID = 1L;

	public StandardLocalDateTimeDeserializer() {
		super(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"));
	}

}
