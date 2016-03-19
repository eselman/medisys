package com.medisys.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.medisys.entities.LoginCredentials;
import com.medisys.entities.User;
import com.medisys.services.AuthenticationService;

@Path("/auth")
@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(User user) {
		Response r = null;

		try {
			LoginCredentials loginCredentials = authenticationService
					.getLoginCredentials(user);

//			authenticationService.registerRestLoginAuditing(user.getUsername(),
//					loginCredentials != null, httpRequest.getRemoteAddr(),
//					httpRequest.getHeader("user-agent"));

			if (loginCredentials == null) {
				r = Response.status(Status.UNAUTHORIZED).build();
			} else {
				r = Response.status(Status.OK).entity(loginCredentials).build();
			}
		} catch (Exception e) {
			r = Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al al loguear el usuario: "
							+ user.getUsername()).build();
		}

		return r;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response logout(HttpServletRequest httpRequest) {
		Response r = null;
		
		try {
			LoginCredentials loginCredentials = authenticationService.logout(httpRequest.getHeader("Authorization"));
			
			if (loginCredentials == null) {
				r = Response.status(Status.UNAUTHORIZED).build();
	        } else {
	        	r = Response.status(Status.OK).entity("Logout exitoso").build();
	        }
		} catch (Exception e) {
			r = Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Se produjo un error al al cerrar la sesion del usuario").build();
		}

		return r;
	}
}