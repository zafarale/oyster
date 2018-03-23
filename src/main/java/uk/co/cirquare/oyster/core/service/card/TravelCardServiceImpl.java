/**
 * 
 */
package uk.co.cirquare.oyster.core.service.card;

import java.util.List;

import org.springframework.stereotype.Repository;

import uk.co.cirquare.oyster.core.model.Barrier;
import uk.co.cirquare.oyster.core.model.TravelCard;
import uk.co.cirquare.oyster.core.service.BaseCrudService;
import uk.co.cirquare.oyster.core.service.ServiceException;

/**
 * @author zali
 *
 */
@Repository
public class TravelCardServiceImpl extends BaseCrudService<Long, TravelCard> implements TravelCardService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.co.cirquare.oyster.core.service.card.TravelCardService#loadCard(uk.co.
	 * cirquare.oyster.core.model.TravelCard, java.lang.Double)
	 * Guarded by {@link BaseCrudService#get()} for invalid card ids.
	 */
	@Override
	public TravelCard loadCard(TravelCard card, Double amount) throws ServiceException {
		//Validate Card
		TravelCard tcard = this.get(card.getUid());
		//Load balance
		tcard.setBalance(amount);
		return this.update(tcard);
	}

}
