import model.InMemoryRepository;
import model.MyMap;
import model.Student;
import model.User;

import java.net.Inet4Address;
import java.util.*;

public class Main {
    public static List<Student> getList(){
        List<Student> l = new ArrayList<>();
        l.add(new Student("1", 9.7f));
        l.add(new Student("2", 8.7f));
        l.add(new Student("3", 3.2f));
        l.add(new Student("4", 5.6f));
        l.add(new Student("5", 9.4f));
        l.add(new Student("6", 9.7f));
        l.add(new Student("7", 5.7f));

        return l;
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ion", 8.6f);
        Student s2 = new Student("Gabi", 9.6f);
        Student s3 = new Student("Ana", 5.6f);

        Set<Student> set = new HashSet<Student>();

        set.add(s1);
        set.add(s2);
        set.add(s3);

        for (Student s:set){
            System.out.println(s);
        }

        Set<Student> treeSet = new TreeSet<Student>();

        treeSet.add(s1);
        treeSet.add(s2);
        treeSet.add(s3);

        for (Student s:treeSet){
            System.out.println(s);
        }

        System.out.println();

        Map<String, Student> hashMap = new HashMap<String, Student>();

        hashMap.put(s1.getNume(), s1);
        hashMap.put(s2.getNume(), s2);
        hashMap.put(s3.getNume(), s3);

        for (Map.Entry<String, Student> s:hashMap.entrySet()){
            System.out.println(s);
        }

        Map<String, Student> treeMap = new TreeMap<String, Student>();

        treeMap.put(s1.getNume(), s1);
        treeMap.put(s2.getNume(), s2);
        treeMap.put(s3.getNume(), s3);

        for (Map.Entry<String, Student> s:treeMap.entrySet()){
            System.out.println(s);
        }

        Set<Student> students3 = new TreeSet<>((o1, o2) -> {    return o1.getName().compareTo(o2.getName());});

        System.out.println();

        MyMap map = new MyMap();
        List<Student> list = getList();
        for (Student s:list){
            map.add(s);
        }

        for (Map.Entry<Integer, List<Student>> entry: map.getEntries()){
            System.out.println(entry.getKey());
            List<Student> temp = entry.getValue();
            Collections.sort(temp);

            for (Student s : temp){
                System.out.println(s);
            }
        }

        System.out.println();

        User u1 = new User("John", "Ion"); u1.setId(2L);
        User u2 = new User("Maria", "Ana"); u2.setId(1L);
        User u3 = new User("John", "dsad"); u3.setId(3L);

        InMemoryRepository<Long, User> repo = new InMemoryRepository<>();

        repo.save(u1);
        repo.save(u2);
        repo.save(u3);

        for (User user : repo.findAll()){
            System.out.println(user);
        }
    }
}