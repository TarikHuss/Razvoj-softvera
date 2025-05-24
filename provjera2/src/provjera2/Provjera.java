package provjera2;

import java.util.*;
import java.util.stream.*;

public class Provjera {
	
public static void main(String[] args) {

	List<String> stringList = new ArrayList<>();
    Random rand = new Random();
    
    String abeceda = "abcdefghijklmnopqrstuvwxyz";

    for (int i = 0; i < 50; i++) {
        int duzina = rand.nextInt(8) + 3; 
        String s = "";
        for (int j = 0; j < duzina; j++) {
            int index = rand.nextInt(abeceda.length());
            s += abeceda.charAt(index);
        }
        stringList.add(s);
    }
	
    stringList.forEach(System.out::println);

    System.out.println();
    
    List<String> lista1 = stringList.stream()
    .distinct()
    .collect(Collectors.toList());

    List<Integer> lista2 = lista1.stream()
            .map(String::length)
            .peek(len -> System.out.println("Duzina: " + len))
            .collect(Collectors.toList());

    System.out.println();
    
    List<Integer> lista3 = lista2.stream()
            .sorted((Integer a, Integer b) -> b-a)
            .peek(System.out::println)
            .collect(Collectors.toList());

    System.out.println();
    
    List<String> lista4 = lista3.stream()
            .map(String::valueOf)
            .collect(Collectors.toList());
    
    int[] parni = lista3.stream()
            .filter(n -> n % 2 == 0)
            .peek(n -> System.out.println("Paran: " + n))
            .mapToInt(Integer::intValue)
            .toArray();
 
}
}