package response.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import response.Rs;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRs implements Rs {
    private Long id;

    private String username;

    private String password;

    private Role role;
}
