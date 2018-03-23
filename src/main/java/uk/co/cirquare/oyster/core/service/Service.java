/**
 * 
 */
package uk.co.cirquare.oyster.core.service;

import uk.co.cirquare.oyster.OysterExpcetion;

/**
 * @author zali
 *
 */
public interface Service {
	/**
	 * Service Initialised
	 */
	void init() throws OysterExpcetion;

	/**
	 * Service destroying
	 */
	void destroy() throws OysterExpcetion;
}
