package hr.fer.backend.repository;

import hr.fer.backend.model.PolarnaSvijetlost;
import hr.fer.backend.model.PrimaryKey;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PolarnaSvijetlostRepository extends JpaRepository<PolarnaSvijetlost, PrimaryKeyId> {
    List<PolarnaSvijetlost> findAllByPrimaryKey_Datumvrijeme(Timestamp datumvrijeme);
    PolarnaSvijetlost findByPrimaryKey(PrimaryKey primaryKey);

    @Query(value = "select datumvrijeme from polarna where (abs(extract(epoch from (datumvrijeme - :datumvrijeme))) < extract(epoch from (interval '1 day'))) order by abs(extract(epoch from (datumvrijeme - :datumvrijeme))) limit 1", nativeQuery = true)
    Timestamp findNearestTimestamp(@Param("datumvrijeme") Timestamp datumvrijeme);
}
