/**
 * 
 */
package uk.co.cirquare.oyster.service.barrier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
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
import uk.co.cirquare.oyster.core.service.ServiceException;
import uk.co.cirquare.oyster.core.service.barrier.BarrierService;

/**
 * @author zali
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ComponentScan("uk.co.cirquare.oyster.core.service")
public class BarrierServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(BarrierServiceTest.class);

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BarrierService barrierService;

	private List<Barrier> barriers;

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
					// barriers.add(barrier);
					// logger.debug(barrier.toString());
				} catch (ServiceException e) {
					e.printStackTrace();
				}

			});
		});
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	// @Test
	public void createBarriersSucess() throws IOException {

	}

	//@Test
	public void getAllBarriers() {
		try {
			List<Barrier> barriers = barrierService.getAll();
			//barriers.forEach(System.out::println);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void getBarrierByName() {
		try {
			List<Barrier> barriers = barrierService.get("Archway");
			assertEquals(barriers.size(), 2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getBarrierByNameWithMinimumZone() {
		try {
			List<Barrier> barriers = barrierService.get("Manor House");
			Barrier barrier = barriers.stream().min(Comparator.comparing(Barrier::getZone)).get();
			logger.debug(barrier.toString());
			assertEquals(1, barriers.size());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
