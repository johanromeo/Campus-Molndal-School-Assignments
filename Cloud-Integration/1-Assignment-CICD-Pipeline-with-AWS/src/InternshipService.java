package com.jromeo.internshipbackend.service;

import com.jromeo.internshipbackend.dto.InternshipDTO;
import com.jromeo.internshipbackend.entity.Internship;
import com.jromeo.internshipbackend.exception.InternshipNotFoundException;
import com.jromeo.internshipbackend.repository.InternshipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService {
    private final InternshipRepository internshipRepository;

    /**
     * ModelMapper dependency makes it easy to map from Internship to InternshipDTO and vice versa.
     */
    private final ModelMapper mapper;

    public InternshipService(InternshipRepository internshipRepository, ModelMapper mapper) {
        this.internshipRepository = internshipRepository;
        this.mapper = mapper;
    }

    public InternshipDTO createInternship(InternshipDTO dto) {
        Internship internship = mapper.map(dto, Internship.class);

        internshipRepository.save(internship);

        return mapper.map(internship, InternshipDTO.class);
    }

    public InternshipDTO getInternship(Integer id) {
        Internship internship = findInternshipOrThrowException(id);
        return mapper.map(internship, InternshipDTO.class);
    }

    public List<InternshipDTO> getAllInternships() {
        List<Internship> internships = internshipRepository.findAll();

        return internships
                .stream()
                .map(internship -> mapper.map(internship, InternshipDTO.class))
                .toList();
    }

    public InternshipDTO updateInternship(Integer id, InternshipDTO dto) {
        Internship internship = findInternshipOrThrowException(id);

        internship.setCompanyName(dto.getCompanyName());
        internship.setDescription(dto.getDescription());
        internship.setContactPerson(dto.getContactPerson());
        internship.setHasApplied(dto.isHasApplied());

        internshipRepository.save(internship);

        return mapper.map(internship, InternshipDTO.class);
    }

    public void deleteInternship(Integer id) {
        Internship deletedInternship = findInternshipOrThrowException(id);

        internshipRepository.delete(deletedInternship);
    }

    /**
     * Method for finding an Internship from MySQL database or throw custom-made exception if id isn't found.
     */
    public Internship findInternshipOrThrowException(Integer id) {
        return internshipRepository.findById(id)
                .orElseThrow(() -> new InternshipNotFoundException("Internship with id: " + id + " not found"));
    }
}
