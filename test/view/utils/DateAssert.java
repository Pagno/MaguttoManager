package view.utils;

import static org.fest.swing.util.Strings.areEqualOrMatch;
import static org.fest.swing.util.Strings.match;
import static org.fest.util.Strings.*;

import java.util.regex.Pattern;

import org.fest.assertions.*;

public class DateAssert extends Assert implements AssertExtension {

	private final String actual;

	static DateAssert assertThat(String s) {
		return new DateAssert(s);
	}

	static DateAssert verifyThat(String s) {
		return new DateAssert(s);
	}

	DateAssert(String actual) {
		this.actual = actual;
	}

	DateAssert as(String description) {
		description(description);
		return this;
	}

	DateAssert as(Description description) {
		description(description);
		return this;
	}

	DateAssert isEqualOrMatches(String s) {
		if (areEqualOrMatch(s, actual))
			return this;
		throw failure(concat("actual value:<", quote(actual),
				"> is not equal to or does not match pattern:<", quote(s), ">"));
	}

	DateAssert matches(Pattern pattern) {
		if (match(pattern, actual))
			return this;
		throw failure(concat("actual value:<", quote(actual),
				"> does not match pattern:<", quote(pattern.pattern()), ">"));
	}

}
