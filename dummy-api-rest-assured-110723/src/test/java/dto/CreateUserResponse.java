package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateUserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String id;
    private String gender;
    private String title;
    private String registerDate;
    private String updatedDate;
    private String phone;
    public String picture;
    public String dateOfBirth;

    public Location location;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class Location {
        public String street;
        public String city;
        public String state;
        public String country;
        public String timezone;
    }
}