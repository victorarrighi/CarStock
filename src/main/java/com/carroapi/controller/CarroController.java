package com.carroapi.controller;

import com.carroapi.model.Carro;
import com.carroapi.service.CarroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "*")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public List<Carro> listarTodos() {
        return carroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Carro> buscarPorId(@PathVariable Long id) {
        return carroService.buscarPorId(id);
    }

    @PostMapping
    public Carro adicionar(@RequestBody Carro carro) {
        return carroService.adicionar(carro);
    }

    @PutMapping("/{id}")
    public Optional<Carro> atualizar(@PathVariable Long id, @RequestBody Carro carro) {
        return carroService.atualizar(id, carro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        carroService.deletar(id);
    }
}
