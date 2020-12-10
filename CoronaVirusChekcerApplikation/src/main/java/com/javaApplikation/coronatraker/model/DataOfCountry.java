package com.javaApplikation.coronatraker.model;

import java.util.List;

public class DataOfCountry {
	
	private String datum;
	private int totalOfCases;
	private int totalOfDeath;
	private int totalOfRecouvry;
	public int getTotalOfCases() {
		return totalOfCases;
	}
	public void setTotalOfCases(int totalOfCases) {
		this.totalOfCases = totalOfCases;
	}
	public int getTotalOfDeath() {
		return totalOfDeath;
	}
	public void setTotalOfDeath(int totalOfDeath) {
		this.totalOfDeath = totalOfDeath;
	}
	public int getTotalOfRecouvry() {
		return totalOfRecouvry;
	}
	public void setTotalOfRecouvry(int totalOfRecouvry) {
		this.totalOfRecouvry = totalOfRecouvry;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	@Override
	public String toString() {
		return "DataOfCountry [datum=" + datum + ", totalOfCases=" + totalOfCases + ", totalOfDeath=" + totalOfDeath
				+ ", totalOfRecouvry=" + totalOfRecouvry + "]";
	}
	

}
