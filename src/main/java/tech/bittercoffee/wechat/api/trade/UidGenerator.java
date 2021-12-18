package tech.bittercoffee.wechat.api.trade;

import org.apache.commons.text.RandomStringGenerator;

public final class UidGenerator {
	private static final RandomStringGenerator uidGenerator = new RandomStringGenerator.Builder().withinRange('0', '9')
			.build();

	public static String next() {
		return uidGenerator.generate(32);
	}
}
