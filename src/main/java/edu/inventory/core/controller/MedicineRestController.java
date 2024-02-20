package edu.inventory.core.controller;

import edu.inventory.core.entity.Medicine;
import edu.inventory.core.repository.MedicineRepository;
import edu.inventory.core.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    //Endpoint para obtener el listado de medicamentos
    @GetMapping
    public ResponseEntity<?> getMedicines(){
        return ResponseEntity.ok(medicineRepository.findAll());
    }

    //Endpoint para obtener medicamentos por nombre
    @GetMapping("{medicine_name}")
    public ResponseEntity<?> getMedicineByName(@PathVariable String medicine_name) {
        List<Medicine> medicineList = medicineRepository.findAllByName(medicine_name);
        if (medicineList != null) {
            return ResponseEntity.ok(medicineList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para crear un nuevo medicamento
    @PostMapping
    public ResponseEntity<?> createMedicine(@RequestBody Medicine medicine) {
        List<Medicine> medicineList = medicineRepository.findAllByName(medicine.getMedicine_name());
        if(medicineList != null){
            for(Medicine medicine1: medicineList) {
                if (medicine.getMedicine_exp_date().equals(medicine1.getMedicine_exp_date())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }
        }
        Medicine medicineCreate = medicineRepository.save(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineCreate);
    }


    // Endpoint para actualizar un medicamento existente
    @PutMapping("/{idmedicine}")
    public ResponseEntity<?> updateMedicine(@PathVariable Long idmedicine, @RequestBody Medicine updatedMedicine) {
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

    // Endpoint para eliminar un medicamento por su ID
    @DeleteMapping("/{idmedicine}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long idmedicine) {
        medicineRepository.deleteById(idmedicine);
        return ResponseEntity.noContent().build();
    }

}
