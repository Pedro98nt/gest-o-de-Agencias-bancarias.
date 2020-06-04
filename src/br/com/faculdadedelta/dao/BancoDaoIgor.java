package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.BancoIgor;

public class BancoDaoIgor {

	public void incluir(BancoIgor bancoIgor) throws Exception  {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="INSERT INTO bancos (nome, codigob, cnpj) VALUE (?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,  bancoIgor.getNome().trim());
			ps.setString(2, bancoIgor.getCodigob().trim());
			ps.setString(3, bancoIgor.getCnpj().trim());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}}
     	
	public void alterar(BancoIgor bancoIgor) throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE bancos SET nome =?, codigob=?, cnpj=? WHERE id_agencia=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bancoIgor.getNome().trim());
			ps.setString(2, bancoIgor.getCodigob().trim());
			ps.setString(3, bancoIgor.getCnpj().trim());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}}
	
	public void excluir (BancoIgor bancoIgor) throws Exception {
		Connection conn =  Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM bancos WHERE id_agencia=?";
		PreparedStatement ps = null;
	    try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, bancoIgor.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}}
	
	
	public List<BancoIgor>listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_agencia, nome, codigob, cnpj FROM agencias";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BancoIgor> listaRetorno = new ArrayList<BancoIgor>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
			BancoIgor banco = new BancoIgor();
				banco.setId(rs.getLong("id"));
				banco.setNome(rs.getString("nome_banco"));
				banco.setCodigob(rs.getString("codgio_banco"));
				banco.setCnpj(rs.getString("cnpj_banco"));
				
				listaRetorno.add(banco);
			}
			
			
		
		} catch(SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	
	public BancoIgor pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_banco, nome, codigob, cnpj FROM bancos WHERE id_banco=?";
		PreparedStatement ps  = null;
		ResultSet rs = null;
		BancoIgor retorno = new BancoIgor();
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			ps.setLong(1, id);
			
			if(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNome(rs.getString("nome_banco").trim());
				retorno.setCodigob(rs.getString("codgio_banco").trim());
				retorno.setCnpj(rs.getString("cnpj_banco").trim());
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return retorno;
	}}