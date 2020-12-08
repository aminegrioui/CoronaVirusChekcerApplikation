package com.javaApplikation.coronatraker.model;

public class StateCases {
  
	
	private String country;
	private int latestTotalCases;
	private int defFromPrevDayCases;
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
	public int getDefFromPrevDayCases() {
		return defFromPrevDayCases;
	}
	public void setDefFromPrevDayCases(int defFromPrevDayCases) {
		this.defFromPrevDayCases = defFromPrevDayCases;
	}
	@Override
	public String toString() {
		return "StateCases [country=" + country + ", latestTotalCases=" + latestTotalCases + ", defFromPrevDayCases="
				+ defFromPrevDayCases + "]";
	}
	
}
