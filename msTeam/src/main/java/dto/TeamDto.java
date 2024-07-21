package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor

public class TeamDto {

    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private String country ;
    private String league ;
}
