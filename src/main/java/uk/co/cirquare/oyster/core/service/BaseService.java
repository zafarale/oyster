/**
 * 
 */
package uk.co.cirquare.oyster.core.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zali
 *
 */
public abstract class BaseService implements Service {

	public final Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Service#init
	 */
	@Override
	public void init() {
		logger.info("Starting Service " + getClass().getSimpleName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Service#destroy()
	 */
	@Override
	public void destroy() {
		logger.info("Stopping Service " + getClass().getSimpleName());
	}
}
