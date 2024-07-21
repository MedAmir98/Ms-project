package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PlayerAddResponse {

    private Long idPlayer ;
    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String nationality;
    private Long playerAge;
    private TeamDTO teamDto;
}
