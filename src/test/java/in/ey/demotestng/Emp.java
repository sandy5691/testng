package in.ey.demotestng;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Emp {

	String name; int salary;
    Emp(String n, int s){ name=n; salary=s; }
    public static void main(String[] args) {
    	List<String> words = Arrays.asList("apple", "banana", "watermelon", "kiwi");
    	System.out.println(words.stream().collect(Collectors.toMap(str->str, String::length))
    	.entrySet().stream().max((o1,o2)->o1.getValue()>o2.getValue()?1:o1.getValue()<o2.getValue()?-1:0)
    	.get().getKey());

    }

}
