/**
 * 
 */
package uk.co.cirquare.oyster.core.service;

import java.util.List;

import uk.co.cirquare.oyster.core.model.Model;

/**
 * @author zali
 *
 */
public interface CrudService<K, M extends Model<K>> {

	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	M create(M entity) throws ServiceException;

	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	M update(M entity) throws ServiceException;

	/**
	 * @param entity
	 * @throws ServiceException
	 */
	void delete(M entity) throws ServiceException;

	/**
	 * @param uid
	 * @return
	 * @throws ServiceException
	 */
	M get(K uid) throws ServiceException;

	/**
	 * @return
	 * @throws ServiceException
	 */
	List<M> getAll() throws ServiceException;
}
