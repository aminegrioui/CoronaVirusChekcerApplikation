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


import com.javaApplikation.coronatraker.model.StateDeaths;


@Service
public class CoronaServiceDeath {
  

	private static String urlDeaths="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    
	private List<StateDeaths> listOfDeaths=new ArrayList<>();
	
	public List<StateDeaths> getListOfCases() {
		return listOfDeaths;
	}

	public void setListOfCases(List<StateDeaths> listOfCases) {
		this.listOfDeaths = listOfCases;
	}
    
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void fetchCoronaData() throws IOException, InterruptedException {
		List<StateDeaths> newlistOfDeaths=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
    	HttpRequest requestDeaths=HttpRequest.newBuilder().uri(URI.create(urlDeaths)).build();
    	HttpResponse<String> httpResponseDeaths=client.send(requestDeaths, HttpResponse.BodyHandlers.ofString());
		StringReader incases = new StringReader(httpResponseDeaths.body());
    	Iterable<CSVRecord> recordsCases = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(incases);
    	
    	for (CSVRecord record : recordsCases) {
    		StateDeaths stateCase=new StateDeaths();
    	    String country = record.get("Country/Region");
    	    int latestTotalCases = Integer.parseInt(record.get(record.size() - 1));
    	    int prevTotalCases = Integer.parseInt(record.get(record.size() - 2));
    	    int defFromPrevDayCases=latestTotalCases-prevTotalCases;
    	    stateCase.setCountry(country);
    	    stateCase.setLatestTotalDeaths(latestTotalCases);
    	    stateCase.setDefFromPrevDayDeaths(defFromPrevDayCases);
    	    
    	    newlistOfDeaths.add(stateCase);
    	}
    	this.listOfDeaths=newlistOfDeaths;
    }
}
