package com.string.wordbreak

/**
 * @author Ameya
 *
 * Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words
 *
 * @see <a href="http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/">Reference</a>
 */
class WordBreak {

    boolean success
    List<List<String>> results = []

    void init() {
        success = false
        results.clear()
    }

    void wordBreak(String word, Set<String> dictionary) {
        init()
        println "Word: ${word}"
        println "Dictionary: ${dictionary}"
        wordBreak(word, dictionary, [])
        if (success) {
            println 'Yes'
            results.each { println it.join(' ') }
        }
        else {
            println 'No'
        }
    }

    void wordBreak(String word, Set<String> dictionary, List<String> parts) {
        for (int i = 0; i < word.length(); i++) {
            String subString = word.substring(0, i+1)
            parts.add(subString)
            if (dictionary.contains(subString)) {
                int beginIndex = i + 1
                if (beginIndex < word.length()) {
                    wordBreak(word.substring(beginIndex), dictionary, parts)
                }
                else {
                    success = true
                    results.add(parts.collect())
                }
            }
            parts.remove(parts.size() - 1)
        }
    }
}

class WordBreakTester {
    static void main(String[] args) {
        WordBreak wordBreak = new WordBreak()
        Set<String> dict1 = ['a', 'bc', 'abc', 'def'] as Set
        wordBreak.wordBreak('abcdef', dict1)
        wordBreak.wordBreak('abcdef', dict1)
        Set<String> dict2 = ['mobile','samsung','sam','sung','man','mango', 'icecream',
                             'and','go','i','like','ice','cream'] as Set
        wordBreak.wordBreak('ilike', dict2)
        wordBreak.wordBreak('ilikesamsung', dict2)
        wordBreak.wordBreak('mobilegoiicecream', dict2)
    }
}


// Output:

// Word: abcdef
// Dictionary: [a, bc, abc, def]
// Yes
// a bc def
// abc def
// Word: abccdef
// Dictionary: [a, bc, abc, def]
// No
// Word: ilike
// Dictionary: [mobile, samsung, sam, sung, man, mango, icecream, and, go, i, like, ice, cream]
// Yes
// i like
// Word: ilikesamsung
// Dictionary: [mobile, samsung, sam, sung, man, mango, icecream, and, go, i, like, ice, cream]
// Yes
// i like sam sung
// i like samsung
// Word: mobilegoiicecream
// Dictionary: [mobile, samsung, sam, sung, man, mango, icecream, and, go, i, like, ice, cream]
// Yes
// mobile go i ice cream
// mobile go i icecream
