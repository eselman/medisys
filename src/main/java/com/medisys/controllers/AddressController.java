package com.medisys.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.medisys.entities.Country;
import com.medisys.entities.County;
import com.medisys.entities.Department;
import com.medisys.entities.Town;
import com.medisys.services.AddressService;

@Path("/address")
@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/countries")
	public Response getCountries() {
		Response r = null;

		try {
			List<Country> countries = addressService.getCountries();
			r = Response.status(Status.OK).entity(countries).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Se produjo un error al obtener los paises")
					.build();
		}

		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/counties/{countryCode}")
	public Response getCountiesByCountryCode(@PathParam("countryCode") String countryCode) {
		Response r = null;

		try {
			List<County> counties = addressService.getCountiesByCountryCode(countryCode);
			r = Response.status(Status.OK).entity(counties).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al obtener las provincias para el pais: " + countryCode).build();
		}
		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/departments/{countyCode}")
	public Response getDepartmentsByCountyCode(@PathParam("countyCode") String countyCode) {
		Response r = null;

		try {
			List<Department> departments = addressService.getDepartmentsByCountyCode(countyCode);
			r = Response.status(Status.OK).entity(departments).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al obtener los departamentos para la provincia: " + countyCode)
					.build();
		}
		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/towns/{departmentCode}")
	public Response getTownsByDepartmentCode(@PathParam("departmentCode") String departmentCode) {
		Response r = null;

		try {
			List<Town> towns = addressService.getTownsByDepartmentCode(departmentCode);
			r = Response.status(Status.OK).entity(towns).build();
		} catch (Exception e) {
			r = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al obtener las localidades para el departamento: " + departmentCode)
					.build();
		}
		return r;
	}
}