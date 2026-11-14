
public class Solution {

    private static final int NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS = -1;
    private static final int[] RANGE_OF_DAYS = {1, (int) Math.pow(10, 9)};

    public int minDays(int[] bloomDay, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet) {
        if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet > bloomDay.length) {
            return NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
        }
        return findMinDaysToMakeTargetNumberOfBouquets(bloomDay, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet);
    }

    private int findMinDaysToMakeTargetNumberOfBouquets(int[] bloomDay, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet) {
        int minDays = Integer.MAX_VALUE;
        int left = RANGE_OF_DAYS[0];
        int right = RANGE_OF_DAYS[1];

        while (left <= right) {
            int days = left + (right - left) / 2;
            if (isPossibleToMakeTargetNumberOfBouquets(bloomDay, days, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet)) {
                minDays = days;
                right = days - 1;
            } else {
                left = days + 1;
            }
        }

        return minDays != Integer.MAX_VALUE ? minDays : NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
    }

    private boolean isPossibleToMakeTargetNumberOfBouquets(int[] bloomDay, int days, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet) {
        int countAdjacentFlowers = 0;
        for (int i = 0; i < bloomDay.length && targetNumberOfBouquets > 0; ++i) {
            if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet - countAdjacentFlowers > bloomDay.length - i) {
                return false;
            }
            if (bloomDay[i] > days) {
                countAdjacentFlowers = 0;
                continue;
            }
            ++countAdjacentFlowers;
            if (countAdjacentFlowers == numberOfAdjacentFlowersToMakeBouquet) {
                --targetNumberOfBouquets;
                countAdjacentFlowers = 0;
            }
        }
        return targetNumberOfBouquets == 0;
    }
}
