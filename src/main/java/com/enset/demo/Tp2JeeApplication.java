package com.enset.demo;

import com.enset.demo.entities.Patient;
import com.enset.demo.reposetories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2JeeApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp2JeeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
        patientRepository.save(new Patient(null, "Ahmad", new Date(), 2300, false));
        patientRepository.save(new Patient(null, "yassine", new Date(), 5000, true));
        patientRepository.save(new Patient(null, "farah", new Date(), 1500, false));
        patientRepository.save(new Patient(null, "karim", new Date(), 2400, false));
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });

        System.out.println("*******************************");
        Patient patient = patientRepository.findById(4L).get();
        System.out.println(patient.getName());
        System.out.println("*******************************");
        List<Patient> patients = patientRepository.findByNameContains("r");
        patients.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("*******************************");
        List<Patient> patients2 = patientRepository.findByMalade(true);
        patients2.forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("*******************************");
        List<Patient> patients3 = patientRepository.findByNameContainsAndMalade("r", true);
        patients3.forEach(p -> {
            System.out.println(p.toString());
        });*/
    }
}

