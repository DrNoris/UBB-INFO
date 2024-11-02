public class Read {
    private String[] words;
    private static Read instance;
    private Read(){}

    public static Read getInstance() {
        if (instance == null) {
            instance = new Read();
            return instance;
        }
        else
            return instance;
    }

    public void readText(){
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        words = scanner.nextLine().split(" ");
    }

    public String[] getWords(){
        return words;
    }
}
