package com.medisys.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.medisys.entities.PatientHistory;
import com.medisys.services.PatientHistoryService;

@Path("/ph")
@Controller
public class PatientHistoryController {

	@Autowired
	private PatientHistoryService patientHistoryService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patientHistory/{idType}/{idNumber}")
	public Response getPatientHistoryByPatientId(@PathParam("idType") String idType,
			@PathParam("idNumber") String idNumber) {
		Response r = null;

		try {
			PatientHistory patientHistory = patientHistoryService.getPatientHistoryByPatientId(idType, idNumber);

			if (patientHistory != null) {
				r = Response.status(Status.OK).entity(patientHistory).build();
			} else {
				r = Response
						.status(Status.NOT_FOUND)
						.entity("No se encontro la historia clinica para el paciente con dni: " + idType + "-"
								+ idNumber).build();
			}
		} catch (Exception e) {
			r = Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar la historia clinica del paciente con dni: " + idType + "-"
							+ idNumber).build();
		}

		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patientHistory/{lastName}")
	public Response getPatientHistoryByPatientLastName(@PathParam("lastName") String lastName) {
		Response r = null;

		try {
			PatientHistory patientHistory = patientHistoryService.getPatientHistoryByLastName(lastName);

			if (patientHistory != null) {
				r = Response.status(Status.OK).entity(patientHistory).build();
			} else {
				r = Response.status(Status.NOT_FOUND)
						.entity("No se encontro la historia clinica para el paciente con apellido: " + lastName)
						.build();
			}
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar la historia clinica del paciente con apellido: " + lastName)
					.build();
		}

		return r;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patientHistory")
	public Response savePatientHistory(PatientHistory patientHistory) {
		Response r = null;

		try {
			patientHistory = patientHistoryService.savePatientHistory(patientHistory);
			r = Response.status(Status.OK).entity(patientHistory).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al guardar la historia clinica").build();
		}

		return r;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patientHistory")
	public Response updatePatientHistory(PatientHistory patientHistory) {
		Response r = null;

		try {
			patientHistory = patientHistoryService.savePatientHistory(patientHistory);
			r = Response.status(Status.OK).entity(patientHistory).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al actualizar la historia clinica").build();
		}

		return r;
	}
}