package 领扣.刷题

import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-23
 */
class Solution67 {
    fun addBinary(a: String, b: String): String {
        var leftP = 0
        var carrier = 0

        var result = ""
        while (leftP < a.length || leftP < b.length) {
            if (leftP < a.length && leftP < b.length) {
                val tmp = a[a.length - leftP - 1].code - 48 + b[b.length - leftP - 1].code - 48 + carrier
                when (tmp) {
                    0 -> {
                        result = "0$result"
                        carrier = 0
                    }

                    1 -> {
                        result = "1$result"
                        carrier = 0
                    }

                    2 -> {
                        carrier = 1
                        result = "0$result"
                    }

                    else -> {
                        result = "1$result"
                        carrier = 1
                    }
                }
            } else if (leftP < a.length) {
                val tmp = a[a.length - leftP - 1].code - 48 + carrier
                when (tmp) {
                    0 -> {
                        result = "0$result"
                        carrier = 0
                    }

                    1 -> {
                        result = "1$result"
                        carrier = 0
                    }

                    else -> {
                        result = "0$result"
                        carrier = 1
                    }
                }
            } else {
                val tmp = b[b.length - leftP - 1].code - 48 + carrier
                when (tmp) {
                    0 -> {
                        result = "0$result"
                        carrier = 0
                    }

                    1 -> {
                        result = "1$result"
                        carrier = 0
                    }

                    else -> {
                        result = "0$result"
                        carrier = 1
                    }
                }
            }
            leftP++
        }
        if (carrier > 0) result = "1$result"
        return result
    }
}

fun main() {
    print(Solution67().addBinary("11", "1001"))
}