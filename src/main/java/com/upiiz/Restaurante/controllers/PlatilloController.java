package com.upiiz.Restaurante.controllers;

import com.upiiz.Restaurante.entities.Platillo;
import com.upiiz.Restaurante.servers.PlatilloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garufa/public/v1")
public class PlatilloController {

    private final PlatilloService platilloService;

    public PlatilloController(PlatilloService platilloService) {
        this.platilloService = platilloService;
    }

    @GetMapping("/platillos")
    public ResponseEntity<List<Platillo>> getAllPlatillos() {
        return ResponseEntity.ok(platilloService.getAllPlatillos());
    }

    @GetMapping("/platillos/{id}")
    public ResponseEntity<Platillo> getPlatilloById(@PathVariable Long id) {
        Platillo platillo = platilloService.getPlatilloById(id);
        if (platillo != null) {
            return ResponseEntity.ok(platillo);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/platillos")
    public ResponseEntity<Platillo> createPlatillo(@RequestBody Platillo platillo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platilloService.addPlatillo(platillo));
    }

    @PutMapping("/platillos/{id}")
    public ResponseEntity<Platillo> updatePlatillo(@PathVariable Long id, @RequestBody Platillo platillo) {
        return ResponseEntity.ok(platilloService.updatePlatillo(id, platillo)); // ✅ Pasar ambos
    }

    @DeleteMapping("/platillos/{id}")
    public ResponseEntity<Void> deletePlatillo(@PathVariable Long id) {
        platilloService.deletePlatillo(id);
        return ResponseEntity.noContent().build();
    }
}
