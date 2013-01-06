package database.dao.country;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import play.db.DB;

import models.user.Country;

public class CountryDAO {

	public static final String QUERY_GET_ALL_COUNTRIES_ORDERED_BY_NAME = "SELECT country_id,country_name FROM country ORDER BY country_name";
	public static final String QUERY_GET_COUNTRY = "SELECT country_id,country_name FROM country WHERE country_id = ?";
	
	public static List<Country> getAllCountriesOrderedByName() {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ALL_COUNTRIES_ORDERED_BY_NAME);
			return CountryDAORowMapper.mapCountryList(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Country getCountry(Integer countryId) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_COUNTRY);
			ps.setInt(1, countryId);
			return CountryDAORowMapper.mapCountry(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
