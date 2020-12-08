package com.javaApplikation.coronatraker.model;

public class StateDeaths {
	private String country;
	private int defFromPrevDayDeaths;
	 private int latestTotalDeaths;
	 
	

	public int getDefFromPrevDayDeaths() {
		return defFromPrevDayDeaths;
	}
	
	public void setDefFromPrevDayDeaths(int defFromPrevDayDeaths) {
		this.defFromPrevDayDeaths = defFromPrevDayDeaths;
	}
	public int getLatestTotalDeaths() {
		return latestTotalDeaths;
	}
	public void setLatestTotalDeaths(int latestTotalDeaths) {
		this.latestTotalDeaths = latestTotalDeaths;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	 
	@Override
	public String toString() {
		return "StateDeaths [country=" + country + ", defFromPrevDayDeaths=" + defFromPrevDayDeaths
				+ ", latestTotalDeaths=" + latestTotalDeaths + "]";
	}
}
