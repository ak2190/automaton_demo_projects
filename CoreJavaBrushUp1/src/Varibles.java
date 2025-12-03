
public class Varibles {

	public static void main(String[] args) {
		
		int num =8;
		String name ="arun";
		boolean human = true;
		double pie = 3.14;
		int[] arr = new int[5];
		
		arr[0]=1;
		arr[1]=2;
		arr[2]=4;
		arr[3]=5;
		arr[4]=6;
		
		int[] arr2 = {1,2,3,4,5,6};

		for(int i=0;i<arr2.length;i++) {
			if(arr2[i]%2 == 0) {
			System.out.println(arr2[i]+" is an even number.");
			}
			else {
				System.out.println(arr2[i]+" is not an even number.");
			}
		}
		
		String[] names = {"arun","asha","navika","hachi"};
		
		for(String s:names) {
			System.out.println(s);
		}
		
		

	}

}
