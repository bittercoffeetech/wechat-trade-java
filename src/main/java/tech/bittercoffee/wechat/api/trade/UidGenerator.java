package tech.bittercoffee.wechat.api.trade;

import org.apache.commons.text.RandomStringGenerator;

public final class UidGenerator {
	private static final RandomStringGenerator source = new RandomStringGenerator.Builder().withinRange('0', '9')
			.build();
	
	private UidGenerator() {
		
	}

	public static String next() {
		return source.generate(32);
	}
}
