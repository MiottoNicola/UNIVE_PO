package Avogador_es.WordCount;

import java.util.HashSet;
import java.util.Set;

class Result {

    private static final Set<Character> ADMISSIBLE_CHARACTERS = new HashSet<>();
    private static final Set<Character> ADMISSIBLE_ENDING = new HashSet<>();

    static {
        initialize();
    }

    public static void initialize() {
        for (char c = 'a'; c <= 'z'; c++) {
            ADMISSIBLE_CHARACTERS.add(c);
            ADMISSIBLE_ENDING.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            ADMISSIBLE_CHARACTERS.add(c);
            ADMISSIBLE_ENDING.add(c);
        }
        ADMISSIBLE_CHARACTERS.add('-');
        ADMISSIBLE_ENDING.add('-');
        ADMISSIBLE_ENDING.add('.');
        ADMISSIBLE_ENDING.add(',');
        ADMISSIBLE_ENDING.add('?');
        ADMISSIBLE_ENDING.add('!');
    }

    public static int howMany(String sentence) {
        String[] words = sentence.split("\\s+");
        int count = 0;

        for (String word : words) {
            if (!word.isEmpty()) {
                char[] characters = word.toCharArray();
                boolean valid = true;

                for (int i = 0; i < characters.length; i++) {
                    if (i == characters.length - 1 && !ADMISSIBLE_ENDING.contains(characters[i])) {
                        valid = false;
                        break;
                    } else if (!ADMISSIBLE_CHARACTERS.contains(characters[i])) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    count++;
                }
            }
        }

        return count;
    }
}
