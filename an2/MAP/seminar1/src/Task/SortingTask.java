package Task;

public class SortingTask extends Task{
    private final Sorter sorter;
    private final int[] numbers;

    public SortingTask(Sorter sorter, int[] numbers){
        this.sorter = sorter;
        this.numbers = numbers;
    }

    @Override
    public void execute() {
        sorter.sort(numbers);

        for(int i : numbers){
            System.out.print(i + " ");
        }

        System.out.println();
    }
}
