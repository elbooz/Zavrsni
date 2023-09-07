package hr.fer.backend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PrimaryKeyId implements Serializable {
    private Date datum;
    private Integer longitude;
    private Integer latitude;
}
