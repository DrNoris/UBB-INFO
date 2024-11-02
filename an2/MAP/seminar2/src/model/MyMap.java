package model;

import java.util.*;

public class MyMap {
    private final Map<Integer, List<Student>> students;

    public MyMap(){
        students = new TreeMap<>(new StudentGradeComparator());
    }

    public static class StudentGradeComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public void add(Student s){
        int medie = Math.round(s.getMedie());

        if (students.get(medie) == null){
            List<Student> list = new ArrayList<>();
            list.add(s);
            students.put(medie, list);
        }
        else{
            students.get(medie).add(s);
        }
    }

    public Set<Map.Entry<Integer, List<Student>>> getEntries(){
        return students.entrySet();
    }
}
