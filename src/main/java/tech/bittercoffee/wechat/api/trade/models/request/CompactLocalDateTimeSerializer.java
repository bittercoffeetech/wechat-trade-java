package tech.bittercoffee.wechat.api.trade.models.request;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class CompactLocalDateTimeSerializer extends LocalDateTimeSerializer {
	private static final long serialVersionUID = 1L;

	public CompactLocalDateTimeSerializer() {
		super(DateTimeFormatter.ofPattern("uuuuMMddHHmmss"));
	}

}
