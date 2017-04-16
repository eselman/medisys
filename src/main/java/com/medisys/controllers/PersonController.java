package com.medisys.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.medisys.entities.Doctor;
import com.medisys.entities.IdentificationType;
import com.medisys.entities.Patient;
import com.medisys.entities.Specialty;
import com.medisys.services.PersonService;

@Path("/person")
@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	// =============================================== DOCTOR
	// ==================================================================
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doctor/{idType}/{idNumber}")
	public Response getDoctorById(@PathParam("idType") String idType, @PathParam("idNumber") String idNumber) {
		Response r = null;

		try {
			Doctor doctor = personService.getDoctorById(idType, idNumber);

			if (doctor != null) {
				r = Response.status(Status.OK).entity(doctor).build();
			} else {
				r = Response.status(Status.NOT_FOUND)
						.entity("No se encontro el doctor con dni: " + idType + "-" + idNumber).build();
			}
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar el doctor con dni: " + idType + "-" + idNumber).build();
		}

		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doctors/{lastName}")
	public Response getDoctorsByLastName(@PathParam("lastName") String lastName) {
		Response r = null;

		try {
			List<Doctor> doctors = personService.getDoctorsByLastName(lastName);

			if (doctors == null) {
				doctors = new ArrayList<Doctor>();
			}

			r = Response.status(Status.OK).entity(doctors).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar los doctores con apellido: " + lastName).build();
		}

		return r;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doctor")
	public Response saveDoctor(Doctor doctor) {
		Response r = null;

		try {
			doctor = personService.saveDoctor(doctor);
			r = Response.status(Status.OK).entity(doctor).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al guardar el nuevo doctor")
					.build();
		}

		return r;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doctor")
	public Response updateDoctor(Doctor doctor) {
		Response r = null;

		try {
			doctor = personService.saveDoctor(doctor);
			r = Response.status(Status.OK).entity(doctor).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al actualizar los datos del doctor")
					.build();
		}

		return r;
	}

	// =============================================== PATIENT
	// ==================================================================
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patients")
	public Response getPatients() {
		Response r = null;

		try {
			List<Patient> patients = personService.getPatients();

			if (patients == null) {
				patients = new ArrayList<Patient>();
			}

			r = Response.status(Status.OK).entity(patients).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar el listado de pacientes").build();
		}

		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patient/{idType}/{idNumber}")
	public Response getPatientById(@PathParam("idType") String idType, @PathParam("idNumber") String idNumber) {
		Response r = null;

		try {
			Patient patient = personService.getPatientById(idType, idNumber);

			if (patient != null) {
				r = Response.status(Status.OK).entity(patient).build();
			} else {
				r = Response.status(Status.NOT_FOUND)
						.entity("No se encontro el paciente con dni: " + idType + "-" + idNumber).build();
			}
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar el paciente con dni: " + idType + "-" + idNumber).build();
		}

		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patients/{lastName}")
	public Response getPatientsByLastName(@PathParam("lastName") String lastName) {
		Response r = null;

		try {
			List<Patient> patients = personService.getPatientsByLastName(lastName);

			if (patients == null) {
				patients = new ArrayList<Patient>();
			}

			r = Response.status(Status.OK).entity(patients).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar los pacientes con apellido: " + lastName).build();
		}

		return r;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patient")
	public Response savePatient(Patient patient) {
		Response r = null;

		try {
			patient = personService.savePatient(patient);
			r = Response.status(Status.OK).entity(patient).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al guardar el nuevo paciente")
					.build();
		}

		return r;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/patient")
	public Response updatePatient(Patient patient) {
		Response r = null;

		try {
			patient = personService.savePatient(patient);
			r = Response.status(Status.OK).entity(patient).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al actualizar los datos del paciente")
					.build();
		}

		return r;
	}

	//========================== GENERAL PARAMS ==========================================================
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/identification-types")
	public Response getIdentificationTypes() {
		Response r = null;

		try {
			List<IdentificationType> identificationTypes = personService.getIdentificationTypes();

			if (identificationTypes == null) {
				identificationTypes= new ArrayList<IdentificationType>();
			}

			r = Response.status(Status.OK).entity(identificationTypes).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar los tipos de documentos").build();
		}

		return r;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/specialties")
	public Response getSpecialties() {
		Response r = null;

		try {
			List<Specialty> specialties = personService.getSpecialties();

			if (specialties == null) {
				specialties = new ArrayList<Specialty>();
			}

			r = Response.status(Status.OK).entity(specialties).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al buscar las especialidades").build();
		}

		return r;
	}
}