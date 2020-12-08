package com.javaApplikation.coronatraker.sevices;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.javaApplikation.coronatraker.model.StateRecovered;


@Service
public class CoronaServiceRecoverd {


	private static String urlRecoverd="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
    
	private List<StateRecovered> listOfRecovered=new ArrayList<>();
	
	public List<StateRecovered> getListOfCases() {
		return listOfRecovered;
	}

	public void setListOfCases(List<StateRecovered> listOfCases) {
		this.listOfRecovered = listOfCases;
	}
    
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void fetchCoronaData() throws IOException, InterruptedException {
		 List<StateRecovered> newlistOfRcovered=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
    	HttpRequest requestRecovered=HttpRequest.newBuilder().uri(URI.create(urlRecoverd)).build();
    	HttpResponse<String> httpResponseDeaths=client.send(requestRecovered, HttpResponse.BodyHandlers.ofString());
		StringReader incases = new StringReader(httpResponseDeaths.body());
    	Iterable<CSVRecord> recordsCases = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(incases);
    
    	for (CSVRecord record : recordsCases) {
    		StateRecovered stateCase=new StateRecovered();
    	    String country = record.get("Country/Region");
    	    int latestTotalCases = Integer.parseInt(record.get(record.size() - 1));
    	  
    	    stateCase.setCountry(country);
    	    stateCase.setLatestTotalRecovered(latestTotalCases);
    	    
    	    newlistOfRcovered.add(stateCase);
    	}
    	System.out.println(newlistOfRcovered);
    	this.listOfRecovered=newlistOfRcovered;
    }
}
