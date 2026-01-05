package com.upiiz.Restaurante.servers;

import com.upiiz.Restaurante.entities.Platillo;
import com.upiiz.Restaurante.repositories.PlatilloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatilloService {
    private final PlatilloRepository platilloRepository;

    public PlatilloService(PlatilloRepository platilloRepository) {
        this.platilloRepository = platilloRepository;
    }

    public Platillo addPlatillo(Platillo platillo) {
        return platilloRepository.save(platillo);
    }

    public List<Platillo> getAllPlatillos() {
        return (List<Platillo>) platilloRepository.findAll();
    }

    public Platillo getPlatilloById(Long id) {
        return platilloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado con id: " + id));
    }

    public Platillo updatePlatillo(Long id, Platillo platilloActualizado) {
        Platillo platilloExistente = getPlatilloById(id);

        platilloExistente.setNombre(platilloActualizado.getNombre());
        platilloExistente.setDescripcion(platilloActualizado.getDescripcion());
        platilloExistente.setPrecio(platilloActualizado.getPrecio());

        return platilloRepository.save(platilloExistente);
    }

    public void deletePlatillo(Long id) {
        if (!platilloRepository.existsById(id)) {
            throw new RuntimeException("Platillo no encontrado con id: " + id);
        }
        platilloRepository.deleteById(id);
    }
}