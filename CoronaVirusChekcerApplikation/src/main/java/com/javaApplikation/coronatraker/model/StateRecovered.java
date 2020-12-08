package com.javaApplikation.coronatraker.model;

public class StateRecovered {
	private String country;
	 private int latestTotalRecovered;

	public int getLatestTotalRecovered() {
		return latestTotalRecovered;
	}

	public void setLatestTotalRecovered(int latestTotalRecovered) {
		this.latestTotalRecovered = latestTotalRecovered;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "StateRecovered [country=" + country + ", latestTotalRecovered=" + latestTotalRecovered + "]";
	}
	
	 

}
