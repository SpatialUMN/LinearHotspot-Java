
public class StatisticMeasure {
	
	static double densityRatio(double count, double weight) {

		if (NetworkDataset.numTotalActivities <= count) {
			return Double.MAX_VALUE;
		}

		else {
			return count * (NetworkDataset.totalWeight-weight) / (weight * ((double)NetworkDataset.numTotalActivities - count));
		}
	}
	
	static double logLikelihoodRatio (double count, double weight) {

		if (NetworkDataset.numTotalActivities <= count) {
			return Double.MAX_VALUE;
		}
		else {
			double B = NetworkDataset.numTotalActivities * weight / NetworkDataset.totalWeight;
			if (NetworkDataset.numTotalActivities >= B) {
				double logC = Math.log(count);
				double logB = Math.log(B);
				double logctotC = Math.log((double)(NetworkDataset.numTotalActivities-count));
				double logctotB = Math.log((double)(NetworkDataset.numTotalActivities-B));
				return count * (logC - logB) + (double)(NetworkDataset.numTotalActivities - count) * (logctotC-logctotB);
			}
			else {
				return 0;
			}
		}
	}
	public static void main(String[] args) {
		
		System.out.println(densityRatio(5, 6));

	}
}
