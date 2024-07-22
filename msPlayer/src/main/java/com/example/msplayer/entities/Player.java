package com.example.msplayer.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String nationality;
    private Long playerAge;
    private String teamId ;

}
