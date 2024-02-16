package edu.inventory.core.controller;

import edu.inventory.core.entity.Medicine;
import edu.inventory.core.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineRestController {

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping
    public List<Medicine> getMedicines(){
        return medicineRepository.findAll();
    }

    @GetMapping("{idmedicine}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long idmedicine) {
        Medicine medicine = medicineRepository.findById(idmedicine).orElse(null);
        if (medicine != null) {
            return ResponseEntity.ok(medicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        Medicine newMedicine = medicineRepository.save(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMedicine);
    }

    // Endpoint para actualizar un producto existente
    @PutMapping("/{idmedicine}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long idmedicine, @RequestBody Medicine updatedMedicine) {
        Medicine existingMedicine = medicineRepository.findById(idmedicine).orElse(null);
        if (existingMedicine != null) {
            if(updatedMedicine.getMedicine_name() != null){
                existingMedicine.setMedicine_name(updatedMedicine.getMedicine_name());
            }
            if ((updatedMedicine.getMedicine_name() != null)){
                existingMedicine.setMedicine_type(updatedMedicine.getMedicine_type());
            }
            Medicine updatedMedicineBD = medicineRepository.save(existingMedicine);
            return ResponseEntity.ok(updatedMedicineBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{idmedicine}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long idmedicine) {
        medicineRepository.deleteById(idmedicine);
        return ResponseEntity.noContent().build();
    }

}
