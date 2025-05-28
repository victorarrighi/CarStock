package com.carroapi.service;

import com.carroapi.model.Carro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private List<Carro> carros = new ArrayList<>();
    private Long nextId = 1L;

    public List<Carro> listarTodos() {
        return carros;
    }

    public Optional<Carro> buscarPorId(Long id) {
        return carros.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Carro adicionar(Carro carro) {
        carro.setId(nextId++);
        carros.add(carro);
        return carro;
    }

    public Optional<Carro> atualizar(Long id, Carro carroAtualizado) {
        return buscarPorId(id).map(carro -> {
            carro.setModelo(carroAtualizado.getModelo());
            carro.setMarca(carroAtualizado.getMarca());
            carro.setPreco(carroAtualizado.getPreco());
            return carro;
        });
    }

    public boolean deletar(Long id) {
        return carros.removeIf(c -> c.getId().equals(id));
    }
}
