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
import br.com.hamburgueria.objs.Usuario;
import br.com.hamburgueria.service.UsuarioService;

@Path("UsuarioRest")
public class UsuarioRest extends UtilRest {
	
	public UsuarioRest() {
	}
	@POST
	@Path("/addUsuario")
	@Consumes("application/*")
	public Response addUsuario(String UsuarioParam) {
		try {
			Usuario user = new ObjectMapper().readValue(UsuarioParam,
					Usuario.class);
			UsuarioService service = new UsuarioService();
			service.adicionarUsuario(user);
			return this.buildResponse("Usuário cadastrado com sucesso.");
		} catch (HamburgueriaException | IOException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("buscarUsuariosPorNome/{nome}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarUsuariosPorNome(@PathParam("nome") String nome) {
		try {
			UsuarioService service = new UsuarioService();
			if(nome.equals("null")){
				nome = "";
			}
			System.out.println(nome);
			return this.buildResponse(service.buscarUsuarioPorNome(nome));

		} catch (HamburgueriaException e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}		
	}
	
	
	@DELETE
	@Path("/deletarUsuario/{id}")
	@Consumes("application/*")
	public Response deletarUsuario(@PathParam("id") int id) {
		try{
			UsuarioService service = new UsuarioService();
			service.deletarUsuario(id);			
			return this.buildResponse("Usuário deletado com sucesso!");
		}catch(HamburgueriaException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/buscarUsuarioPeloId/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response buscarUsuarioPeloId(@PathParam("id")int id){
		try{
			UsuarioService service = new UsuarioService();
			return this.buildResponse(service.buscarUsuarioPorId(id));
		}catch(HamburgueriaException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@PUT
	@Path("/editarUsuario")
	@Consumes("application/*")
	public Response editarUsuario(String usuarioParam){
		try{
			Usuario user = new ObjectMapper().readValue(usuarioParam, Usuario.class);
			UsuarioService service = new UsuarioService();
			service.atualizarUsuario(user);			
			return this.buildResponse("Usuário editado com sucesso.");
		}catch(HamburgueriaException | IOException e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}