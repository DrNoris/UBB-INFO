import Domain.NumarComplex;
import Domain.Operation;
import Repository.ComplexExpression;
import Repository.RepositoryNumarComplex;
import Service.ExpressionFactory;
import Service.ServiceNumarComplex;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Expression: ");
        Read reader = Read.getInstance();

        reader.readText();

        String[] words = reader.getWords();

        RepositoryNumarComplex repo = new RepositoryNumarComplex();
        ServiceNumarComplex serv = new ServiceNumarComplex(repo);

        for (String word : words){
            if (!"+-/*".contains(word)){
                try {
                    double[] valori = serv.convertComplexString(word);
                    serv.addNumarComplex(valori[0], valori[1]);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    return;
                }
            }
            else{
                if (!"+-/*".contains(word))
                    System.err.println("Invalid operator");
                else
                    serv.addOperator(word);
            }
        }

        ExpressionFactory expressionFactory = ExpressionFactory.getInstance();
        ArrayList<Operation> operations = serv.getOperations();
        ArrayList<NumarComplex> numarComplexes = serv.getNumere();
        while(!operations.isEmpty()) {
            int index = 0;
            for (int i = 0; i < operations.size(); ++i) {
                if (operations.get(i).equals(Operation.MULTIPLICATION) || operations.get(i).equals(Operation.DIVISION)) {
                    index = i;
                    break;
                }
            }
            ArrayList<NumarComplex> numere = new ArrayList<>();
            numere.add(numarComplexes.get(index));
            numere.add(numarComplexes.get(index + 1));

            ComplexExpression expression = expressionFactory.createExpression(operations.get(index), numere);
            NumarComplex rezultat = expression.execute();

            operations.remove(index);
            numarComplexes.remove(index);
            numarComplexes.remove(index);
            numarComplexes.add(index, rezultat);
        }
        System.out.println("Rezultatul este: " + numarComplexes.getFirst());
    }
}