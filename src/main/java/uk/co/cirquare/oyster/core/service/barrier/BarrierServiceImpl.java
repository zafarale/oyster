/**
 * 
 */
package uk.co.cirquare.oyster.core.service.barrier;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cirquare.oyster.core.model.Barrier;
import uk.co.cirquare.oyster.core.model.Direction;
import uk.co.cirquare.oyster.core.model.Journey;
import uk.co.cirquare.oyster.core.model.TravelCard;
import uk.co.cirquare.oyster.core.service.BaseCrudService;
import uk.co.cirquare.oyster.core.service.ServiceException;
import uk.co.cirquare.oyster.core.service.card.TravelCardService;

/**
 * @author zali
 *
 */
@Repository
@Transactional
public class BarrierServiceImpl extends BaseCrudService<Long, Barrier> implements BarrierService {

	@Autowired
	private TravelCardService travelCardService;

	@Override
	public List<Barrier> get(String name) throws ServiceException {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Barrier> criteriaQuery = builder.createQuery(Barrier.class);
		Root<Barrier> barrier = criteriaQuery.from(Barrier.class);
		criteriaQuery.select(barrier);
		criteriaQuery.where(builder.equal(barrier.get("name"), name));
		TypedQuery<Barrier> query = entityManager.createQuery(criteriaQuery);

		// Query query = this.entityManager.createQuery("SELECT e FROM " + entityClass +
		// " WHERE e.name =" + name);
		return query.getResultList();

	}

	

	//@Transactional(propagation = Propagation.REQUIRED)
	@Override
	@org.springframework.data.jpa.repository.Query("Select ")
	public void passThroughBarrier(Barrier barrier, TravelCard card, Direction direction) {// throws ServiceException {

		try {

			TravelCard tcard = travelCardService.get(card.getUid());

			if (direction.equals(Direction.INWARD)) {
				Journey journey = new Journey();
				journey.setStart(barrier);
				tcard.getJourneys().add(journey);
			} else {
				List<Journey> journeys = tcard.getJourneys();
				Journey journey = journeys.get(journeys.size() - 1);
				journey.setEnd(barrier);
			}

			tcard = travelCardService.update(tcard);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
