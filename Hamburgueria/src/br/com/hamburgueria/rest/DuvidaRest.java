package br.com.hamburgueria.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.objs.Duvida;
import br.com.hamburgueria.service.DuvidaService;

@Path("DuvidaRest")
public class DuvidaRest extends UtilRest{
	
	public DuvidaRest() {
	}

	@POST
	@Path("/adicionar")
	@Consumes("application/*")
	public Response adicionar(String duvida) {
		try {
			Duvida duv = new ObjectMapper().readValue(duvida, Duvida.class);
			DuvidaService service = new DuvidaService();
			service.adicionar(duv);
			return buildResponse("Obrigado pela sua opinão, precisamos de mais pessoas como você!");
		} catch (HamburgueriaException | IOException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("buscarNome/{nome}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response buscarNome(@PathParam("nome") String nome) {
		try {
			DuvidaService service = new DuvidaService();
			if (nome.equals("null")) {
				nome = "";
			}
			return this.buildResponse(service.buscarNome(nome));
		} catch (HamburgueriaException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

}
