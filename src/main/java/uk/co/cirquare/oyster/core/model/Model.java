/**
 * 
 */
package uk.co.cirquare.oyster.core.model;

import java.io.Serializable;

/**
 * @author zali
 *
 */
public interface Model<K> extends Serializable {
	/**
	 *
	 * @return <K> unique identifier for the entity.
	 *
	 */
	K getUid();

	/**
	 *
	 * @param uid
	 *            unique identifier for the entity.
	 */
	void setUid(K uid);
}
