package com.jromeo.internshipbackend.controller;

import com.jromeo.internshipbackend.dto.InternshipDTO;
import com.jromeo.internshipbackend.service.InternshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    /**
     * Simple RestController to set up the API, allowing the client to manipulate Internships.
     * Client receives a Response Entity of type <InternshipDTO>(<String> for DELETE request)
     * for best practice, alongside a suitable http status code.
     */

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @PostMapping("")
    public ResponseEntity<InternshipDTO> createInternship(@RequestBody InternshipDTO dto) {
        InternshipDTO createdInternship = internshipService.createInternship(dto);

        return new ResponseEntity<>(createdInternship, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipDTO> getInternshipById(@PathVariable Integer id) {
        InternshipDTO fetchedInternship = internshipService.getInternship(id);

        return new ResponseEntity<>(fetchedInternship, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<InternshipDTO>> getAllInternships() {
        List<InternshipDTO> fetchedInternships = internshipService.getAllInternships();

        return new ResponseEntity<>(fetchedInternships, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipDTO> updateInternship(@PathVariable Integer id, @RequestBody InternshipDTO dto) {
        InternshipDTO updatedInternship = internshipService.updateInternship(id, dto);

        return new ResponseEntity<>(updatedInternship, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInternship(@PathVariable Integer id) {
        internshipService.deleteInternship(id);

        return new ResponseEntity<>("Internship Deleted", HttpStatus.OK);
    }
}
