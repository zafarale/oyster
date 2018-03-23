/**
 * 
 */
package uk.co.cirquare.oyster;

import uk.co.cirquare.oyster.core.error.ErrorCode;

/**
 * @author zali
 *
 */
public class OysterExpcetion extends Exception {
	private ErrorCode errorCode = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2734002708247906995L;

	/**
	 * @param errorCode
	 */
	public OysterExpcetion(final ErrorCode errorCode) {
		super("" + errorCode);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a domain exception storing the given error code and throwable for
	 * root cause analysis. This will throw an {@link IllegalArgumentException} if:
	 * <ol>
	 * <li>The given error code is <code>null</code> or empty.</li>
	 * <li>The given cause is <code>null</code>.</li>
	 * </ol>
	 * 
	 * @param errorCode
	 *            The given error code.
	 * @param cause
	 *            The given cause.
	 */
	public OysterExpcetion(final ErrorCode errorCode, final Throwable cause) {
		super("" + errorCode, cause);
		verifyCode(errorCode);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a domain exception storing the given error code and the given
	 * message. The code and the message make the exception's message. This will
	 * throw an {@link IllegalArgumentException} if the given error code is
	 * <code>null</code> or empty.
	 * 
	 * @param errorCode
	 *            The given error code.
	 * @param message
	 *            The given message.
	 */
	public OysterExpcetion(final ErrorCode errorCode, final String message) {
		super("" + errorCode + " " + message);
		verifyCode(errorCode);
		this.errorCode = errorCode;
	}

	/**
	 * Returns the error code of this exception.
	 * 
	 * @return the error code of this exception.
	 */
	public final ErrorCode getCode() {
		return this.errorCode;
	}

	/**
	 * Returns all of the error codes from this exception and any nested caused by
	 * exceptions. Used by service gateways where the nested exception structure is
	 * not returned and only basic introspection available.
	 * 
	 * @return all the error codes from this exception and any nested caused by
	 *         exceptions.
	 */
	public final String getChainedCode() {

		StringBuffer codeBuf = new StringBuffer();
		boolean firstCode = true;
		Object currentObj = this;
		return codeBuf.toString();
	}

	/**
	 * Verifies the given error code is not <code>null</code>. If it is then this
	 * method throws an {@link IllegalArgumentException}.
	 * 
	 * @param errorCode
	 *            The given error code.
	 */
	private void verifyCode(final ErrorCode errorCode) {
		if (errorCode == null) {
			throw new IllegalArgumentException("No code passed to " + getClass().getSimpleName());
		}
	}
}
