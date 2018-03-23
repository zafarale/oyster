/**
 * 
 */
package uk.co.cirquare.oyster.core.service.card;

import uk.co.cirquare.oyster.core.model.TravelCard;
import uk.co.cirquare.oyster.core.service.BaseCrudService;
import uk.co.cirquare.oyster.core.service.CrudService;
import uk.co.cirquare.oyster.core.service.ServiceException;

/**
 * @author zali
 *
 */
public interface TravelCardService extends CrudService<Long, TravelCard> {
	/**
	 * Loads Money on to the Card {@link TravelCard#setBalance(Double)} 
	 * @param card
	 * @param amount
	 * @return
	 * @throws ServiceException
	 */
	public TravelCard loadCard(TravelCard card, Double amount) throws ServiceException;
}
