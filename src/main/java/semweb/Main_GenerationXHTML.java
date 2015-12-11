package semweb;

import java.rmi.RemoteException;

public class Main_GenerationXHTML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String url_Albert="http://dbpedia.org/resource/Albert_Einstein";
			GenerationXHTML test = new GenerationXHTML() ;
			test.gerneration(url_Albert);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

				
				

	}

}
