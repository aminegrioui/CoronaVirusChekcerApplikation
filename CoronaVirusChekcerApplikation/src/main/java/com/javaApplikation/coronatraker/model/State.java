package com.javaApplikation.coronatraker.model;

public class State {

	 private String country;
	 private int latestTotalCases;
	 private int latestTotalDeaths;
	 private int defFromPrevDayCases;
	 private int defFromPrevDayDeaths;
	 private int latestTotalRecovered;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public int getLatestTotalDeaths() {
		return latestTotalDeaths;
	}
	public void setLatestTotalDeaths(int latestTotalDeaths) {
		this.latestTotalDeaths = latestTotalDeaths;
	}
	public int getDefFromPrevDayCases() {
		return defFromPrevDayCases;
	}
	public void setDefFromPrevDayCases(int defFromPrevDayCases) {
		this.defFromPrevDayCases = defFromPrevDayCases;
	}
	public int getDefFromPrevDayDeaths() {
		return defFromPrevDayDeaths;
	}
	public void setDefFromPrevDayDeaths(int defFromPrevDayDeaths) {
		this.defFromPrevDayDeaths = defFromPrevDayDeaths;
	}
	public int getLatestTotalRecovered() {
		return latestTotalRecovered;
	}
	public void setLatestTotalRecovered(int latestTotalRecovered) {
		this.latestTotalRecovered = latestTotalRecovered;
	}
	@Override
	public String toString() {
		return "State [country=" + country + ", latestTotalCases=" + latestTotalCases + ", latestTotalDeaths="
				+ latestTotalDeaths + ", defFromPrevDayCases=" + defFromPrevDayCases + ", defFromPrevDayDeaths="
				+ defFromPrevDayDeaths + ", latestTotalRecovered=" + latestTotalRecovered + "]";
	}
	
	 
}
