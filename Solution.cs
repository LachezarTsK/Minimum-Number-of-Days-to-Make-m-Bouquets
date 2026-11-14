
using System;

public class Solution
{

    private static readonly int NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS = -1;
    private static readonly int[] RANGE_OF_DAYS = { 1, (int)Math.Pow(10, 9) };
    public int MinDays(int[] bloomDay, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet)
    {
        if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet > bloomDay.Length)
        {
            return NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
        }
        return FindMinDaysToMakeTargetNumberOfBouquets(bloomDay, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet);
    }

    private int FindMinDaysToMakeTargetNumberOfBouquets(int[] bloomDay, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet)
    {
        int minDays = int.MaxValue;
        int left = RANGE_OF_DAYS[0];
        int right = RANGE_OF_DAYS[1];

        while (left <= right)
        {
            int days = left + (right - left) / 2;
            if (IsPossibleToMakeTargetNumberOfBouquets(bloomDay, days, targetNumberOfBouquets, numberOfAdjacentFlowersToMakeBouquet))
            {
                minDays = days;
                right = days - 1;
            }
            else
            {
                left = days + 1;
            }
        }

        return minDays != int.MaxValue ? minDays : NOT_POSSIBLE_TO_MAKE_TARGET_NUMBER_OF_BOUQUETS;
    }

    private bool IsPossibleToMakeTargetNumberOfBouquets(int[] bloomDay, int days, int targetNumberOfBouquets, int numberOfAdjacentFlowersToMakeBouquet)
    {
        int countAdjacentFlowers = 0;
        for (int i = 0; i < bloomDay.Length && targetNumberOfBouquets > 0; ++i)
        {
            if (targetNumberOfBouquets * numberOfAdjacentFlowersToMakeBouquet - countAdjacentFlowers > bloomDay.Length - i) 
            {
                return false;
            }
            if (bloomDay[i] > days)
            {
                countAdjacentFlowers = 0;
                continue;
            }
            ++countAdjacentFlowers;
            if (countAdjacentFlowers == numberOfAdjacentFlowersToMakeBouquet)
            {
                --targetNumberOfBouquets;
                countAdjacentFlowers = 0;
            }
        }
        return targetNumberOfBouquets == 0;
    }
}
