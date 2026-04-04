package entity.player;

import entity.team.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
    private Long id;
    private TeamEntity teamId;
    private String firstName;
    private String lastName;
    private String position;
    private int age;
    private int height;
    private int weight;
}
