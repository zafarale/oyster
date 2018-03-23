/**
 * 
 */
package uk.co.cirquare.oyster.core.model;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author zali
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseModel<K> implements Model<K> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -578968992758235324L;

	/**
	 * K id.
	 */
	@Id
	@GeneratedValue
	private K uid;

	/**
	 *
	 * @return <K> unique identifier for the entity.
	 *
	 */
	public K getUid() {
		return this.uid;
	}

	/**
	 *
	 * @param id
	 *            unique identifier for the entity.
	 */
	public void setUid(final K id) {
		this.uid = id;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
