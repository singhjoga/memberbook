package com.punjuprogrammers.memberbook.bl.persistence;

public class ConverterUtil {
	public static String escapeSqlString(String str) {
		StringBuilder sBuilder = new StringBuilder(str.length() * 11 / 10);

		int stringLength = str.length();

		for (int i = 0; i < stringLength; ++i) {
			char c = str.charAt(i);

			switch (c) {
			case 0: /* Must be escaped for 'mysql' */
				sBuilder.append('\\');
				sBuilder.append('0');

				break;

			case '\n': /* Must be escaped for logs */
				sBuilder.append('\\');
				sBuilder.append('n');

				break;

			case '\r':
				sBuilder.append('\\');
				sBuilder.append('r');

				break;

			case '\\':
				sBuilder.append('\\');
				sBuilder.append('\\');

				break;

			case '\'':
				sBuilder.append('\\');
				sBuilder.append('\'');

				break;

			case '"': /* Better safe than sorry */
				sBuilder.append('\\');

				sBuilder.append('"');

				break;

			case '\032': /* This gives problems on Win32 */
				sBuilder.append('\\');
				sBuilder.append('Z');

				break;

			case '\u00a5':
			case '\u20a9':
				// escape characters interpreted as backslash by mysql
				// fall through

			default:
				sBuilder.append(c);
			}
		}

		return sBuilder.toString();
	}
}
