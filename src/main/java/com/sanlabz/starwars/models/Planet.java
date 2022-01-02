package com.sanlabz.starwars.models;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "planet")
public class Planet implements Serializable {

    @Id
    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

    @UniqueElements
    private String name;

    private String climate;

    private String terrain;

    private Integer films;

}
