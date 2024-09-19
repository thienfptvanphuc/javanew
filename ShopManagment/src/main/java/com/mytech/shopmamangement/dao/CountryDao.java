package com.mytech.shopmamangement.dao;

import java.util.List;

import com.mytech.shopmamangement.db.DbConnector;
import com.mytech.shopmamangement.models.Country;
import com.mytech.shopmamangement.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CountryDao {

	public List<Country> getCountries() {
		EntityManager entityManager = DbConnector.getEntityManager();

		TypedQuery<Country> typedQuery = entityManager.createQuery("SELECT co FROM Country co", Country.class);
//		entityManager.createNativeQuery("SELECT * FROM s8_countries", Country.class);

		return typedQuery.getResultList();
	}

	public Country getCountryById(int countryId) {
		EntityManager entityManager = DbConnector.getEntityManager();

		TypedQuery<Country> typedQuery = entityManager.createNamedQuery(Country.QUERY_FIND_BY_ID, Country.class);
		typedQuery.setParameter("id", countryId);
		return typedQuery.getSingleResult();
	}
}
