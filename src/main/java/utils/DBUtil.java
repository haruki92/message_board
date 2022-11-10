package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	private static final String PERSISTENS_UNIT_NAME = "message_board";
	private static EntityManagerFactory emf;
	
	public static EntityManager creatEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
	
	private static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(PERSISTENS_UNIT_NAME);
		}
		
		return emf;
	}
}
