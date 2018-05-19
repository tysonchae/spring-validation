package com.tyson.springvalidaton.domain.member.dto;

import com.tyson.springvalidaton.domain.member.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberRequestDto {

    private Long id;

    @NotBlank(message = "Insert your name.")
    private String name;

    @NotBlank(message = "Insert phone number.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11 digits are permitted")
    private String phoneNumber;

    @NotBlank(message = "Insert email address")
    @Email(message = "follow email address form")
    private String email;

    public MemberRequestDto() {
    }

    public Member toEntity(){
        String[] phones = parsePhone();
        return new Member(name, phones[0], phones[1], phones[2], email);
    }

    private String[] parsePhone(){
        String[] phones = new String[3];
        int mid = phoneNumber.length() == 10? 7:8;
        phones[0] = phoneNumber.substring(0,3);
        phones[1] = phoneNumber.substring(4, mid);
        phones[2] = phoneNumber.substring(mid, phoneNumber.length()-1);
        return phones;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
