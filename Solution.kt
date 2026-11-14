
import kotlin.math.pow

class Solution {

    private companion object {
        const val NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS = -1
        val RANGE_OF_DAYS = intArrayOf(1, (10.0).pow(9.0).toInt())
    }

    fun minDays(bloomDay: IntArray, targetNumberOfBouquets: Int, numberOfAdjacentFlowersToMakeBouquet: Int): Int {
        if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet > bloomDay.size) {
            return NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS
        }
        return findMinDaysToMakeTargetNumberOfBouquets(bloomDay, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet)
    }

    private fun findMinDaysToMakeTargetNumberOfBouquets(bloomDay: IntArray, targetNumberOfBouquets: Int, numberOfAdjacentFlowersToMakeBouquet: Int): Int {
        var minDays = Int.MAX_VALUE
        var left = RANGE_OF_DAYS[0]
        var right = RANGE_OF_DAYS[1]

        while (left <= right) {
            val days = left + (right - left) / 2
            if (isPossibleToMakeTargetNumberOfBouquets(bloomDay, days, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet)) {
                minDays = days
                right = days - 1
            } else {
                left = days + 1
            }
        }

        return if (minDays != Int.MAX_VALUE) minDays else NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS
    }

    private fun isPossibleToMakeTargetNumberOfBouquets(bloomDay: IntArray, days: Int, targetNumberOfBouquets: Int, numberOfAdjacentFlowersToMakeBouquet: Int): Boolean {
        var countAdjacentFlowers = 0
        var targetNumberOfBouquets = targetNumberOfBouquets

        for (i in bloomDay.indices) {
            if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet - countAdjacentFlowers > bloomDay.size - i) {
                return false;
            }
            if (bloomDay[i] > days) {
                countAdjacentFlowers = 0
                continue
            }
            ++countAdjacentFlowers
            if (countAdjacentFlowers == numberOfAdjacentFlowersToMakeBouquet) {
                --targetNumberOfBouquets
                countAdjacentFlowers = 0
            }
            if (targetNumberOfBouquets == 0){
                break
            }
        }
        return targetNumberOfBouquets == 0
    }
}
