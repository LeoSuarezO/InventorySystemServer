package edu.inventory.core.repository;

import edu.inventory.core.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Query(value = "SELECT * FROM Medicine WHERE medicine_name = :medicine_name", nativeQuery = true)
    List<Medicine> findAllByName (String medicine_name);
}
