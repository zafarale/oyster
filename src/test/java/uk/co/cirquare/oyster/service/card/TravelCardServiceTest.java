/**
 * 
 */
package uk.co.cirquare.oyster.service.card;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

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

import uk.co.cirquare.oyster.core.model.Journey;
import uk.co.cirquare.oyster.core.model.TravelCard;
import uk.co.cirquare.oyster.core.service.ServiceException;
import uk.co.cirquare.oyster.core.service.card.TravelCardService;

/**
 * @author zali
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql=true)
@ComponentScan("uk.co.cirquare.oyster.core.service")
public class TravelCardServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(TravelCardServiceTest.class);

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TravelCardService travelCardService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTravelCardRecordsSucess() {
		IntStream.rangeClosed(1, 5).forEach(index -> {
			try {
				TravelCard card = travelCardService.create(new TravelCard(10.0D));
				assertNotNull(card.getUid());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void updateTravelCard() {
		try {
			
			TravelCard card = travelCardService.create(new TravelCard(0.0D));
			TravelCard card2 = travelCardService.loadCard(card, 30.0D);
			assertEquals(card, card2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
