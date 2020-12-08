package com.javaApplikation.coronatraker.sevices;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaApplikation.coronatraker.model.State;
import com.javaApplikation.coronatraker.model.StateCases;
import com.javaApplikation.coronatraker.model.StateDeaths;
import com.javaApplikation.coronatraker.model.StateRecovered;



public class CoronaSerice {
	
	@Autowired
	private CoronaServiceCases caseService=new CoronaServiceCases();
	
	@Autowired
	private CoronaServiceDeath caseDeaths= new CoronaServiceDeath();
	
	@Autowired
	private CoronaServiceRecoverd caseRecovered=new CoronaServiceRecoverd();
	
	private List<State> listOfDataVirus=new ArrayList<>();
	

    public List<State> getListOfDataVirus() {
		return listOfDataVirus;
	}


	public void setListOfCases(List<State> listOfCases) {
		this.listOfDataVirus = listOfCases;
	}

    @PostConstruct
	public void fetchCoronaData()  {
		
    	
    }
}
