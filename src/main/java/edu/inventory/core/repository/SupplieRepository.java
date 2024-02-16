package edu.inventory.core.repository;

import edu.inventory.core.entity.Supplie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplieRepository extends JpaRepository<Supplie, Long> {
}
