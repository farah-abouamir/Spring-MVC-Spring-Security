package com.enset.demo.web;
import com.enset.demo.entities.Patient;
import com.enset.demo.reposetories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;


@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping(path="/patients")
    public String List(Model model,
                       @RequestParam(name="page", defaultValue="0")int page,
                       @RequestParam(name="size", defaultValue="5")int size,
                       @RequestParam(name="motCle", defaultValue="")String mc)
    {
        Page<Patient> pagePatients=patientRepository.findByNameContains(mc, PageRequest.of(page,size));
        model.addAttribute("patients",pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", mc);
        model.addAttribute("size", size);
        return "patients";
    }

    @GetMapping(path="/deletePatient")
    public String delete(Long id, String motCle,int page , int size){
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size+"&motCle="+motCle;
    }
    @GetMapping(path = "/formPatient")
    public String formPatient(Model model){
    model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/savePatient")
    public String savePatient( @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formPatient" ;

        patientRepository.save(patient);
        return "formPatient" ;
    }

    @GetMapping(value = "/editPatient")
    public String edit(Model model, Long id) {
        Patient p = patientRepository.getOne(id);
        model.addAttribute("patient", p);
        return "formPatient";
    }

}
