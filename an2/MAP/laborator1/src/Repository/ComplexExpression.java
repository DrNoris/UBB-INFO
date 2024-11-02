package Repository;

import Domain.NumarComplex;
import Domain.Operation;

import java.util.ArrayList;

public abstract class ComplexExpression {
    protected Operation operation;
    protected ArrayList<NumarComplex> numereComplexe;

    public ComplexExpression(Operation operation, ArrayList<NumarComplex> numereComplexe){
        this.operation = operation;
        this.numereComplexe = numereComplexe;
    }

    public NumarComplex execute(){
        return executeOneOperation();
    }

    public abstract NumarComplex executeOneOperation();
}
