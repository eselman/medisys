package com.medisys.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.medisys.entities.Laboratory;
import com.medisys.entities.Medicine;
import com.medisys.services.MedicineService;

@Path("/medicines")
@Controller
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/laboratories")
	public Response getLaboratories() {
		Response r = null;

		try {
			List<Laboratory> laboratories = medicineService.getLaboratories();
			r = Response.status(Status.OK).entity(laboratories).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al obtener los laboratorios")
					.build();
		}

		return r;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{laboratoryId}")
	public Response getMedicinesByLaboratory(@PathParam("laboratoryId") Long laboratoryId) {
		Response r = null;

		try {
			List<Medicine> medicines = medicineService.getMedicinesByLaboratory(laboratoryId);
			r = Response.status(Status.OK).entity(medicines).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al obtener los medicamentos para el laboratorio: " + laboratoryId)
					.build();
		}

		return r;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchMedicines(@QueryParam("query") String query) {
		Response r = null;

		try {
			List<Medicine> medicines = medicineService.searchMedicines(query);
			r = Response.status(Status.OK).entity(medicines).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al buscar medicamentos")
					.build();
		}

		return r;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/medicine")
	public Response saveMedicine (Medicine medicine) {
		Response r = null;
		
		try {
			medicine = medicineService.saveMedicine(medicine);
			r = Response.status(Status.OK).entity(medicine).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al guardar el nuevo medicamento")
					.build();
		}
		
		return r;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/medicine")
	public Response updateMedicine (Medicine medicine) {
		Response r = null;
		
		try {
			medicine = medicineService.saveMedicine(medicine);
			r = Response.status(Status.OK).entity(medicine).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al actualizar el medicamento: " + medicine.getId())
					.build();
		}
		
		return r;
	}
}