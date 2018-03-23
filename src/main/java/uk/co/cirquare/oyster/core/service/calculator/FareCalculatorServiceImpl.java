/**
 * 
 */
package uk.co.cirquare.oyster.core.service.calculator;

import java.util.Comparator;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.cirquare.oyster.core.model.Barrier;
import uk.co.cirquare.oyster.core.model.Journey;
import uk.co.cirquare.oyster.core.service.BaseService;
import uk.co.cirquare.oyster.core.service.ServiceException;
import uk.co.cirquare.oyster.core.service.barrier.BarrierService;

/**
 * @author zali
 *
 */
@Service
public class FareCalculatorServiceImpl extends BaseService implements FareCalculatorService {

	private HashMap<String, Double> fares = new HashMap<>();

	@Autowired
	private BarrierService barrierService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.co.cirquare.oyster.core.service.BaseService#init()
	 */
	@Override
	public void init() {
		super.init();
		fares.put("zone1", 2.50);
		fares.put("one_zone_outside_zone1", 2.00);
		fares.put("one_zone_including_zone1", 3.00);
		fares.put("two_zone_outside_zone1-", 2.25);
		fares.put("all", 3.20);
		fares.put("bus", 1.80);

	}

	@Override
	public Journey calculate(Journey journey) throws ServiceException {
		this.logger.debug(journey.toString());
		Barrier start = barrierService.get(journey.getStart().getName()).stream()
				.min(Comparator.comparing(Barrier::getZone)).get();
		Barrier end = barrierService.get(journey.getEnd().getName()).stream()
				.min(Comparator.comparing(Barrier::getZone)).get();
		logger.debug(start.toString());
		logger.debug(end.toString());
		// Anywhere Zone 1
		if (start.getZone().equals(1) && end.getZone().equals(1)) {
			journey.setFare(fares.get("zone1"));
		}
		// Any One Zone Outside Zone 1
		else if (!start.getZone().equals(1) || !end.getZone().equals(1)) {
			journey.setFare(fares.get("one_zone_outside_zone1"));
		}
		// Any Two Zone Including Zone 1
		else if (!start.getZone().equals(1) && !end.getZone().equals(1)) {
			journey.setFare(fares.get("one_zone_including_zone1"));
		}
		// Any Two Zone Excluding Zone 1
		else if (start.getZone().equals(1) && end.getZone().equals(1)) {
			journey.setFare(fares.get("two_zone_outside_zone1"));
		} else {
			journey.setFare(fares.get("all"));
		}
		return journey;
	}

}
