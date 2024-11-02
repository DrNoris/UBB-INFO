package ubb.scs.map.repository.file;

import ubb.scs.map.domain.Prietenie;
import ubb.scs.map.domain.Tuple;
import ubb.scs.map.domain.validators.Validator;

import java.time.LocalDateTime;

public class PritenieRepository extends AbstractFileRepository<Tuple<Long, Long>, Prietenie> {

    public PritenieRepository(Validator<Prietenie> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Prietenie createEntity(String line) {
        String[] splited = line.split(";");
        Prietenie prietenie = new Prietenie();
        prietenie.setId(new Tuple<>(Long.parseLong(splited[0]), Long.parseLong(splited[1])));
        prietenie.setDate(LocalDateTime.parse(splited[2]));
        return prietenie;
    }

    @Override
    public String saveEntity(Prietenie entity) {
        return entity.getId().getLeft() + ";" + entity.getId().getRight() + ";" + entity.getDate();
    }
}
