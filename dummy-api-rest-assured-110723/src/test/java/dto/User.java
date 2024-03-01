package dto;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class User {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
    private int total;
    private int limit;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String registerDate;
    private String updateDate;
    private Location location;
    private String error;

    @Getter@Setter @AllArgsConstructor@NoArgsConstructor
    class Location {
        public String street;
        public String city;
        public String state;
        public String country;
        public String timezone;
    }
}