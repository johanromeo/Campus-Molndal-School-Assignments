package com.jromeo.internshipbackend.service;

import com.jromeo.internshipbackend.dto.InternshipDTO;
import com.jromeo.internshipbackend.entity.Internship;
import com.jromeo.internshipbackend.exception.InternshipNotFoundException;
import com.jromeo.internshipbackend.repository.InternshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class InternshipServiceTest {

    /**
     * Mockito is used to mock InternshipService's dependencies, such as InternshipRepository and ModelMapper, so that
     * no live CRUD-operation tests are made on the live MySQL database hosted on AWS.
     */
    @InjectMocks
    private InternshipService internshipService;
    @Mock
    private InternshipRepository internshipRepository;
    @Mock
    private ModelMapper mapper;

    private InternshipDTO dto;
    private Internship internship;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new InternshipDTO(
                1,
                "Company",
                "Description",
                "Contact Person",
                true
        );

        internship = new Internship(
                1,
                "Company",
                "Description",
                "Contact Person",
                true
        );
    }

    @Test
    public void should_create_internship() {
        // Mocking method calls
        when(mapper.map(dto, Internship.class))
                .thenReturn(internship);
        when(internshipRepository.save(internship))
                .thenReturn(internship);
        when(mapper.map(internship, InternshipDTO.class))
                .thenReturn(dto);

        // When
        InternshipDTO createdInternship = internshipService.createInternship(dto);

        // Then
        assertEquals(dto.getId(), createdInternship.getId());
        assertEquals(dto.getCompanyName(), createdInternship.getCompanyName());

        verify(mapper, times(1))
                .map(dto, Internship.class);
        verify(internshipRepository, times(1))
                .save(internship);
        verify(mapper, times(1))
                .map(internship, InternshipDTO.class);
    }

    @Test
    public void should_get_correct_internship() {
        // Given
        Integer id = 1;

        // Mocking method calls
        when(internshipRepository.findById(id))
                .thenReturn(java.util.Optional.of(internship));
        when(mapper.map(internship, InternshipDTO.class))
                .thenReturn(dto);

        // When
        InternshipDTO foundInternship = internshipService.getInternship(id);

        // Then
        assertEquals(dto.getId(), foundInternship.getId());
        assertEquals(dto.getCompanyName(), foundInternship.getCompanyName());

        verify(internshipRepository, times(1))
                .findById(id);
        verify(mapper, times(1))
                .map(internship, InternshipDTO.class);
    }

    @Test
    public void should_return_list_of_internships() {
        // Mocking method calls
        when(internshipRepository.findAll())
                .thenReturn(java.util.List.of(internship));
        when(mapper.map(internship, InternshipDTO.class))
                .thenReturn(dto);

        // When
        var internships = internshipService.getAllInternships();

        // Then
        assertEquals(1, internships.size());
        assertEquals(dto.getId(), internships.get(0).getId());
        assertEquals(dto.getCompanyName(), internships.get(0).getCompanyName());

        verify(internshipRepository, times(1))
                .findAll();
        verify(mapper, times(1))
                .map(internship, InternshipDTO.class);
    }

    @Test
    public void should_update_internship() {
        // Given
        Integer id = 1;
        InternshipDTO updatedDto = new InternshipDTO(
                1,
                "Updated Company",
                "Updated Description",
                "Updated Contact Person",
                false
        );

        // Mocking method calls
        when(internshipRepository.findById(id))
                .thenReturn(java.util.Optional.of(internship));
        when(mapper.map(internship, InternshipDTO.class))
                .thenReturn(dto);
        when(mapper.map(updatedDto, Internship.class))
                .thenReturn(internship);
        when(internshipRepository.save(internship))
                .thenReturn(internship);
        when(mapper.map(internship, InternshipDTO.class))
                .thenReturn(updatedDto);

        // When
        InternshipDTO updatedInternship = internshipService.updateInternship(id, updatedDto);

        // Then
        assertEquals(updatedDto.getId(), updatedInternship.getId());
        assertEquals(updatedDto.getCompanyName(), updatedInternship.getCompanyName());

        verify(internshipRepository, times(1))
                .findById(id);
        verify(mapper, times(1))
                .map(internship, InternshipDTO.class);
        verify(internshipRepository, times(1))
                .save(internship);
        verify(mapper, times(1))
                .map(internship, InternshipDTO.class);
    }

    @Test
    public void should_delete_internship() {
        // Given
        Integer id = 1;

        // Mocking method calls
        when(internshipRepository.findById(id))
                .thenReturn(java.util.Optional.of(internship));

        // When
        internshipService.deleteInternship(id);

        // Then
        verify(internshipRepository, times(1))
                .findById(id);
        verify(internshipRepository, times(1))
                .delete(internship);
    }

    @Test
    public void should_throw_internship_not_found_exception_when_id_is_wrong() {
        // Given
        Integer id = 1;

        // Mocking method calls
        when(internshipRepository.findById(id))
                .thenReturn(java.util.Optional.empty());

        // When
        // Then
        assertThrows(InternshipNotFoundException.class,
                () -> internshipService.getInternship(id));

        assertEquals("Internship with id: 1 not found",
                assertThrows(InternshipNotFoundException.class,
                        () -> internshipService.getInternship(id)).getMessage());
    }
}