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
 */
class Flatten {

    static Map<String, String> flatten(Map<String, Object> map) {
        if(map == null) {
            return null
        }
        Map<String, String> flatMap = [:]
        flatten(map, null, flatMap)
        return flatMap
    }

    static void flatten(Map.Entry<String, Object> entry, String parentKey, Map<String, String> flatMap) {
        String parentKeyComponent = "${parentKey != null ? parentKey + '.' : ''}"
        if(entry.value instanceof String) {
            flatMap.put("${parentKeyComponent}${entry.key}" as  String, entry.value as String)
            return
        }
        flatten(entry.value as Map, "${parentKeyComponent}${entry.key}", flatMap)
    }

    static void flatten(Map<String, Object> map, String parentKey, Map<String, String> flatMap) {
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            flatten(entry, parentKey, flatMap)
        }
    }
}

class FlattenTester {
    static void main(String[] args) {
        assert null == Flatten.flatten(null)
        assert [:] == Flatten.flatten([:])
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
        assert ['a':'A', 'b.b1':'B1', 'b.b2.b21':'B21', 'b.b2.b22.b221':'B221', 'c':'C'] == Flatten.flatten(map)
    }
}
