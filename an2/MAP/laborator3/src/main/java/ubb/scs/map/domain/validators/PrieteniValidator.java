package ubb.scs.map.domain.validators;

import ubb.scs.map.domain.Prietenie;
import ubb.scs.map.domain.Utilizator;

public class PrieteniValidator implements Validator<Prietenie> {
    @Override
    public void validate(Prietenie entity) throws ValidationException {
        if(entity.getId().getRight() == 0 || entity.getId().getLeft() == 0)
            throw new ValidationException("Invalid ID");
    }
}
