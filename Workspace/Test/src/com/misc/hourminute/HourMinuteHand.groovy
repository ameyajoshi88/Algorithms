package com.misc.hourminute

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author Ameya
 *
 * This program takes as input a string which represents a time value and returns
 * the angle between the hour and minute hands of an analog clock at that time
 *
 * e.g. 12:30 -> 165
 *
 * Assumption: Time is provided in 12hr format, 12:00 is the origin/reference
 * @see <a href="http://www.mkyong.com/regular-expressions/how-to-validate-time-in-12-hours-format-with-regular-expression/">12 hour time regex</a>
 */
class HourMinuteHand {

    private static final float HOUR_HAND_RATE = 0.5f // degrees per min
    private static final float MINUTE_HAND_RATE = 6f // degrees per min
    private static final Pattern PATTERN_VALID_TIME = ~/(1[012]|[1-9]):([0-5][0-9])/

    static float angle(String time) {
        Matcher matcher = (time =~ PATTERN_VALID_TIME)
        if (!matcher.matches()) {
            return -1 // 'Invalid input'
        }
        int hours = matcher.group(1) as int
        int minutes = matcher.group(2) as int
        return Math.abs(hourHandAngle(hours, minutes) - minuteHandAngle(minutes))
    }

    /**
     * @param hours
     * @param minutes
     * @return angle made by the hour hand w.r.t origin
     */
    private static float hourHandAngle(int hours, int minutes) {
        int minutesElapsed = (hours % 12) * 60 + minutes
        return minutesElapsed * HOUR_HAND_RATE
    }

    /**
     * @param minutes
     * @return angle made by the minute hand w.r.t origin
     */
    private static float minuteHandAngle(int minutes) {
        return minutes * MINUTE_HAND_RATE
    }
}

class HourMinuteHandTester {
    static void main(String[] args) {
        assert -1f == HourMinuteHand.angle('100:20')
        assert -1f == HourMinuteHand.angle('05:30')
        assert -1f == HourMinuteHand.angle('7:70')
        assert 165f == HourMinuteHand.angle('12:30')
        assert 75f == HourMinuteHand.angle('3:30')
        assert 242.5f == HourMinuteHand.angle('9:05')
    }
}
