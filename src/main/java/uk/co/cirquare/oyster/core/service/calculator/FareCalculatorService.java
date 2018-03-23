/**
 * 
 */
package uk.co.cirquare.oyster.core.service.calculator;

import uk.co.cirquare.oyster.core.model.Journey;
import uk.co.cirquare.oyster.core.service.Service;
import uk.co.cirquare.oyster.core.service.ServiceException;

/**
 * @author zali
 *
 */
public interface FareCalculatorService extends Service{
	/**
	 * 
	 * @param journey
	 * @return
	 * @throws ServiceException
	 */
	public Journey calculate(Journey journey) throws ServiceException;
}
