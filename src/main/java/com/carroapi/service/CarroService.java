package com.carroapi.service;

import com.carroapi.DAO.CarroDAO;
import com.carroapi.model.Carro;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroDAO carroDAO;

    public CarroService() {
        this.carroDAO = new CarroDAO();
    }

    public List<Carro> listarTodos() {
        try {
            return carroDAO.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar carros", e);
        }
    }

    public Optional<Carro> buscarPorId(Long id) {
        try {
            return carroDAO.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar carro por ID", e);
        }
    }

    public Carro adicionar(Carro carro) {
        try {
            carroDAO.adicionar(carro);
            return carro;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar carro", e);
        }
    }

    public Optional<Carro> atualizar(Long id, Carro carro) {
        try {
            Optional<Carro> carroExistente = carroDAO.buscarPorId(id);
            if (carroExistente.isPresent()) {
                carro.setId(id);
                carroDAO.atualizar(carro);
                return Optional.of(carro);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar carro", e);
        }
    }

    public void deletar(Long id) {
        try {
            carroDAO.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar carro", e);
        }
    }
}
