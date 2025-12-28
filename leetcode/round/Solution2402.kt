package leetcode.round

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-27
 */
class Solution2402 {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        val idleRooms: PriorityQueue<MeetingRoom> = PriorityQueue(
            compareBy<MeetingRoom> { it.number }
        )

        for (i in 0..<n) {
            idleRooms.add(MeetingRoom(number = i))
        }

        val usedRooms: PriorityQueue<MeetingRoom> = PriorityQueue(
            compareBy<MeetingRoom> { it.endTime }
                .thenBy { it.number }
        )

        meetings.sortBy { it[0] }

        var maxCount = 0
        var bestRoom = 0

        for (meeting in meetings) {
            // 挪动空闲会议室
            while (usedRooms.isNotEmpty()) {
                val room = usedRooms.poll()
                if (room.endTime <= meeting[0]) {
                    idleRooms.offer(room)
                } else {
                    usedRooms.offer(room)
                    break
                }
            }

            // 从空闲会议室中选一个
            if (idleRooms.isNotEmpty()) {
                val room = idleRooms.poll()
                room.endTime = meeting[1].toLong()
                room.count += 1
                if (room.count >= maxCount) {
                    if (room.count == maxCount) {
                        bestRoom = min(bestRoom, room.number)
                    } else {
                        maxCount = room.count
                        bestRoom = room.number
                    }
                }
                usedRooms.offer(room)
            } else {
                // 没有空闲会议室，从使用的会议室中选一个最早结束的
                val room = usedRooms.poll()
                room.endTime = room.endTime + meeting[1] - meeting[0]
                room.count += 1
                if (room.count >= maxCount) {
                    if (room.count == maxCount) {
                        bestRoom = min(bestRoom, room.number)
                    } else {
                        maxCount = room.count
                        bestRoom = room.number
                    }
                }
                usedRooms.offer(room)
            }
        }

        return bestRoom
    }

    data class MeetingRoom(
        val number: Int,
        var count: Int = 0,
        var endTime: Long = 0,
    )
}

fun main() {
    Solution2402().mostBooked(
        4,
        arrayOf(intArrayOf(18, 19), intArrayOf(3, 12), intArrayOf(17, 19), intArrayOf(2, 13), intArrayOf(7, 10))
    )
        .let(::println)
}