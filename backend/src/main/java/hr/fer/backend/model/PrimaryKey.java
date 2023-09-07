package hr.fer.backend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PrimaryKey implements Serializable {
    private Timestamp datumvrijeme;
    private Integer longitude;
    private Integer latitude;
}
