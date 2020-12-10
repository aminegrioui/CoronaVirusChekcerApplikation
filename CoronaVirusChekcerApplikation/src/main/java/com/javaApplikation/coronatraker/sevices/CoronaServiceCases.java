package com.javaApplikation.coronatraker.sevices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.javaApplikation.coronatraker.model.DataOfCountry;
import com.javaApplikation.coronatraker.model.StateCases;

/* 
 *    Class CoronaServiceCases to fetch data and send it 
 *    to controller mvc
 *    data will send as list of StateCases(it's class)
 */

@Service
public class CoronaServiceCases {
	// url of CoronaViruscases from CSSEGISandData
	private static String urlCases = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	// list of StateCases (Country, totalCases today, Difference between today and
	// yesterdy)
	private List<StateCases> listOfCases = new ArrayList<>();

	public List<StateCases> getListOfCases() {
		return listOfCases;
	}

	/*
	 * This method fetch data and send a list of StateCases to the controller
	 */

	@Scheduled(cron = "* * * 1 * *")
	public List<StateCases> fetchCoronaData() throws IOException, InterruptedException {

		List<StateCases> newlistOfCases = new ArrayList<>();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest requestcases = HttpRequest.newBuilder().uri(URI.create(urlCases)).build();
		HttpResponse<String> httpResponseCases = client.send(requestcases, HttpResponse.BodyHandlers.ofString());
		StringReader incases = new StringReader(httpResponseCases.body());
		Iterable<CSVRecord> recordsCases = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(incases);

		for (CSVRecord record : recordsCases) {
			StateCases stateCase = new StateCases();
			String country = record.get("Country/Region");
			int latestTotalCases = Integer.parseInt(record.get(record.size() - 1));
			int prevTotalCases = Integer.parseInt(record.get(record.size() - 2));
			int defFromPrevDayCases = latestTotalCases - prevTotalCases;

			stateCase.setCountry(country);

			stateCase.setLatestTotalCases(latestTotalCases);
			stateCase.setDefFromPrevDayCases(defFromPrevDayCases);

			newlistOfCases.add(stateCase);
		}

		this.listOfCases = newlistOfCases;

		return listOfCases;

	}
	/*
	 * fetch Data with given parameters: country or date
	 * 
	 */

	public List<StateCases> fetchCoronaDataCountry(String country, String date)
			throws IOException, InterruptedException {
		// list of data with a given country
		List<StateCases> listOfCasesCountry = new ArrayList<>();
		// list of data with a given date
		List<StateCases> listOfCasesDate = new ArrayList<>();
		// be used to request HTTP resources over the network
		HttpClient client = HttpClient.newHttpClient();
		/*
		 * Requests can be sent either synchronously or asynchronously. The synchronous
		 * API, as expected, blocks until the HttpResponse is available.
		 */
		HttpRequest requestcases = HttpRequest.newBuilder().uri(URI.create(urlCases)).build();
		HttpResponse<String> httpResponseCases = client.send(requestcases, HttpResponse.BodyHandlers.ofString());
		StringReader incases = new StringReader(httpResponseCases.body());
		/*
		 * using bufferedReader to get the first line of csv file the header of csv
		 */
		BufferedReader bufferedReader = new BufferedReader(incases);
		/*
		 * get the first line
		 */
		String line = bufferedReader.readLine();
		/*
		 * convert line to a String array using split
		 */
		String[] arrayOfHeader = line.split(",");
		/*
		 * covert the arrayOfString to a list to test if the given date is availible in
		 * the header
		 */
		List<String> list = Arrays.asList(arrayOfHeader);
		list.subList(4, list.size());
		incases = new StringReader(httpResponseCases.body());
		Iterable<CSVRecord> recordsCases = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(incases);
		String monthDate = "";
		String yearDate = "";
		String dayDate = "";
		String newDatum = "";
		

		// transform the given datum to Exp: 10.12.2020 => 12/10/2020
		if (!date.isEmpty()) {
			monthDate = date.charAt(5) == 48 ? date.substring(6, 7) + "" : date.substring(5, 7);
			yearDate = date.substring(2, 4);
			dayDate = date.charAt(8) == 48 ? date.substring(9, 10) : date.substring(8, 10);
			
			// the new form of datum
			newDatum = monthDate + "/" + dayDate + "/" + yearDate;
		
		}

		/*
		 * a list has the header of csv file test newDatum in the list loop the record
		 * of CSV file from each row get country, the total cases using the given date
		 * and calculate the deference between date and prev date,
		 */
		if (list.contains(newDatum)) {
			for (CSVRecord record : recordsCases) {
				// new object of stateCase
				StateCases stateCase = new StateCases();

				String countryFromData = record.get("Country/Region");
				int latestTotalCases = Integer.parseInt(record.get(newDatum));
                int indexOfPreviousDate=list.indexOf(newDatum);
				int prevTotalCases = Integer.parseInt(record.get(indexOfPreviousDate -1));
				int defFromPrevDayCases = latestTotalCases - prevTotalCases>0?prevTotalCases:0;

				stateCase.setCountry(countryFromData);

				stateCase.setLatestTotalCases(latestTotalCases);
				stateCase.setDefFromPrevDayCases(defFromPrevDayCases);
				/*
				 * add the object StateCases
				 */
				listOfCasesDate.add(stateCase);

			}

		}
		/*
		 * loop the providen list from the method fetchCoronaData() and put the new
		 * objekt in a list if it contain the given country
		 */
		for (StateCases state : fetchCoronaData()) {
			if (country != null && state.getCountry().equalsIgnoreCase((country))) {
				listOfCasesCountry.add(state);
				// if will found break the loop
				break;
			}

		}
		/*
		 * the data of date is first, when the user give the country and date
		 * 
		 */
		return !listOfCasesDate.isEmpty() ? listOfCasesDate : listOfCasesCountry;
	}
	
	public List<DataOfCountry> getDatumAndTotalCasesCountry(String country) throws URISyntaxException, IOException, InterruptedException{
		List<DataOfCountry> listdataOfCountry=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest requestcases = HttpRequest.newBuilder().uri(URI.create(urlCases)).build();
		
	
		HttpResponse<String> response=client.send(requestcases, HttpResponse.BodyHandlers.ofString());
		StringReader incases = new StringReader(response.body());
		Iterable<CSVRecord> recordsCases = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(incases);
		List<String> headerOfCsv=getHeaderRowOfCSVfile();
		System.out.println(headerOfCsv);
		System.out.println(country);
		for(CSVRecord record: recordsCases) {
			
			if(country.equals(record.get("Country/Region"))) {
				
				int sizeOfData=record.size()-1;
				for(int i=0;i<30;i++) {
					DataOfCountry dataCountry=new DataOfCountry();
					int latestTotalCases = Integer.parseInt(record.get(sizeOfData - i));
					dataCountry.setTotalOfCases(latestTotalCases);
					String datum=headerOfCsv.get(headerOfCsv.size()-1- i);
					dataCountry.setDatum(datum);
					System.out.println(dataCountry);
					listdataOfCountry.add(dataCountry);
					
				}
				System.out.println(listdataOfCountry);
				
				break;
				
			}
		}
		
		return listdataOfCountry;
	}
	public List<String> getHeaderRowOfCSVfile() throws IOException, InterruptedException{
		
				// be used to request HTTP resources over the network
				HttpClient client = HttpClient.newHttpClient();
				/*
				 * Requests can be sent either synchronously or asynchronously. The synchronous
				 * API, as expected, blocks until the HttpResponse is available.
				 */
				HttpRequest requestcases = HttpRequest.newBuilder().uri(URI.create(urlCases)).build();
				
				HttpResponse<String> httpResponseCases = client.send(requestcases, HttpResponse.BodyHandlers.ofString());
				StringReader incases = new StringReader(httpResponseCases.body());
				/*
				 * using bufferedReader to get the first line of csv file the header of csv
				 */
				BufferedReader bufferedReader = new BufferedReader(incases);
				/*
				 * get the first line
				 */
				String line = bufferedReader.readLine();
				/*
				 * convert line to a String array using split
				 */
				String[] arrayOfHeader = line.split(",");
				List<String> list = Arrays.asList(arrayOfHeader);
				
				return list.subList(4, list.size());
	}

}
