package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BancoDaoIgor;
import br.com.faculdadedelta.modelo.BancoIgor;

@ManagedBean
@SessionScoped
public class BancoControllerIgor {

	private BancoIgor banco = new BancoIgor();
	private BancoDaoIgor dao= new BancoDaoIgor();
	

	public BancoIgor getBanco() {
		return banco;
	}

	public void setBanco(BancoIgor banco) {
		this.banco = banco;
	}
	
	public void limparCampo() {
		banco = new BancoIgor();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		if(banco.getId()==null) {
				dao.incluir(banco);
				limparCampo();
				exibirMensagem("inclusão realizada com sucesso");
			}else {
				dao.alterar(banco);
				limparCampo();
				exibirMensagem("alteração realizada com sucesso");
			}
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return "cadastroBanco.xhtml";
		
	}
	public String editar() {
		return "cadastroBanco.xhtml";
		
	}
	
	public String excluir() {
		
		try {
			dao.excluir(banco);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		return "listaBanco.xhtml";
		
	}
	
	public List<BancoIgor> getLista(){
		List<BancoIgor> listaRetorno = new ArrayList<BancoIgor>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	
}
