package br.com.faculdadedelta.modelo;

public class AgenciaIgor {
  
	private Long id;
	private String nome;
	private String codigo;
	private String digito;
	private BancoIgor bancoIgor;
	
	public AgenciaIgor() {
		super();
	}
	
	public AgenciaIgor(Long id, String nome, String codigo, String digito, BancoIgor bancoIgor) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.digito = digito;
		this.bancoIgor = bancoIgor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDigito() {
		return digito;
	}
	public void setDigito(String digito) {
		this.digito = digito;
	}
	public BancoIgor getBanco() {
		return bancoIgor;
	}
	public void setBanco(BancoIgor bancoIgor) {
		this.bancoIgor = bancoIgor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgenciaIgor other = (AgenciaIgor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
