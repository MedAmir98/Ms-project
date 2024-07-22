package com.example.msplayer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDto {

    private Long id;
    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String nationality;
    private Long playerAge;
    private String teamId;
    private TeamDTO teamDTO;


}
