/**
 * 
 */
package uk.co.cirquare.oyster.core.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author zali
 *
 */
@Entity
public class TravelCard extends BaseModel<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4111860832327476387L;

	private Date startDate;

	private Date endDate;

	private Double balance;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Journey> journeys = new ArrayList<>();

	/**
	 * 
	 * @param balance
	 */
	public TravelCard(Double balance) {
		this.balance = balance;
		this.startDate = new Date();
		LocalDateTime five_years_ahead = this.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
				.plusYears(5);
		this.setEndDate(Date.from(five_years_ahead.atZone(ZoneId.systemDefault()).toInstant()));
	}

	/**
	 * 
	 * @param balance
	 * @param startDate
	 */
	public TravelCard(Double balance, Date startDate) {
		this.balance = balance;
		this.startDate = startDate;

		LocalDateTime five_years_ahead = this.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
				.plusYears(5);

		this.setEndDate(Date.from(five_years_ahead.atZone(ZoneId.systemDefault()).toInstant()));
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @return the journeys
	 */
	public List<Journey> getJourneys() {
		return journeys;
	}

	/**
	 * @param journeys
	 *            the journeys to set
	 */
	public void setJourneys(List<Journey> journeys) {
		this.journeys = journeys;
	}

}
