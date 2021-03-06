package com.mocadev.moviebuddy;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-09-24
 **/
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public static class CommandNotFoundException extends ApplicationException {

		public CommandNotFoundException() {
			super("command not found.");
		}

	}

	public static class UndefinedCommandActionException extends ApplicationException {

		public UndefinedCommandActionException() {
			super("command action is undefined.");
		}

	}

	public static class InvalidCommandArgumentsException extends ApplicationException {

		public InvalidCommandArgumentsException() {
			super("input error, please try again!");
		}

		public InvalidCommandArgumentsException(String message) {
			super(message);
		}

		public InvalidCommandArgumentsException(Throwable cause) {
			super(String.format("%s: %s", cause.getClass().getSimpleName(), cause.getMessage()),
				cause);
		}

	}
}
