package myutilities.helper;

import java.util.Arrays;

public class Learner {

public double[] properties = new double[81];
	
	public void CalculatePercentage(Gambar g){
		int indeks = 0;
		for (int i = 0; i < g.chaincodes.length; i++) {

			
			
			int[] size = new int[9];
			Arrays.fill(size,0);
			for (int j = 0; j < g.chaincodes[i].length(); j++) {
				size[g.chaincodes[i].charAt(j)-'0']++;
			}
			
			
			
			int total = g.chaincodes[i].length();
			double[] percentage = new double[9];
			if(total>0){
				// calculate percentage
				for (int j = 0; j < 9; j++) {
					percentage[j] = (double) ((double)size[j] / (double) total);
				}
			}else{
				Arrays.fill(percentage,0);
			}
			
			
			// mindahin percentage ke global properties
			for (int j = 0; j < percentage.length; j++) {
				properties[indeks] = percentage[j];
				indeks++;
			}
		}
	}
	
	public void printproperties(){
		for (int i = 0; i < properties.length; i++) {
			System.out.print((int) (properties[i]*100)+",");
		}
	}

}
