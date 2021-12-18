package tech.bittercoffee.wechat.api.trade.models.response;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class CompactLocalDateDeserializer extends LocalDateDeserializer {
	private static final long serialVersionUID = 1L;

	public CompactLocalDateDeserializer() {
		super(DateTimeFormatter.ofPattern("uuuuMMdd"));
	}

}
