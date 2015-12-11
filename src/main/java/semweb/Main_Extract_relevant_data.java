package semweb;

import java.rmi.RemoteException;

import com.hp.hpl.jena.rdf.model.RDFNode;

public class Main_Extract_relevant_data {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url_w3cstaff ="http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html";
		String url_Albert="http://dbpedia.org/resource/Albert_Einstein";
		String Saint_Etienne="http://dbpedia.org/page/Saint-%C3%89tienne";
//		String url_Albert="http://en.wikipedia.org/wiki/Albert_Einstein";

		PersonExtractor test;
		try {
			test= new PersonExtractor();
			/******************************************************************
			 * test of function RDFNode primaryTopic(String iri):
			 */
//			test.primaryTopic(url_Albert);
			/******************************************************************
			 * test of function RDFNode showPersonData(String iri):
			 */
			test.showPersonData(url_Albert);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
