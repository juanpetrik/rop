package com.petrik.rop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO implements Serializable {

    private Long id;

    private String name;

    private String phone;

    @Email
    private String email;

}
