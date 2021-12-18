package tech.bittercoffee.wechat.api.trade.models.request;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CompactLocalDateSerializer extends LocalDateSerializer {
	private static final long serialVersionUID = 1L;

	public CompactLocalDateSerializer() {
		super(DateTimeFormatter.ofPattern("uuuuMMdd"));
	}

}
