package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("id")
    private long houseId;
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

}
