package edu.inventory.core.controller;

import edu.inventory.core.entity.Medicine;
import edu.inventory.core.entity.Supplie;
import edu.inventory.core.repository.SupplieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplies")
public class SupplieRestController {

    @Autowired
    private SupplieRepository supplieRepository;

    @GetMapping
    public List<Supplie> getSupplies(){
        return supplieRepository.findAll();
    }

    @GetMapping("{idsupplie}")
    public ResponseEntity<Supplie> getSupplieById(@PathVariable Long idsupplie) {
        Supplie supplie = supplieRepository.findById(idsupplie).orElse(null);
        if (supplie != null) {
            return ResponseEntity.ok(supplie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Supplie> createSupplie(@RequestBody Supplie supplie) {
        Supplie newSupplie = supplieRepository.save(supplie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSupplie);
    }

    // Endpoint para actualizar un producto existente
    @PutMapping("/{idsupplie}")
    public ResponseEntity<Supplie> updateMedicine(@PathVariable Long idsupplie, @RequestBody Medicine updatedMedicine) {
        Supplie existingSupplie = supplieRepository.findById(idsupplie).orElse(null);
        if (existingSupplie != null) {
            if(updatedMedicine.getMedicine_name() != null){
                existingSupplie.setSupplie_name(updatedMedicine.getMedicine_name());
            }
            if ((updatedMedicine.getMedicine_name() != null)){
                existingSupplie.setSupplie_type(updatedMedicine.getMedicine_type());
            }
            Supplie updatedSupplieBD = supplieRepository.save(existingSupplie);
            return ResponseEntity.ok(updatedSupplieBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{idSupplue}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long idSupplue) {
        supplieRepository.deleteById(idSupplue);
        return ResponseEntity.noContent().build();
    }
}
