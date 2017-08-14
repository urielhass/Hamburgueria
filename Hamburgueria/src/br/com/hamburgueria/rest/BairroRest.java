package br.com.hamburgueria.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.hamburgueria.service.BairroService;

@Path("BairroRest")
public class BairroRest extends UtilRest{
	@GET
	@Path("/buscarPorId/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarPorId(@PathParam("id")int id) {
		try {
			BairroService bairro = new BairroService();
			return this.buildResponse(bairro.buscarPorId(id));

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
