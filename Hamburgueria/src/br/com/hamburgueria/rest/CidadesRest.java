package br.com.hamburgueria.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.hamburgueria.service.CidadeService;

@Path("/CidadesRest")
public class CidadesRest extends UtilRest {
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listarTodas() {
		try {
			CidadeService cidade = new CidadeService();
			return this.buildResponse(cidade.listarTodas());

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	

}
