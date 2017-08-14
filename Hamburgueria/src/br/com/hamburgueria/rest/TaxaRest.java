package br.com.hamburgueria.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.objs.Taxa;
import br.com.hamburgueria.service.TaxaService;

@Path("TaxaRest")
public class TaxaRest extends UtilRest{
	
	public TaxaRest() {
	}
	
	@GET
	@Path("buscar/{nome}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscar(@PathParam("nome") String nome) {
		try {
			TaxaService service = new TaxaService();
			if(nome.equals("null")){
				nome = "";
			}
			return this.buildResponse(service.buscar(nome));

		} catch (HamburgueriaException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}		
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes("application/*")
	public Response atualizar(String taxa){
		try{
			Taxa taxas = new ObjectMapper().readValue(taxa, Taxa.class);
			TaxaService service = new TaxaService();
			service.editar(taxas);			
			return this.buildResponse("Valores atualizados.");
		}catch(HamburgueriaException | IOException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
