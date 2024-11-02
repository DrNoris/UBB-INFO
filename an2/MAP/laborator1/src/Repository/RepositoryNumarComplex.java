package Repository;

import Domain.NumarComplex;
import Domain.Operation;

import java.util.ArrayList;

public class RepositoryNumarComplex {
    private final ArrayList<NumarComplex> numereComplexe;
    private final ArrayList<Operation> operations;

    public RepositoryNumarComplex(){
        this.operations = new ArrayList<>();
        this.numereComplexe = new ArrayList<>();
    }

    public void createNumarComplex(double real, double imaginar){
        NumarComplex numarComplex = new NumarComplex(real, imaginar);
        numereComplexe.add(numarComplex);
    }

    public void createOperator(Operation operation){
        operations.add(operation);
    }

    public ArrayList<NumarComplex> getNumereComplexe(){
        return numereComplexe;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }
}
