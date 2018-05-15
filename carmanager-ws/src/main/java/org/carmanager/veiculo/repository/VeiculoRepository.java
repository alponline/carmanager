package org.carmanager.veiculo.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    private Connection connection;

    public VeiculoRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Veiculo> getList() throws SQLException {
        String sql = "SELECT * from TB_VEICULO";
        final PreparedStatement ps = connection.prepareStatement(sql);
        final ResultSet rs = ps.executeQuery();
        List<Veiculo> list = new ArrayList<Veiculo>();
        while (rs.next()){
            Veiculo veiculo = new Veiculo();
            veiculo.setId(rs.getInt("id"));
            veiculo.setVeiculo(rs.getString("veiculo"));
            veiculo.setMarca(rs.getString("marca"));
            veiculo.setAno(rs.getInt("ano"));
            veiculo.setDescricao(rs.getString("descricao"));
            veiculo.setVendido(rs.getBoolean("vendido"));
            veiculo.setCreated(rs.getDate("created"));
            veiculo.setUpdated(rs.getDate("updated"));
            list.add(veiculo);
        }
        ps.close();
        return list;
    }

    public Veiculo getVeiculo(int id) throws SQLException {
        String sql = "SELECT * from TB_VEICULO WHERE ID = ?";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        final ResultSet rs = ps.executeQuery();
        Veiculo veiculo =null;
        while (rs.next()){
            veiculo = new Veiculo();
            veiculo.setId(rs.getInt("id"));
            veiculo.setVeiculo(rs.getString("veiculo"));
            veiculo.setMarca(rs.getString("marca"));
            veiculo.setAno(rs.getInt("ano"));
            veiculo.setDescricao(rs.getString("descricao"));
            veiculo.setVendido(rs.getBoolean("vendido"));
            veiculo.setCreated(rs.getDate("created"));
            veiculo.setUpdated(rs.getDate("updated"));
            break;
        }
        ps.close();
        return veiculo;
    }

    public void add(Veiculo veiculo) throws SQLException {
        String sql ="INSERT INTO TB_VEICULO (veiculo, marca, ano, descricao, vendido, created, updated) values (?,?,?,?,?,?,?)";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,veiculo.getVeiculo());
        ps.setString(2,veiculo.getMarca());
        ps.setInt(   3,veiculo.getAno());
        ps.setString(4,veiculo.getDescricao());
        ps.setBoolean(5,veiculo.getVendido());
        ps.setDate(6, veiculo.getCreated());
        ps.setDate(7, veiculo.getUpdated());
        ps.execute();
        connection.commit();
        ps.close();
    }

    public void update(Veiculo veiculo) throws SQLException {
        String sql ="UPDATE TB_VEICULO SET veiculo = ? , marca = ?, ano = ?, descricao = ?, vendido = ?,created = ?,updated = ? where id = ?";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,veiculo.getVeiculo());
        ps.setString(2,veiculo.getMarca());
        ps.setInt(   3,veiculo.getAno());
        ps.setString(4,veiculo.getDescricao());
        ps.setBoolean(5,veiculo.getVendido());
        ps.setDate(6, (Date) veiculo.getCreated());
        ps.setDate(7, (Date) veiculo.getUpdated());
        ps.setInt(8,veiculo.getId());

        ps.execute();
        connection.commit();
        ps.close();
    }

    public void delete(int id) throws SQLException {
        String sql ="DELETE from TB_VEICULO where id = ?";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.execute();
        connection.commit();
        ps.close();
    }

    public void updateData(Veiculo veiculo) throws SQLException {
        String sql ="UPDATE TB_VEICULO SET veiculo = ? ,descricao = ?,vendido = ?,created = ?,updated = ? where id = ?";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,veiculo.getVeiculo());
        ps.setString(2,veiculo.getMarca());
        ps.setInt(   3,veiculo.getAno());
        ps.setString(5,veiculo.getDescricao());
        ps.setBoolean(4,veiculo.getVendido());
        ps.setDate(6, (Date) veiculo.getCreated());
        ps.setDate(7, (Date) veiculo.getUpdated());
        ps.setInt(8,veiculo.getId());

        ps.execute();
        connection.commit();
        ps.close();
    }

    public List<Veiculo> find(String param) {
        return null;
    }
}
