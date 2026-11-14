
function minDays(bloomDay: number[], targetNumberOfBouquets: number, numberOfAdjacentFlowersToMakeBouquet: number): number {
    if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet > bloomDay.length) {
        return Util.NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
    }
    return findMinDaysToMakeTargetNumberOfBouquets(bloomDay, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet);
};

function findMinDaysToMakeTargetNumberOfBouquets(bloomDay: number[], targetNumberOfBouquets: number, numberOfAdjacentFlowersToMakeBouquet: number): number {
    let minDays = Number.MAX_SAFE_INTEGER;
    let left = Util.RNAGE_OF_DAYS[0];
    let right = Util.RNAGE_OF_DAYS[1];

    while (left <= right) {
        const days = left + Math.floor((right - left) / 2);
        if (isPossibleToMakeTargetNumberOfBouquets(bloomDay, days, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet)) {
            minDays = days;
            right = days - 1;
        } else {
            left = days + 1;
        }
    }

    return minDays !== Number.MAX_SAFE_INTEGER ? minDays : Util.NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
}

function isPossibleToMakeTargetNumberOfBouquets(bloomDay: number[], days: number, targetNumberOfBouquets: number, numberOfAdjacentFlowersToMakeBouquet: number) {
    let countAdjacentFlowers = 0;
    for (let i = 0; i < bloomDay.length && targetNumberOfBouquets > 0; ++i) {
        if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet - countAdjacentFlowers > bloomDay.length - i) {
            return false;
        }
        if (bloomDay[i] > days) {
            countAdjacentFlowers = 0;
            continue;
        }
        ++countAdjacentFlowers;
        if (countAdjacentFlowers === numberOfAdjacentFlowersToMakeBouquet) {
            --targetNumberOfBouquets;
            countAdjacentFlowers = 0;
        }
    }
    return targetNumberOfBouquets === 0;
}

class Util {
    static NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS = -1;
    static RNAGE_OF_DAYS = [1, Math.pow(10, 9)];
}
