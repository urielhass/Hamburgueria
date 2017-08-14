package br.com.hamburgueria.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.google.gson.Gson;

@Path("Sessao")
public class SessaoRest {

	@Context
	private HttpServletRequest req;
	
	@POST
	@Path("/getSessao/{sessao}")
	@Produces("application/json")
	public String getSessao(@PathParam("sessao") String parametro){
		
		HttpSession sessao = req.getSession(false);
		String variSessao = (String)sessao.getAttribute(parametro);
		Map<String, String> msg = new HashMap<String, String>();
		
		msg.put("sessao", variSessao);
		String json = new Gson().toJson(msg);
		return json;
	}
}
