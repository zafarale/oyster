/**
 * 
 */
package uk.co.cirquare.oyster.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author zali
 *
 */
@Entity
public class Journey extends BaseModel<Long> {

	private static Double MAX_FARE = 3.20;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6688376745520789150L;

	private Double fare = MAX_FARE;

	@OneToOne
	@JoinColumn(name = "startingpoint")
	private Barrier start;

	@OneToOne
	@JoinColumn(name = "endingpoint")
	private Barrier end;

	public Journey() {

	}

	/**
	 * @return the fare
	 */
	public Double getFare() {
		return fare;
	}

	/**
	 * @param fare
	 *            the fare to set
	 */
	public void setFare(Double fare) {
		this.fare = fare;
	}

	/**
	 * @return the start
	 */
	public Barrier getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(Barrier start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Barrier getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Barrier end) {
		this.end = end;
	}

}
