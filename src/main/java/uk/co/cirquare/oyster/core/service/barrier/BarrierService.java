/**
 * 
 */
package uk.co.cirquare.oyster.core.service.barrier;

import java.util.List;

import uk.co.cirquare.oyster.core.error.ErrorCode;
import uk.co.cirquare.oyster.core.model.Barrier;
import uk.co.cirquare.oyster.core.model.Direction;
import uk.co.cirquare.oyster.core.model.TravelCard;
import uk.co.cirquare.oyster.core.service.CrudService;
import uk.co.cirquare.oyster.core.service.ServiceException;

/**
 * @author zali
 *
 */
public interface BarrierService extends CrudService<Long, Barrier> {

	public List<Barrier> get(String name) throws ServiceException;
	
	

	public void passThroughBarrier(Barrier barrier, TravelCard card, Direction direction) throws ServiceException;
}
