
package main
import "math"

const NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS = -1
var RANGE_OF_DAYS = []int{1, int(math.Pow(10.0, 9.0))}

func minDays(bloomDay []int, targetNumberOfBouquets int, numberOfAdjacentFlowersToMakeBouquet int) int {
    if targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet > len(bloomDay) {
        return NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS
    }
    return findMinDaysToMakeTargetNumberOfBouquets(bloomDay, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet)
}

func findMinDaysToMakeTargetNumberOfBouquets(bloomDay []int, targetNumberOfBouquets int, numberOfAdjacentFlowersToMakeBouquet int) int {
    minDays := math.MaxInt
    left := RANGE_OF_DAYS[0]
    right := RANGE_OF_DAYS[1]

    for left <= right {
        days := left + (right - left) / 2
        if isPossibleToMakeTargetNumberOfBouquets(bloomDay, days, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet) {
                minDays = days
            right = days - 1
        } else {
            left = days + 1
        }
    }

    if minDays != math.MaxInt {
        return minDays
    }
    return NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS
}

func isPossibleToMakeTargetNumberOfBouquets(bloomDay []int, days int, targetNumberOfBouquets int, numberOfAdjacentFlowersToMakeBouquet int) bool {
    var countAdjacentFlowers = 0

    for i := range bloomDay {
         if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet - countAdjacentFlowers > len(bloomDay) - i) {
            return false;
        }
        if bloomDay[i] > days {
            countAdjacentFlowers = 0
            continue
        }
        countAdjacentFlowers++
        if countAdjacentFlowers == numberOfAdjacentFlowersToMakeBouquet {
            targetNumberOfBouquets--
            countAdjacentFlowers = 0
        }
        if targetNumberOfBouquets == 0 {
            break
        }
    }
    return targetNumberOfBouquets == 0
}
