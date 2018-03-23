/**
 * 
 */
package uk.co.cirquare.oyster.service.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.opencsv.CSVReader;

import uk.co.cirquare.oyster.core.model.Barrier;
import uk.co.cirquare.oyster.core.model.Direction;
import uk.co.cirquare.oyster.core.model.Journey;
import uk.co.cirquare.oyster.core.model.TravelCard;
import uk.co.cirquare.oyster.core.service.ServiceException;
import uk.co.cirquare.oyster.core.service.barrier.BarrierService;
import uk.co.cirquare.oyster.core.service.calculator.FareCalculatorService;
import uk.co.cirquare.oyster.core.service.card.TravelCardService;

/**
 * @author zali
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("uk.co.cirquare.oyster.core.service")
public class FareCalculatorServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(FareCalculatorServiceTest.class);

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BarrierService barrierService;

	@Autowired
	private TravelCardService travelCardService;

	@Autowired
	private FareCalculatorService fareCalculator;

	private TravelCard card;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = this.getClass().getClassLoader();
		File csvFile = new File(classLoader.getResource("stations.csv").getFile());
		assertTrue(csvFile.exists());
		CSVReader reader = new CSVReader(new FileReader(csvFile));

		List<String[]> csvEntries = reader.readAll();
		csvEntries.stream().forEach(csvEntry -> {
			Integer zones[] = Arrays.stream(csvEntry[1].split("&")).map(String::trim).map(Integer::parseInt)
					.toArray(Integer[]::new);
			// If Barrier/Station lies in two zones create two entries
			Arrays.stream(zones).forEach(zone -> {
				try {
					Barrier barrier = barrierService.create(new Barrier(csvEntry[0], zone));

					// logger.debug(barrier.toString());
				} catch (ServiceException e) {
					e.printStackTrace();
				}

			});
		});
		fareCalculator.init();
		card = travelCardService.create(new TravelCard(0.0D));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	//@Test
	public void testFareCalcuationJourneyOne() {
		try {

			card = travelCardService.loadCard(card, 30.0D);

			List<Barrier> hbBarriers = barrierService.get("Holborn");
			Barrier holborn = hbBarriers.get(hbBarriers.size() - 1);
			// Barrier earlsCourt = barrierService.get("Earl's Court").get(0);

			List<Barrier> ecBarriers = barrierService.get("Earl's Court");
			Barrier earlsCourt = ecBarriers.get(ecBarriers.size() - 1);

			// entityManager.getEntityManager().getTransaction().begin();
			barrierService.passThroughBarrier(holborn, card, Direction.INWARD);
			// entityManager.getEntityManager().getTransaction().commit();

			barrierService.passThroughBarrier(earlsCourt, card, Direction.OUTWARD);

			assertEquals(holborn, card.getJourneys().get(0).getStart());
			assertEquals(earlsCourt, card.getJourneys().get(0).getEnd());

			List<Journey> journeys = card.getJourneys();
			Journey journey = journeys.get(journeys.size() - 1);

			journey = fareCalculator.calculate(journey);

			logger.debug(journey.toString());

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFareCalcuationJourneyTwo() {
		try {

			card = travelCardService.loadCard(card, 30.0D);

			List<Barrier> hbBarriers = barrierService.get("Hammersmith");
			Barrier hammersmith = hbBarriers.get(hbBarriers.size() - 1);
			// Barrier earlsCourt = barrierService.get("Earl's Court").get(0);

			List<Barrier> ecBarriers = barrierService.get("Earl's Court");
			Barrier earlsCourt = ecBarriers.get(ecBarriers.size() - 1);

			barrierService.passThroughBarrier(earlsCourt, card, Direction.INWARD);
			barrierService.passThroughBarrier(hammersmith, card, Direction.OUTWARD);

			assertEquals(earlsCourt, card.getJourneys().get(0).getStart());
			assertEquals(hammersmith, card.getJourneys().get(0).getEnd());

			List<Journey> journeys = card.getJourneys();
			Journey journey = journeys.get(journeys.size() - 1);

			journey = fareCalculator.calculate(journey);

			logger.debug(journey.toString());

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
