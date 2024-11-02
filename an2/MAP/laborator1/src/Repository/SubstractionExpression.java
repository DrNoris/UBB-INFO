package Repository;

import Domain.NumarComplex;
import Domain.Operation;

import java.util.ArrayList;

public class SubstractionExpression extends ComplexExpression{
    public SubstractionExpression(Operation operation, ArrayList<NumarComplex> numere){
        super(operation, numere);
    }

    @Override
    public NumarComplex executeOneOperation() {
        NumarComplex nr1 = numereComplexe.get(0);
        NumarComplex nr2 = numereComplexe.get(1);

        return NumarComplex.scadere(nr1, nr2);
    }
}
