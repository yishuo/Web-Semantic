package semweb;

import java.rmi.RemoteException;

import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class Main_Navigating_the_model {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JenaRdfaReader.inject();
		PersonDataImpl test;
		Model m = ModelFactory.createDefaultModel();
		String url_shadi="http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi";
		
		String url_Albert="http://dbpedia.org/resource/Albert_Einstein";
//		String url="http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html";
		String url = "http://dbpedia.org/resource/Albert_Einstein";
		RDFNode r = m.createResource(url_Albert);
	

		try {
			m = ModelFactory.createDefaultModel();
			m.read(url,"TTL");			
		} catch ( org.apache.jena.riot.RiotException e) {
			// TODO Auto-generated catch block			
			try {
				m = ModelFactory.createDefaultModel();
				m.read(url,"RDF/XML");				
				} catch ( org.apache.jena.riot.RiotException a) {
					// TODO Auto-generated catch block
					try {
						m = ModelFactory.createDefaultModel();
						m.read(url,"RDFA");	
						} catch ( org.apache.jena.riot.RiotException c) {
							c.printStackTrace();							
						}
					}
			}finally{
//				m.write(System.out);
			}
		
		try {
			test = new PersonDataImpl();
				
			/******************************************************************
			 * test of function name(RDFNode person, Model model):
			 */
			//test.name(r, m);
			
			/******************************************************************
			 * test of function birth(RDFNode person, Model model):
			 */
			//test.birth(r, m);
			
			/******************************************************************
			 * test of function death(RDFNode person, Model model):
			 */
			//test.death(r, m);
			
			/******************************************************************
			 * test of function picture(RDFNode person, Model model):
			 */
			//test.picture(r, m);
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}

}
