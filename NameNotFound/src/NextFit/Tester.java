package NextFit;

import javax.swing.SwingUtilities;

public class Tester {
	   public static void main(String[] args) {
	        Palestra palestra = new Palestra(100,100); // Passa la capacità massima di clienti alla palestra
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new PalestraGui(palestra);
	            }
	        });
	    }
}
