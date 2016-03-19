package com.medisys.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.medisys.entities.MedicalInsurance;
import com.medisys.entities.MedicalTestType;
import com.medisys.services.MedicalParamsService;

@Path("/medical-params")
@Controller
public class MedicalParamsController {

	@Autowired
	private MedicalParamsService medicalInsuranceService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/medical-insurances")
	public Response getMedicalInsurances() {
		Response r = null;

		try {
			List<MedicalInsurance> insurances = medicalInsuranceService.getMedicalInsurances();
			r = Response.status(Status.OK).entity(insurances).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al obtener las obras sociales").build();
		}

		return r;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/medical-test-types")
	public Response getMedicalTestTypes() {
		Response r = null;

		try {
			List<MedicalTestType> testTypes = medicalInsuranceService.getMedicalTestTypes();
			r = Response.status(Status.OK).entity(testTypes).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al obtener los tipos de analisis").build();
		}

		return r;
	}
}