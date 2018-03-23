/**
 * 
 */
package uk.co.cirquare.oyster.core.service;

import uk.co.cirquare.oyster.OysterExpcetion;
import uk.co.cirquare.oyster.core.error.ErrorCode;

/**
 * @author zali
 *
 */
public class ServiceException extends OysterExpcetion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 793705060781507345L;

	public ServiceException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

}
