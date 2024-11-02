package Service;

import Domain.NumarComplex;
import Domain.Operation;
import Repository.*;

import java.util.ArrayList;

public class ExpressionFactory {
    private ExpressionFactory() {}
    private static ExpressionFactory instance;

    public static ExpressionFactory getInstance(){
        if (instance == null){
            instance = new ExpressionFactory();
        }
        return instance;
    }

    public ComplexExpression createExpression(Operation operation, ArrayList<NumarComplex> args){
        if (operation.equals(Operation.ADDITION))
            return new AdditionExpression(operation, args);
        else if (operation.equals(Operation.SUBSTRACTION))
            return new SubstractionExpression(operation, args);
        else if (operation.equals(Operation.MULTIPLICATION))
            return new MultiplicationExpression(operation, args);
        else if (operation.equals(Operation.DIVISION))
            return new DivisionExpression(operation, args);

        return null;
    }
}
