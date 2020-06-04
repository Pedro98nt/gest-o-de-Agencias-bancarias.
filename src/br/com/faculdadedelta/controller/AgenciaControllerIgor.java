package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.AgenciaDaoIgor;
import br.com.faculdadedelta.modelo.AgenciaIgor;
import br.com.faculdadedelta.modelo.BancoIgor;

@ManagedBean
@SessionScoped
public class AgenciaControllerIgor {

	private AgenciaIgor agencia = new AgenciaIgor();
	private AgenciaDaoIgor dao = new AgenciaDaoIgor();
	private BancoIgor bancoSelecionado = new BancoIgor();

	public AgenciaIgor getAgencia() {
		return agencia;
	}

	public void setAgencia(AgenciaIgor agencia) {
		this.agencia = agencia;
	}

	public BancoIgor getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(BancoIgor bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}
	
	public void limparCampo() {
		agencia= new AgenciaIgor();
		bancoSelecionado= new BancoIgor();
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		if(agencia.getId()==null) {
			agencia.setBanco(bancoSelecionado);
				dao.incluir(agencia);
				limparCampo();
				exibirMensagem("inclusão realizada com sucesso");
			} else {
				dao.alterar(agencia);
				limparCampo();
				exibirMensagem("alteração realizada com sucesso");
			}
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		return "cadastroAgencia.xhtml";
	}
	
	public String editar() {
		bancoSelecionado = agencia.getBanco();
		return "cadastroAgencia.xhtml";
		
	}
	
	public String excluir() {
		
		try {
			dao.excluir(agencia);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return "listaAgencia.xhtml";
		
	}
	
	public List<AgenciaIgor> getLista(){
		List<AgenciaIgor>listaRetorno = new ArrayList<AgenciaIgor>();
		
		try {
			listaRetorno= dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		
		
		return listaRetorno;
		
	}
	
}
