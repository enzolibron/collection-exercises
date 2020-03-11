package collectionExercises;

import java.util.*;
import java.util.stream.Collectors;

class CollectionExercise {
    public static void main(String[] args) {
        List<String> collectedInputs = collectInputs();
        LinkedHashMap<String, Integer> collectedWordsWithFrequencyCount = separateMultipleWordsAndCountFrequency(collectedInputs);
        getTopTreeFrequentWords(collectedWordsWithFrequencyCount).forEach((word, count) ->{
            System.out.println(word + ": " + count);
        });
    }

    public static List<String> collectInputs() {
        List<String> words = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String input;

        do {
            System.out.println("Enter a word");
            input = myObj.nextLine();  // Read user input
            System.out.println("input is: " + input);  // Output user input

            if(!input.equalsIgnoreCase("exit")) {
                separateMultipleWords(input).forEach(word->{
                    words.add(word);
                });
            }

        } while (!(input.equalsIgnoreCase("exit")));

        return words;
    }

    public static List<String> separateMultipleWordsWithoutDuplicates(String str) {
        List<String> separatedWords = Arrays.asList(str.split(" "))
                .stream()
                .distinct()
                .collect(Collectors.toList());
        return separatedWords;
    }

    public static List<String> separateMultipleWords(String str) {
        List<String> separatedWords = Arrays.asList(str.split(" "));

        return separatedWords;
    }

    public static LinkedHashMap<String, Integer> separateMultipleWordsAndCountFrequency(List<String> words) {
        LinkedHashMap<String, Integer> separatedWordsNoDuplicateAndWithFrequencyCount = new LinkedHashMap<>();
        words.forEach(word ->{
            separatedWordsNoDuplicateAndWithFrequencyCount.put(word, Collections.frequency(words, word));
        });
        return separatedWordsNoDuplicateAndWithFrequencyCount;
    }

    public static LinkedHashMap<String, Integer> getTopTreeFrequentWords(LinkedHashMap<String, Integer> inputLhm) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();

        // 1. get entrySet from LinkedHashMap object
        Set<Map.Entry<String, Integer>> entrySet =
                inputLhm.entrySet();

        // 2. convert LinkedHashMap to List of Map.Entry
        List<Map.Entry<String, Integer>> listEntrySet =
                new ArrayList<Map.Entry<String, Integer>>(
                        entrySet);


        // 3. sort list of entries using Collections class'
        Collections.sort(listEntrySet,
                new Comparator<Map.Entry<String, Integer>>() {

                    @Override
                    public int compare(Map.Entry<String, Integer> es1,
                                       Map.Entry<String, Integer> es2) {
                        return es2.getValue() - es1.getValue();
                    }
                });

        // 5. iterating list and storing in LinkedHahsMap
        for(Map.Entry<String, Integer> map : listEntrySet.subList(0, 3)){
            result.put(map.getKey(), map.getValue());
        }

        return result;
    }
}