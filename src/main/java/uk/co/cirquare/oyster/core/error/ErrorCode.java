package uk.co.cirquare.oyster.core.error;

import java.io.Serializable;
import javax.print.attribute.standard.Severity;

public class ErrorCode implements Serializable {

	protected static final String NO_CODE = "-1";
	protected static final ErrorCode ERROR_CODE_OK = new ErrorCode("0");
	protected static final ErrorCode ERROR_CODE_NO_CODE = new ErrorCode(NO_CODE);
	protected static final ErrorCode ERROR_CODE_NOT_HANDLED = new ErrorCode("1");

	private String code = null;
	private Severity severity;

	/**
	 * 
	 */
	public ErrorCode() {
		this(NO_CODE);
	}

	/**
	 * 
	 */
	public ErrorCode(final String code) {
		this(code, Severity.ERROR);
	}

	/**
	 * 
	 */
	public ErrorCode(final String code, final Severity severity) {
		if (code == null || code.length() == 0) {
			throw new IllegalArgumentException("No code passed to ErrorCode constructor.");
		}
		this.code = code;
		this.severity = severity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof ErrorCode)) {
			return false;
		} else {
			ErrorCode compErrCode = (ErrorCode) o;
			return getCode().equals(compErrCode.getCode());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getCode().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getSimpleName() + ": [code=" + getCode() + ", severity=" + "]";
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
