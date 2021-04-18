package com.enset.demo.reposetories;

import com.enset.demo.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
  /*  public List<Patient> findByNameContains(String name);
    public List<Patient> findByMalade(boolean b);
    public List<Patient> findByNameContainsAndMalade(String name,boolean b);
*/
    public Page<Patient> findByNameContains(String mc, Pageable pageable);
}
