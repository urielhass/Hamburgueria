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
import br.com.hamburgueria.objs.Funcionario;
import br.com.hamburgueria.service.FuncionarioService;

@Path("funcionarioRest")
public class FuncionarioRest extends UtilRest {
	
	public FuncionarioRest() {
	}
	@POST
	@Path("/addFuncionario")
	@Consumes("application/*")
	public Response addFuncionario(String funcionarioParam) {
		try {
			Funcionario funcionario = new ObjectMapper().readValue(funcionarioParam,
					Funcionario.class);
			FuncionarioService service = new FuncionarioService();
			service.adicionarFuncionario(funcionario);
			return this.buildResponse("Funcionário cadastrado com sucesso.");
		} catch (HamburgueriaException | IOException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("buscarFuncionariosPorNome/{nome}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarFuncionariosPorNome(@PathParam("nome") String nome) {
		try {
			FuncionarioService service = new FuncionarioService();
			if(nome.equals("null")){
				nome = "";
			}
			return this.buildResponse(service.buscarFuncionarioPorNome(nome));

		} catch (HamburgueriaException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}		
	}
	
	
	@DELETE
	@Path("/deletarFuncionario/{id}")
	@Consumes("application/*")
	public Response deletarFuncionario(@PathParam("id") int id) {
		try{
			FuncionarioService funcionarioService = new FuncionarioService();
			funcionarioService.deletarFuncionario(id);			
			return this.buildResponse("Funcionário deletado com sucesso!");
		}catch(HamburgueriaException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/buscarFuncionarioPeloId/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response buscarFuncionarioPeloId(@PathParam("id")int id){
		try{
			FuncionarioService funcionarioService = new FuncionarioService();
			return this.buildResponse(funcionarioService.buscarFuncionarioPorId(id));
		}catch(HamburgueriaException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@PUT
	@Path("/editarFuncionario")
	@Consumes("application/*")
	public Response editarFuncionario(String funcionarioParam){
		try{
			Funcionario funcionario = new ObjectMapper().readValue(funcionarioParam, Funcionario.class);
			FuncionarioService service = new FuncionarioService();
			service.atualizarFuncionario(funcionario);			
			return this.buildResponse("Funcionário editado com sucesso.");
		}catch(HamburgueriaException | IOException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}