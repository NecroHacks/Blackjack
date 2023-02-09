import java.util.*;
import java.util.concurrent.TimeUnit;
class Colors{	
	public final String Black = "\u001B[30m";	
	public final String Reset = "\u001B[0m";	
	public final String Red = "\u001B[31m";	
	public final String Green = "\u001B[32m";	
	public final String Yellow = "\u001B[33m";	
	public final String Blue = "\u001B[34m";	
	public final String Purple = "\u001B[35m";	
	public final String Cyan = "\u001B[36m";	
	public final String White = "\u001B[37m";

	public final String[] Rainbow = {Red, Yellow, Green, Blue, Cyan, Purple};

	//Making methods in order to print fancy text, rainbow colors, random colors, etc. Unused

	/**
	 * Slowly prints out text in a rainbow order.
	 * @param text String text to print out in rainbow
	 */
	public void slowRainbowPrint(String text){
		Random rand = new Random();
    for (int i = 0; i<text.length(); i++) {
			int clr = i % 6;
      char c = text.charAt(i);
			if(c ==(' ')){
				System.out.print(c);
			} else {
      	System.out.print(Rainbow[clr] + c);
			}
			try {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(30)+20);
      } catch (Exception e) {}
    }
		System.out.println(Reset);
	}

	/**
	 * Quickly prints out text in a rainbow order.
	 * @param text String text to print out in rainbow
	 */
	public void fastRainbowPrint(String text){
		Random rand = new Random();
    for (int i = 0; i<text.length(); i++) {
			int clr = i % 6;
      char c = text.charAt(i);
			if(c ==(' ')){
				System.out.print(c);
			} else {
      	System.out.print(Rainbow[clr] + c);
			}
			try {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(30));
      } catch (Exception e) {}
    }
		System.out.println(Reset);
	}
	/**
	 * Very quickly prints out text in a rainbow order.
	 * @param text String text to print out in rainbow
	 */
	public void instantRainbowPrint(String text){
		Random rand = new Random();
    for (int i = 0; i<text.length(); i++) {
			int clr = i % 6;
      char c = text.charAt(i);
			if(c ==(' ')){
				System.out.print(c);
			} else {
      	System.out.print(Rainbow[clr] + c);
			}
			try {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2));
      } catch (Exception e) {}
    }
		System.out.println(Reset);
	}
	/**
	 * Slowly prints out text in a random order.
	 * @param text String text to print out in random colors
	 */
	public void slowRandomPrint(String text){
		Random rand = new Random();
    for (int i = 0; i<text.length(); i++) {
			int clr = rand.nextInt(Rainbow.length);
      char c = text.charAt(i);
			if(c ==(' ')){
				System.out.print(c);
			} else {
      	System.out.print(Rainbow[clr] + c);
			}
			try {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(30)+20);
      } catch (Exception e) {}
    }
		System.out.println(Reset);
	}	
}