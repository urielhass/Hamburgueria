package br.com.hamburgueria.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.objs.Produto;
import br.com.hamburgueria.service.ProdutoService;

@Path("ProdutoRest")
public class ProdutoRest extends UtilRest {
	
	public ProdutoRest() {
	}
	@POST
	@Path("/adicionar")
	@Consumes("application/*")
	public Response adicionar(String produto) {
		try {
			Produto prod = new ObjectMapper().readValue(produto,
					Produto.class);
			ProdutoService service = new ProdutoService();
			service.adicionar(prod);
			return this.buildResponse("Produto cadastrado com sucesso.");
		} catch (HamburgueriaException | IOException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("buscarNome/{nome}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarNome(@PathParam("nome") String nome) {
		try {
			ProdutoService service = new ProdutoService();
			if(nome.equals("null")){
				nome = "";
			}
			return this.buildResponse(service.buscarNome(nome));

		} catch (HamburgueriaException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}		
	}
	
	
	@DELETE
	@Path("/deletar/{id}")
	@Consumes("application/*")
	public Response deletar(@PathParam("id") int id) {
		try{
			ProdutoService service = new ProdutoService();
			service.deletar(id);			
			return this.buildResponse("Produto deletado com sucesso!");
		}catch(HamburgueriaException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/buscarId/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response buscarId(@PathParam("id")int id){
		try{
			ProdutoService service = new ProdutoService();
			return this.buildResponse(service.buscarId(id));
		}catch(HamburgueriaException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@PUT
	@Path("/editar")
	@Consumes("application/*")
	public Response editar(String produto){
		try{
			Produto prod = new ObjectMapper().readValue(produto, Produto.class);
			ProdutoService service = new ProdutoService();
			service.atualizar(prod);			
			return this.buildResponse("Produto editado com sucesso.");
		}catch(HamburgueriaException | IOException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}