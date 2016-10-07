package com.cooksys.ftd.assignment.day.one;

/**
 * FizzBuzz is an old programming exercise. The goal is to iterate over a range
 * of numbers and print a message about each number's divisibility.
 * <p>
 * The message is generated the following way: *) if the number is divisible by
 * three, the message is `Fizz` *) if the number is divisible by five, the
 * message is `Buzz` *) if the number is divisible by both three and five, the
 * message is `FizzBuzz` *) otherwise, no message is produced
 * <p>
 * The exact message format for this assignment is specified in the `message(n)`
 * method.
 */
public class FizzBuzz {

	/**
	 * compute weather a given number is divisible by 3
	 * 
	 * @param n
	 * @return true if the number is divisible by 3 and false if not
	 */
	private static boolean fizz(int n) {
		if (n % 3 == 0)
			return true;
		else
			return false;
	}

	/**
	 * compute weather a given number is divisible by 5
	 * 
	 * @param n
	 * @return true if the number is divisible by 5 and false if not
	 */
	private static boolean buzz(int n) {
		if (n % 5 == 0)
			return true;
		else
			return false;
	}

	/**
	 * Checks whether a given int `a` is evenly divisible by a given int `b` or
	 * not. For example, `divides(4, 2)` returns `true` and `divides(4, 3)`
	 * returns `false`.
	 *
	 * @param a
	 *            the number to be divided
	 * @param b
	 *            the number to divide by
	 * @return `true` if a is evenly divisible by b, `false` otherwise
	 * @throws IllegalArgumentException
	 *             if b is zero
	 */
	public static boolean divides(int a, int b) throws IllegalArgumentException {
		if (b == 0)
			throw new IllegalArgumentException("division by 0 is not allowed");
		else {
			double result = a % b;
			if (result == 0)
				return true;
			else
				return false;
		}
	}

	/**
	 * Generates a divisibility message for a given number. Returns null if the
	 * given number is not divisible by 3 or 5. Message formatting examples: 1
	 * -> null // not divisible by 3 or 5 3 -> "3: Fizz" // divisible by only 3
	 * 5 -> "5: Buzz" // divisible by only 5 15 -> "15: FizzBuzz" // divisible
	 * by both three and five
	 *
	 * @param n
	 *            the number to generate a message for
	 * @return a message according to the format above, or null if n is not
	 *         divisible by either 3 or 5
	 */
	public static String message(int n) {
		if (fizz(n)) {
			if (buzz(n)) {
				return (n + ": FizzBuzz");
			} else
				return n + ": Fizz";
		} else if (buzz(n))
			return n + ": Buzz";
		else
			return null;
	}

	/**
	 * Generates an array of messages to print for a given range of numbers. If
	 * there is no message for an individual number (i.e., `message(n)` returns
	 * null), it should be excluded from the resulting array.
	 *
	 * @param start
	 *            the number to start with (inclusive)
	 * @param end
	 *            the number to end with (exclusive)
	 * @return an array of divisibility messages
	 * @throws IllegalArgumentException
	 *             if the given end is less than the given start
	 */
	public static String[] messages(int start, int end) throws IllegalArgumentException {
		if (end < start) {
			throw new IllegalArgumentException("start cannot be less than end");
		} else {
			String[] messages = new String[end - start];
			int messagesIndex = 0;
			for (int i = start; i < end; i++) {
				if (message(i) != null) {
					messages[messagesIndex] = message(i);
					messagesIndex += 1;
				}
			}
			String returnedMessages[] = new String[messagesIndex];
			for (int i = 0; i < messagesIndex; i++) {
				returnedMessages[i] = messages[i];
			}
			return returnedMessages;
		}
	}

	/**
	 * For this main method, iterate over the numbers 1 through 115 and print
	 * the relevant messages to sysout
	 */
	public static void main(String[] args) {
		for (String x : messages(1, 115)) {
			System.out.println(x);
		}
	}

}
