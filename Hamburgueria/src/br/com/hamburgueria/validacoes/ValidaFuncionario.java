package br.com.hamburgueria.validacoes;

import br.com.hamburgueria.objs.Funcionario;

public class ValidaFuncionario {
	
	public boolean funcionario(Funcionario func){
		if(func.getBairro().equals("")){
			return false;
		}else if(func.getCep() - func.getCep() != 0){
			return false;
		} else if(func.getCidade().equals("")){
			return false;
		} else if(func.getCodfuncionario() - func.getCodfuncionario() != 0){
			return false;
		} else if(func.getComplemento().equals("")){
			return false;
		}else if(func.getCpf().equals("")){
			return false;
		}else if(func.getDataNascimento().equals("")){
			return false;
		}else if( func.getEmail().equals("")){
			return false;
		}else if(func.getFone() - func.getFone() != 0){
			return false;
		}else if(func.getFuncao().equals("")){
			return false;
		}else if(func.getNomeFuncionario().equals("")){
			return false;
		}else if(func.getNumero()- func.getNumero() != 0){
			return false;
		}else if(func.getRg().equals("")){
			return false;
		}else if(func.getRua().equals("")){
			return false;
		}else{
			return true;
		}
	}
	
}
