package mapper;
import dto.PlayerDto;
import entities.player;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PlayerMapper {

player playerDtoToPlayer(PlayerDto playerDto);
PlayerDto playerToPlayerDto(player player);
}
