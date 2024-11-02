package Repository;

import Domain.NumarComplex;
import Domain.Operation;

import java.util.ArrayList;

public class DivisionExpression extends ComplexExpression{
    public DivisionExpression(Operation operation, ArrayList<NumarComplex> numereComplexe) {
        super(operation, numereComplexe);
    }

    @Override
    public NumarComplex executeOneOperation() {
        NumarComplex nr1 = numereComplexe.get(0);
        NumarComplex nr2 = numereComplexe.get(1);

        return NumarComplex.impartire(nr1, nr2);
    }
}
