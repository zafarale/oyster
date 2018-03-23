/**
 * 
 */
package uk.co.cirquare.oyster.core.service;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cirquare.oyster.core.error.ErrorCode;
import uk.co.cirquare.oyster.core.model.Model;

/**
 * @author zali
 *
 */
@Transactional
public abstract class BaseCrudService<K, M extends Model<K>> extends BaseService implements CrudService<K, M> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Class<M> entityClass;
	protected Class<K> keyClass;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public BaseCrudService() {
		keyClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

	}

	@Override
	public M create(M entity) throws ServiceException {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public M update(M entity) throws ServiceException {

		return entityManager.merge(entity);
	}

	@Override
	public void delete(M entity) throws ServiceException {
		entityManager.remove(entity);
	}

	@Override
	public M get(K uid) throws ServiceException {
		M entity = entityManager.find(entityClass, uid);

		if (entity == null)
			throw new ServiceException(new ErrorCode("101"), "Record Not Found");

		return entity;
	}

	@Override
	public List<M> getAll() throws ServiceException {
		Query query = entityManager.createQuery("FROM " + entityClass.getSimpleName());
		return query.getResultList();
	}

}
