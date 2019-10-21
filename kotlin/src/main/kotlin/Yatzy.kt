class Yatzy(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int) {

    private var dice = listOf(d1,d2,d3,d4,d5)

	private fun sumOf(side :Int): Int = dice.filter { it == side }.sum()

	fun fours(): Int = sumOf(4)

    fun fives(): Int = sumOf(5)

    fun sixes(): Int = sumOf(6)


	private fun pair() {

	}

    companion object {

        fun chance(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            var total = 0
            total += d1
            total += d2
            total += d3
            total += d4
            total += d5
            return total
        }

        fun yatzy(vararg dice: Int): Int {
            val counts = IntArray(6)
            for (die in dice)
                counts[die - 1]++
            for (i in 0..5)
                if (counts[i] == 5)
                    return 50
            return 0
        }

        fun ones(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int = Yatzy(d1,d2, d3, d4, d5).sumOf(1)

        fun twos(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int = Yatzy(d1,d2, d3, d4, d5).sumOf(2)

        fun threes(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int = Yatzy(d1,d2, d3, d4, d5).sumOf(3)

        fun score_pair(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            //Yatzy(d1, d2, d3, d4, d5).pair()
			return listOf(d1, d2, d3, d4, d5)
				.groupBy { it }
				.mapValues { it.value.size }
				.toList()
				.sortedByDescending { it.first }
				.filter { it.second >= 2 }
				.map{ it.first * 2}
				.firstOrNull()?:0

        }

		fun two_pair(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val counts = IntArray(6)
            counts[d1 - 1]++
            counts[d2 - 1]++
            counts[d3 - 1]++
            counts[d4 - 1]++
            counts[d5 - 1]++
            var n = 0
            var score = 0
            var i = 0
            while (i < 6) {
                if (counts[6 - i - 1] >= 2) {
                    n++
                    score += 6 - i
                }
                i += 1
            }
            return if (n == 2)
                score * 2
            else
                0
        }

		fun four_of_a_kind(_1: Int, _2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[_1 - 1]++
            tallies[_2 - 1]++
            tallies[d3 - 1]++
            tallies[d4 - 1]++
            tallies[d5 - 1]++
            for (i in 0..5)
                if (tallies[i] >= 4)
                    return (i + 1) * 4
            return 0
        }

        fun three_of_a_kind(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val t: IntArray = IntArray(6)
            t[d1 - 1]++
            t[d2 - 1]++
            t[d3 - 1]++
            t[d4 - 1]++
            t[d5 - 1]++
            for (i in 0..5)
                if (t[i] >= 3)
                    return (i + 1) * 3
            return 0
        }

        fun smallStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1
            return if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
            ) 15 else 0
        }

        fun largeStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1
            return if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1
            ) 20 else 0
        }

        fun fullHouse(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray
            var _2 = false
            var i: Int
            var _2_at = 0
            var _3 = false
            var _3_at = 0

            tallies = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1

            i = 0
            while (i != 6) {
                if (tallies[i] == 2) {
                    _2 = true
                    _2_at = i + 1
                }
                i += 1
            }

            i = 0
            while (i != 6) {
                if (tallies[i] == 3) {
                    _3 = true
                    _3_at = i + 1
                }
                i += 1
            }

            return if (_2 && _3)
                _2_at * 2 + _3_at * 3
            else
                0
        }
    }
}


