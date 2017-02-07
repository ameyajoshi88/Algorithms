package com.flatten

/**
 * @author Ameya
 * This program takes as input a Map which could have other nested maps
 * and flattens it
 * e.g
 * Input:
 *  {
 *      a: A
 *      b: {
 *              b1: B1
 *              b2: {
 *                  b21: B21
 *              }
 *          }
 *  }
 *
 *  Output:
 *  {
 *      a: A
 *      b.b1: B1
 *      b.b2.b21: B21
 *  }
 *
 *  Both recursive, iterative solutions are provided
 */
class Flatten {

    static Map<String, String> flattenRecursive(Map<String, Object> map) {
        if(map == null) {
            return null
        }
        Map<String, String> flatMap = [:]
        flattenRecursive(map, null, flatMap)
        return flatMap
    }

    static void flattenRecursive(Map.Entry<String, Object> entry, String parentKey, Map<String, String> flatMap) {
        String parentKeyComponent = "${parentKey != null ? parentKey + '.' : ''}"
        if(entry.value instanceof String) {
            flatMap.put("${parentKeyComponent}${entry.key}" as  String, entry.value as String)
            return
        }
        flattenRecursive(entry.value as Map, "${parentKeyComponent}${entry.key}", flatMap)
    }

    static void flattenRecursive(Map<String, Object> map, String parentKey, Map<String, String> flatMap) {
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            flattenRecursive(entry, parentKey, flatMap)
        }
    }

    static Map<String, String> flattenIterative(Map<String, Object> map) {
        if(map == null) {
            return null
        }
        Map<String, String> flatMap = [:]
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            Set<Map.Entry<String, Object>> entries = new LinkedHashSet<>()
            entries.add(entry)
            Set<String> keys = []
            while (!entries.isEmpty()) {
                entry = entries.last() // remove recently added first
                if(!keys.add(entry.key)) { // must be a parent whose children are already covered
                    keys.remove(entry.key) // remove key
                    entries.remove(entry) // remove entry
                    continue
                }
                if(entry.value instanceof String) {
                    flatMap.put("${getKey(keys)}" as String, entry.value as String)
                    keys.remove(entry.key) // remove key
                    entries.remove(entry) // remove this entry as it is processed completely
                } else {
                    Map<String, Object> valueMap = entry.value as Map
                    entries.addAll(valueMap.entrySet())
                }
            }
        }
        return flatMap
    }

    static String getKey(Set<String> keys) {
        return "${keys.join('.')}"
    }
}

class FlattenTester {
    static void main(String[] args) {
        assert null == Flatten.flattenRecursive(null)
        assert [:] == Flatten.flattenRecursive([:])
        Map<String, Object> map = [
                'a' : 'A',
                'b' : [
                        'b1' : 'B1',
                        'b2' : [
                                'b21' : 'B21',
                                'b22' : [
                                        'b221' : 'B221'
                                ]
                        ]
                ],
                'c' : 'C'
        ]
        assert ['a':'A', 'b.b1':'B1', 'b.b2.b21':'B21', 'b.b2.b22.b221':'B221', 'c':'C'] == Flatten.flattenRecursive(map)
        assert null == Flatten.flattenIterative(null)
        assert [:] == Flatten.flattenIterative([:])
        assert ['a':'A', 'b.b1':'B1', 'b.b2.b21':'B21', 'b.b2.b22.b221':'B221', 'c':'C'] == Flatten.flattenIterative(map)
    }
}
