import domain.Nota;
import domain.Student;
import domain.Tema;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Student> getStudents() {
        Student s1 = new Student("andrei", 221);
        s1.setId(2L);
        Student s2 = new Student("dan", 222);
        s2.setId(2L);
        Student s3 = new Student("and3", 221);
        s3.setId(2L);
        Student s4 = new Student("costel", 222);
        s4.setId(2L);
        return Arrays.asList(s1, s2, s3, s4);
    }

    private static List<Tema> getTeme() {
        return Arrays.asList(
                new Tema("t1", "desc1"),
                new Tema("t2", "desc2"),
                new Tema("t3", "desc3"),
                new Tema("t4", "desc4")
        );
    }

    private static List<Nota> getNote(List<Student> stud, List<Tema> teme) {
        return Arrays.asList(
                new Nota(stud.get(0), teme.get(0), 10d, LocalDate.of(2019, 11, 2), "profesor1"),
                new Nota(stud.get(1), teme.get(0), 9d, LocalDate.of(2019, 11, 2).minusWeeks(1), "profesor1"),
                new Nota(stud.get(1), teme.get(1), 10d, LocalDate.of(2019, 10, 20), "profesor2"),
                new Nota(stud.get(1), teme.get(2), 10d, LocalDate.of(2019, 10, 20), "profesor2"),
                new Nota(stud.get(2), teme.get(1), 7d, LocalDate.of(2019, 10, 28), "profesor12"),
                new Nota(stud.get(2), teme.get(3), 9d, LocalDate.of(2019, 10, 27), "profesor2"),
                new Nota(stud.get(1), teme.get(3), 10d, LocalDate.of(2019, 10, 29), "profesor2")
        );
    }


//    public static void report1(List<Nota> note, String string) {
//        List<Nota> filtrare = note.stream()
//                .filter(x -> x.getStudent().getNume().contains(string))
//                .collect(Collectors.toList());
//
//        Map<Student, ArrayList<Double>> medie = new HashMap<>();
//
//        for (Nota n : filtrare) {
//            Student student = n.getStudent();
//            medie.putIfAbsent(student, new ArrayList<>()); // Initializes an empty list if the student is not already in the map
//            medie.get(student).add(n.getNota()); // Adds the grade to the student's list
//        }
//    }


    public static double average(List<Nota> notaList) {
        double sum = notaList.stream().map(Nota::getValue).reduce(0d, Double::sum);
        return sum / notaList.size();
    }

    public static void report1(List<Nota> note, String string) {
        Map<Student, List<Nota>> studentsGrades = note
                .stream()
                .collect(Collectors.groupingBy(Nota::getStudent));
        studentsGrades.entrySet()
                .stream()
                .filter(x -> x.getKey().getName().contains(string))
                .sorted((o1, o2) -> {
                    double avg1 = average(o1.getValue());
                    double avg2 = average(o2.getValue());
                    if (avg1 > avg2) return -1;
                    else return 0;
                })
                .forEach(x -> System.out.println(x.getKey().getName() + "; media: " + average(x.getValue())));
    }


    public static void report2(List<Nota> note, String string) {
        Map<String, List<Nota>> profesori = note
                .stream()
                .collect(Collectors.groupingBy(Nota::getTeacher));

        profesori.entrySet()
                .stream()
                .filter(x -> x.getKey().contains(string))
                .sorted((o1, o2) -> {
                    double avg1 = average(o1.getValue());
                    double avg2 = average(o2.getValue());
                    if (avg1 > avg2) return -1;
                    else return 0;
                })
                .forEach(x -> System.out.println(x.getKey().toString() + "; Medie: " + average(x.getValue())));
    }

    public static void report3(List<Nota> gradeList, int group) {
        List<Tema> teme = gradeList.stream()
                .filter(x -> x.getStudent().getGroup() == group)
                .collect(Collectors.groupingBy(
                        Nota::getTema,
                        Collectors.mapping(Nota::getStudent, Collectors.toSet()) // Collect unique students per tema
                ))
                .entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size())) // Sort by student count descending
                .map(Map.Entry::getKey) // Get only the tema (key) from each entry
                .toList();
    }


    public static void report4v2(List<Nota> gradeList, String startNumber) {
        List<>
    }

    public static void main(String[] args) {
        List<Student> students = getStudents();
        List<Tema> teme = getTeme();
        List<Nota> note = getNote(students, teme);
    }
}