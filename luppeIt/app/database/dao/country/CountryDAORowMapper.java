package database.dao.country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.user.Country;

public class CountryDAORowMapper {
	
	public static final String COUNTRY_ID_COLUMN = "country_id";
	public static final String COUNTRY_NAME_COLUMN = "country_name";

	public static List<Country> mapCountryList(ResultSet rs) {
		try {
			List<Country> countries = new ArrayList<Country>();
			while (rs.next()) {
				Country country = new Country();
				country.setCountryId(rs.getInt(COUNTRY_ID_COLUMN));
				country.setCountryName(rs.getString(COUNTRY_NAME_COLUMN));
				countries.add(country);
			}
			return countries;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Country mapCountry(ResultSet rs) {
		try {
			rs.next();
			
			Country country = new Country();
			country.setCountryId(rs.getInt(COUNTRY_ID_COLUMN));
			country.setCountryName(rs.getString(COUNTRY_NAME_COLUMN));
			
			return country;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
