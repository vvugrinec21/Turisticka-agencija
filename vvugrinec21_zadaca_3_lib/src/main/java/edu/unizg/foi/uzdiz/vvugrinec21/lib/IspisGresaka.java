package edu.unizg.foi.uzdiz.vvugrinec21.lib;

import java.util.List;
 
 class IspisGresaka {

	public void ispisi(int redak, String linija, List<String> greske) {
		for (String g : greske) {
			System.out.println("Greška u retku " + redak + ": " + linija);
			System.out.println("  → " + g);
		}
	}
	
}
