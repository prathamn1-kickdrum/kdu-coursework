package com.kdu.smarthome.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "kickston_sequence")
public class KickstonSequence {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
}
