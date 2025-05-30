package com.carroapi.DAO;

import com.carroapi.model.Carro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroDAO {

    public void adicionar(Carro carro) throws SQLException {
        String sql = "INSERT INTO carro (marca, modelo, preco) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getPreco());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    carro.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public List<Carro> listarTodos() throws SQLException {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carro";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getLong("id"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setPreco(rs.getInt("preco"));
                carros.add(carro);
            }
        }
        return carros;
    }

    public Optional<Carro> buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM carro WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Carro carro = new Carro();
                    carro.setId(rs.getLong("id"));
                    carro.setMarca(rs.getString("marca"));
                    carro.setModelo(rs.getString("modelo"));
                    carro.setPreco(rs.getInt("preco"));
                    return Optional.of(carro);
                }
            }
        }
        return Optional.empty();
    }

    public void atualizar(Carro carro) throws SQLException {
        String sql = "UPDATE carro SET marca = ?, modelo = ?, preco = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getPreco());
            stmt.setLong(4, carro.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM carro WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
