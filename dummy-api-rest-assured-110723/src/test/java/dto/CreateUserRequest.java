package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String title; // @JsonIgnore - eto dlja ignore polja, ego nuzno vstavljatj pered tem polem, kotoroe mi xotim ignorirovatj
    private String phone;
//    private String registerDate;
//    private String updatedDate;
}