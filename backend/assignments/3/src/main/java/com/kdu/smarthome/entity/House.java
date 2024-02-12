package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name="houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long houseId;
    @JsonProperty("name")
    private String houseName;
    private String address;
    private String houseAdmin;


    @ManyToMany(mappedBy = "house")
    private Set<User> users;


    @OneToMany(mappedBy = "house",cascade = CascadeType.ALL)
    private Set<Room> rooms;
    public void addUserToHouse(User myUser) {
        this.users.add(myUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House house)) return false;
        return Objects.equals(houseId, house.houseId);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("House{");
        sb.append("houseId=").append(houseId);
        sb.append(", houseName='").append(houseName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", houseAdmin='").append(houseAdmin).append('\'');
        sb.append(", rooms=[");
        for (Room room : rooms) {
            sb.append(room);
            sb.append(", ");
        }
        // Remove the trailing comma and space if there are rooms
        if (!rooms.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]}");
        return sb.toString();
    }
}
