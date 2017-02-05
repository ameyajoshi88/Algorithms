package com.array

/**
 * @author Ameya
 * This program takes as input a sentence in the form of an array of characters.
 * The words in the sentence are separated by spaces.
 * This program reverses the sentence.
 * e.g [L,E,T, ,U,S, ,C] => [C, ,U,S, ,L,E,T]
 */
class ReverseSentence {

    static void reverseSentence(char[] sentence) {
        if (sentence == null || sentence.length == 0) {
            return
        }

        // First pass: Reverse individual words
        int wordStart = 0
        for (int i=0; i<sentence.length; i++) {
            if (sentence[i] == ' ' as char) {
                reverseWord(sentence, wordStart, i-1)
                wordStart = i+1
            }
        }
        reverseWord(sentence, wordStart, sentence.length - 1) // Last word needs to be handled

        // Second pass: Treat entire sentence as a word and reverse
        reverseWord(sentence, 0, sentence.length - 1)
    }

    static void reverseWord(char[] sentence, int start, int end) {
        int p1 = start
        int p2 = end
        while (p1 < p2) {
            char temp = sentence[p1]
            sentence[p1] = sentence[p2]
            sentence[p2] = temp
            p1++
            p2--
        }
    }
}

class ReverseSentenceTester {
    static void main(String[] args) {
        // 1.
        char[] sentence = 'LET US SEE' as char[]
        ReverseSentence.reverseSentence(sentence)
        assert 'SEE US LET' as char[] == sentence
        print(sentence)
        // 2.
        sentence = ' ' as char[]
        ReverseSentence.reverseSentence(sentence)
        assert ' ' as char[] == sentence
    }
}
