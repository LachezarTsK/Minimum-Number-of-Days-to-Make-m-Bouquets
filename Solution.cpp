
#include <span>
#include <limits>
#include <vector>
using namespace std;

class Solution {

    static const int NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS = -1;
    inline static const array<int, 2> RANGE_OF_DAYS{ 1, static_cast<int>(pow(10, 9)) };

public:
    int minDays(vector<int>& bloomDay, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet) {
        if (static_cast<long long>(targetNumberOfBouquets) * numberOfAdjacentFlowersToMakeBouquet > bloomDay.size()) {
            return NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
        }
        return findMinDaysToMakeTargetNumberOfBouquets(bloomDay, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet);
    }

private:
    int findMinDaysToMakeTargetNumberOfBouquets(span<const int> bloomDay, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet) {
        int minDays = numeric_limits<int>::max();
        int left = RANGE_OF_DAYS[0];
        int right = RANGE_OF_DAYS[1];

        while (left <= right) {
            int days = left + (right - left) / 2;
            if (isPossibleToMakeTargetNumberOfBouquets(bloomDay, days, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet)) {
                minDays = days;
                right = days - 1;
            }
            else {
                left = days + 1;
            }
        }

        return minDays != numeric_limits<int>::max() ? minDays : NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
    }

    bool isPossibleToMakeTargetNumberOfBouquets(span<const int> bloomDay, int days, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet) {
        int countAdjacentFlowers = 0;
        for (int i = 0; i < bloomDay.size() && targetNumberOfBouquets > 0; ++i) {
            if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet - countAdjacentFlowers > bloomDay.size() - i) {
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
};
