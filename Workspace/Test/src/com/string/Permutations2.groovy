package com.string

/**
 * @author Ameya
 * This program finds all the permutations of a given string
 *
 * Input        Output
 * =====        ======
 * abc          [abc, acb, bac, bca, cab, cba]
 *
 * The idea is to create words starting with each letter in the word. Recursion is used for the same
 */

class Permutations2 {
    Set<String> permutations(String input) {
        if (input == null) {
            return null
        }
        if (input.length() == 2) {
            return [input, "${input.charAt(1)}${input.charAt(0)}"] // just 2 letters, so swap
        }

        Set<String> result = new TreeSet<>() // sorted
        for (int i = 0; i < input.length(); i++) {
            char startChar = input.charAt(i)
            String remaining = input.substring(0, i) + input.substring(i + 1)
            Set<String> remainingPermutations = permutations(remaining)
            remainingPermutations.each { p -> result << "${startChar}${p}" }
        }
        return result
    }
}

class PermutationsTest {
    static void main(String[] args) {
        Permutations2 permutations2 = new Permutations2()
        assert null == permutations2.permutations(null)
        assert [] as Set == permutations2.permutations('')
        assert ['abc', 'acb', 'bac', 'bca', 'cab', 'cba'] as Set == permutations2.permutations('abc')
        assert ['abcd', 'abdc', 'acbd', 'acdb', 'adbc', 'adcb',
                'bacd', 'badc', 'bcad', 'bcda', 'bdac', 'bdca',
                'cabd', 'cadb', 'cbad', 'cbda', 'cdab', 'cdba',
                'dabc', 'dacb', 'dbac', 'dbca', 'dcab', 'dcba'] as Set == permutations2.permutations('abcd')
    }
}
