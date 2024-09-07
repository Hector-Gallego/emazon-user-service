package com.emazon.emazonuserservice.ports.driving.dto;

import com.emazon.emazonuserservice.domain.model.Role;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WareHouseAssistantRequestDto {

    private String name;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;

}