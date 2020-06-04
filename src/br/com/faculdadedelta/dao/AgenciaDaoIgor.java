package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.AgenciaIgor;
import br.com.faculdadedelta.modelo.BancoIgor;

public class AgenciaDaoIgor {

	public void incluir (AgenciaIgor agenciaIgor) throws Exception {
	Connection conn = Conexao.conectarNoBancoDeDados();
    String sql = "INSERT INTO agencias(nome_agencia, codigo_agencia, , id_banco, digito_verificador) VALUE (?,?,?,?)";
    PreparedStatement ps = null;
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, agenciaIgor.getNome().trim());
	    ps.setString(2, agenciaIgor.getCodigo().trim());
		ps.setLong(3, agenciaIgor.getBanco().getId());
		ps.setString(4, agenciaIgor.getDigito().trim());

		ps.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception(e);
	} finally {
		Conexao.fecharConexao(conn, ps, null);
	}}
	
	public void alterar(AgenciaIgor agenciaIgor) throws Exception {
	Connection conn = Conexao.conectarNoBancoDeDados();
	String sql = "UPDATE agencias"
			+ "SET nome_agencia =?"
			+ "codigo_agencai=?"
			+ "id_banco=?"
			+ "digito_verificador=?"
			+ "WHERE id_agencia=?";
	PreparedStatement ps = null;
	try {
		ps = conn.prepareStatement(sql);

		ps = conn.prepareStatement(sql);
		ps.setString(1, agenciaIgor.getNome().trim());
	    ps.setString(2, agenciaIgor.getCodigo().trim());
		ps.setLong(3, agenciaIgor.getBanco().getId());
		ps.setString(4, agenciaIgor.getDigito().trim());
		ps.setLong(5, agenciaIgor.getId());
		ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception(e);
	} finally {
		Conexao.fecharConexao(conn, ps, null);
	}}
	
	public void excluir (AgenciaIgor agenciaIgor) throws Exception {
	Connection conn = Conexao.conectarNoBancoDeDados();
	String sql = "DELETE FROM agencias WHERE id_agencia=?";
	PreparedStatement ps = null;
	try {
		ps = conn.prepareStatement(sql);
		ps.setLong(1, agenciaIgor.getId());
		ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception(e);
	}}
	
   public List<AgenciaIgor> listar() throws Exception {
   Connection conn = Conexao.conectarNoBancoDeDados();
   String sql = "SELECT"
   	        	+ "a.id_agencia AS idAgencia"
   	        	+ "a.nome_agencia AS nomeAgencia"
   	        	+ "a.codigo_agencia AS codigoAgencia"
   	        	+ "a.digito_verificador AS digitoVerificador"
   	        	+ "b.id_banco AS idBanco"
   	        	+ "b.nome AS nome"
   	        	+ "b.codigb AS codigoB"
   	        	+ "b.cnpj AS cnpj"
   	        	+ "FROM agencais a"
   	        	+ "INNER JOIN bancos b ON a.id_agencia = b.id_banco";
   
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	List<AgenciaIgor> listaRetorno = new ArrayList<AgenciaIgor>();
	try {
	 ps = conn.prepareStatement(sql);
	 rs = ps.executeQuery();
	 while(rs.next()) {
		AgenciaIgor agenciaIgor = new AgenciaIgor();
		agenciaIgor.setId(rs.getLong("idAgencia"));
		agenciaIgor.setNome(rs.getString("nomeAgencia"));
		agenciaIgor.setCodigo(rs.getString("codigoAgencia"));
		agenciaIgor.setDigito(rs.getString("digitoVerificador"));
		
		BancoIgor bancoIgor = new BancoIgor();
		bancoIgor.setId(rs.getLong("idBanco"));
	    bancoIgor.setNome(rs.getString("nome"));
	    bancoIgor.setCodigob(rs.getString("codigoB"));
	    bancoIgor.setCnpj(rs.getString("cnpj"));
		
	    agenciaIgor.setBanco(bancoIgor);
	    listaRetorno.add(agenciaIgor);
	 }
	 } catch (SQLException e){
		 e.printStackTrace();
		 throw new Exception(e);
	} finally {
		Conexao.fecharConexao(conn, ps, rs);
	}
	return listaRetorno;  
}
}