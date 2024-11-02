package Service;

import Domain.NumarComplex;
import Domain.Operation;
import Repository.RepositoryNumarComplex;

import java.util.ArrayList;

public class ServiceNumarComplex {
    private final RepositoryNumarComplex repositoryNumarComplex;

    public ServiceNumarComplex(RepositoryNumarComplex repositoryNumarComplex){
        this.repositoryNumarComplex = repositoryNumarComplex;
    }

    public void addNumarComplex(double real, double imaginar) {
        repositoryNumarComplex.createNumarComplex(real, imaginar);
        if (repositoryNumarComplex.getNumereComplexe().size() - repositoryNumarComplex.getOperations().size() > 1
            || repositoryNumarComplex.getOperations().size() > repositoryNumarComplex.getNumereComplexe().size()){
            throw new IllegalArgumentException("Invalid complex number format");
        }
    }

    public void addOperator(String word){
        switch (word){
            case "+":
                repositoryNumarComplex.createOperator(Operation.ADDITION);
                break;

            case "-":
                repositoryNumarComplex.createOperator(Operation.SUBSTRACTION);
                break;

            case "*":
                repositoryNumarComplex.createOperator(Operation.MULTIPLICATION);
                break;

            case "/":
                repositoryNumarComplex.createOperator(Operation.DIVISION);
                break;
        }
    }

    public double[] convertComplexString(String complexStr) {
        // Remove whitespace
        complexStr = complexStr.replaceAll("\\s+", "");

        // Updated regex to account for complex number formats
        String regex = "([-+]?\\d*\\.?\\d*)?([-+]?\\d*\\.?\\d*\\*?i)?";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(complexStr);
        if (matcher.matches()) {
            // Get the real part, defaulting to 0 if empty
            String realPart = matcher.group(1) != null?matcher.group(1) : "";
            String imaginaryPart = matcher.group(2) != null?matcher.group(2) : "";

            double imaginary = 0;
            double real = 0;

            if (!realPart.isEmpty() && (imaginaryPart.contains("*i") && imaginaryPart.length() < 3 || realPart.equals("-") || realPart.equals("*"))){
                imaginaryPart = realPart + imaginaryPart;
                realPart = "";
            }

            if (imaginaryPart.contains("i")){
                String s;
                if (imaginaryPart.contains("*")){
                    s = imaginaryPart.replace("i", "").replace("*", "");
                }
                else{
                    s = imaginaryPart.replace("i", "1");
                }
                imaginary = Double.parseDouble(s);
            }

            if (!realPart.isEmpty()){
                real = Double.parseDouble(realPart);
            }

            return new double[]{real, imaginary};
        } else {
            throw new IllegalArgumentException("Invalid complex number format");
        }
    }


    public ArrayList<NumarComplex> getNumere() {
        return repositoryNumarComplex.getNumereComplexe();
    }

    public ArrayList<Operation> getOperations() {
        return repositoryNumarComplex.getOperations();
    }
}
