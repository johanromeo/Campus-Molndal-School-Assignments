package com.jromeo.internshipbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternshipDTO {

    private Integer id;

    private String companyName;

    private String description;

    private String contactPerson;

    private boolean hasApplied;
}
