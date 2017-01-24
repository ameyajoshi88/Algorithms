package com.meeting

/**
 * @author Ameya
 * This program takes availability of 2 users as 2 lists containing start and end time
 * of the time slot when they are available. The program takes a duration as input, looks at this data,
 * and returns a time slot when a meeting of the duration could be held between the 2 users. There might
 * be a situation when no meeting can be held
 *
 *  Assumption: Time slots of a user are not overlapping(disjoint)
 */

class TimeSlot {

    TimeSlot(long start, long end) {
        this.start = start
        this.end = end
    }

    TimeSlot(List<Long> slot) {
        this.start = slot[0]
        this.end = slot[1]
    }

    final long start
    final long end

    Long getDuration() {
        return this.end - this.start
    }

    String toString() {
        return "${this.start} to ${this.end}"
    }
}

class Scheduler {
    static List<Long> schedule(List<List<Long>> user1, List<List<Long>> user2, long duration) {
        List<TimeSlot> user1Slots = []
        user1.each {u ->
            user1Slots << new TimeSlot(u)
        }
        List<TimeSlot> user2Slots = []
        user2.each {u ->
            user2Slots << new TimeSlot(u)
        }
        TimeSlot slot = findTimeSlot(user1Slots, user2Slots, duration)
        return slot == null ? [] : [slot.start, slot.end]
    }

    static TimeSlot findTimeSlot(List<TimeSlot> user1Slots, List<TimeSlot> user2Slots, long duration) {
        // 1. remove slots whose duration is less than the required duration
        user1Slots = user1Slots
                .stream()
                .filter{s -> s.duration >= duration}
                .collect()
        user2Slots = user2Slots
                .stream()
                .filter{s -> s.duration >= duration}
                .collect()
        // 2. sort in chronological order
        user1Slots.sort({s1,s2 ->
            s1.start <=> s2.start
        })
        user2Slots.sort({s1,s2 ->
            s1.start <=> s2.start
        })
        // 3. find overlap
        TimeSlot result = null
        int i = 0, j = 0
        while(i < user1Slots.size() && j < user2Slots.size()) {
            TimeSlot user1Slot = user1Slots[i]
            TimeSlot user2Slot = user2Slots[j]
            if((Math.min(user1Slot.end, user2Slot.end) - Math.max(user1Slot.start, user2Slot.start)) >= duration) {
                result = new TimeSlot(Math.max(user1Slot.start, user2Slot.start),
                                      Math.max(user1Slot.start, user2Slot.start) + duration)
                break // gives first available slot
            }
            if(user1Slot.end >= user2Slot.end) {
                j++
            }
            else {
                i++
            }
        }
        return result
    }
}

class SchedulerTest {
    static void main(String[] args) {
        assert [] == Scheduler.schedule([ [0L, 5L], [10L, 20L] ], [ [0L, 2L], [12L, 20L] ], 10L)
        assert [12L, 19L] == Scheduler.schedule([ [0L, 5L], [10L, 20L] ], [ [0L, 2L], [12L, 20L] ], 7L)
        assert [0L, 2L] == Scheduler.schedule([ [0L, 5L], [10L, 20L] ], [ [0L, 2L], [12L, 20L] ], 2L)
    }
}
