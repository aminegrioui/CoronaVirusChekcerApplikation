package com.javaApplikation.coronatraker.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaApplikation.coronatraker.model.State;
import com.javaApplikation.coronatraker.model.StateCases;
import com.javaApplikation.coronatraker.model.StateDeaths;
import com.javaApplikation.coronatraker.model.StateRecovered;
import com.javaApplikation.coronatraker.sevices.CoronaSerice;
import com.javaApplikation.coronatraker.sevices.CoronaServiceCases;
import com.javaApplikation.coronatraker.sevices.CoronaServiceDeath;
import com.javaApplikation.coronatraker.sevices.CoronaServiceRecoverd;

@Controller
public class CoronAppController {

	@Autowired
	private CoronaServiceCases service;
	@Autowired
	private CoronaServiceDeath serviceDeath;
	@Autowired
	private CoronaServiceRecoverd serviceRcovered;

	@GetMapping("/")
	public String getViewHomeCoronaData(Model model, @PathParam("country") String country, @PathParam("date") String date) throws IOException, InterruptedException {

		List<StateCases> listOfCoronaData = service.fetchCoronaData();
		List<StateDeaths> listOfCoronaDataDeath = serviceDeath.getListOfCases();
		int totalGlobalCases = 0;
		int totalNewCases = 0;
		int totalGlobalDeaths = 0;

		for (StateCases data : listOfCoronaData) {
			totalGlobalCases += data.getLatestTotalCases();
		}
		for (StateCases data : listOfCoronaData) {
			totalNewCases += data.getDefFromPrevDayCases();
		}
		for (StateDeaths data : listOfCoronaDataDeath) {
			totalGlobalDeaths += data.getLatestTotalDeaths();
		}
       
		model.addAttribute("listOfCoronaData", (country!=null || date!=null)? service.fetchCoronaDataCountry(country,date) :listOfCoronaData);

		model.addAttribute("totalGlobalCases", totalGlobalCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("totalGlobalDeaths", totalGlobalDeaths);
		return "home";

	}

	@GetMapping("/dataAboutDeaths")
	public String getViewOfDataDeaths(Model model) {
		List<StateDeaths> listOfDataDeaths = serviceDeath.getListOfCases();

		int totalGlobalDeaths = 0;
		for (StateDeaths data : listOfDataDeaths) {
			totalGlobalDeaths += data.getLatestTotalDeaths();
		}
		model.addAttribute("listOfDataDeaths", listOfDataDeaths);
		model.addAttribute("totalGlobalDeaths", totalGlobalDeaths);
		return "deathsHome";
	}

	@GetMapping("/dataAboutRecoverd")
	public String getViewOfRcovered(Model model) {
		List<StateRecovered> listOfRecovered = serviceRcovered.getListOfCases();

		int totalGlobalRecoverd = 0;
		for (StateRecovered data : listOfRecovered) {
			totalGlobalRecoverd += data.getLatestTotalRecovered();
		}
		model.addAttribute("listOfRecovered", listOfRecovered);
		model.addAttribute("totalGlobalRecoverd", totalGlobalRecoverd);
		return "recoveredsHome";
	}

}
