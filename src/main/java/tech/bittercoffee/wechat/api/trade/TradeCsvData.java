package tech.bittercoffee.wechat.api.trade;

import static org.apache.commons.beanutils.ConstructorUtils.invokeConstructor;
import static org.apache.commons.lang3.ArrayUtils.EMPTY_OBJECT_ARRAY;
import static org.apache.commons.lang3.RegExUtils.removeAll;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.apache.commons.io.LineIterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;

import tech.bittercoffee.wechat.api.trade.models.TradeSheetResponse;

public final class TradeCsvData {
	
	private static final CsvMapper csvMapper = new CsvMapper();
	static {
		csvMapper.enable(CsvParser.Feature.TRIM_SPACES);
		csvMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}
	
	@SuppressWarnings("unchecked")
	public static <S> S fromStream(Class<S> klass, final InputStream input) throws IllegalArgumentException, IOException {
		Predicate<String> isChineseWord = word -> Pattern.compile("[\u4e00-\u9fa5]").matcher(word).find();
		TradeSheetResponse<?, ?> result;
		try {
			result = (TradeSheetResponse<?, ?>) invokeConstructor(klass,
					EMPTY_OBJECT_ARRAY);
		} catch (ReflectiveOperationException roe) {
			return null;
		}
	
		try (LineIterator reader = new LineIterator(new InputStreamReader(input, StandardCharsets.UTF_8))) {
			AtomicBoolean isSummary = new AtomicBoolean(false);
	
			reader.next(); // Skip First Title
			reader.forEachRemaining(lineText -> {
				lineText = removeAll(lineText, "(`|\\r|\\n)");
				boolean isTitle = isChineseWord.test(lineText.substring(0, 1));
	
				if (!isSummary.get()) {
					isSummary.set(isTitle);
				}
				if (!isTitle) {
					try {
						if (isSummary.get()) {
							result.setSummary(csvMapper.readerFor(result.getSummaryType())
									.with(csvMapper.schemaFor(result.getSummaryType())).readValue(lineText));
						} else {
							result.getRecords().add(csvMapper.readerFor(result.getRecordType())
									.with(csvMapper.schemaFor(result.getRecordType())).readValue(lineText));
						}
					} catch (JsonProcessingException e) {
						// Ignore
					}
				}
			});
		}
	
		return (S) result;
	}

}
