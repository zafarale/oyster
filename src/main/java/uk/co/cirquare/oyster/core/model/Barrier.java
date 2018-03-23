/**
 * 
 */
package uk.co.cirquare.oyster.core.model;

import javax.persistence.Entity;

/**
 * @author zali
 *
 */
@Entity
public class Barrier extends BaseModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5447428777426810591L;

	private String name;

	private Integer zone;

	public Barrier(String name, Integer zone) {
		this.name = name;
		this.zone = zone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the zone
	 */
	public Integer getZone() {
		return zone;
	}

	/**
	 * @param zone
	 *            the zone to set
	 */
	public void setZone(Integer zone) {
		this.zone = zone;
	}
}
