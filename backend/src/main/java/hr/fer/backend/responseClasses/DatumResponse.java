package hr.fer.backend.responseClasses;

import hr.fer.backend.model.PolarnaSvijetlost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatumResponse {

    private List<PolarnaSvijetlost> polarna;

    private Timestamp timestamp;
}
