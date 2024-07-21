package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDto {

    private Long idPlayer;
    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String nationality;
    private Long playerAge;
    private Long teamId;


}
