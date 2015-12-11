/**
 * In this file, you can test all the functions of Classifier:
 * 
 * retrieveTypes(String iri);
 * retrieveSuperClasses(String iri)
 * getAllTypes(String url)
 * isOfType(String entityIRI, String classIRI)
 */

package semweb;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;

import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Main_Extract_relevant_classes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url_shadi="http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi";
		String url_Scientist ="http://www.emse.fr/~zimmermann/Teaching/SemWeb/vocab.ttl#Scientist";
		String url_madamic="http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#madamic";
		String url_Human="http://www.emse.fr/~zimmermann/Teaching/SemWeb/other.ttl#Human";
		ClassifierImpl a;
		try {
			Collection <String> list_subClassOf = new HashSet <String>();
			Collection <String> list_alltypes = new HashSet <String>();
			a = new ClassifierImpl();
			
            /*******************************************************************************			
			 * test of function retrieveTypes:
			 */			
           //a.retrieveTypes(url_shadi);
			
            /*******************************************************************************			
			 * test of function retrieveSuperClasses:
			 */			
            /*			
			list_subClassOf = a.retrieveSuperClasses(url_Scientist);
			System.out.println("**************************************");
			System.out.println("All the subClasses Of the subject are:");
			System.out.println("**************************************");
			for(String i : list_subClassOf)
				System.out.println(i);
            
			*/
            /*******************************************************************************
			 * test of function getALLTypes:
			 */
            String url= "http://dbpedia.org/resource/Albert_Einstein";		
//			list_alltypes = a.getAllTypes(url_shadi);
			list_alltypes = a.getAllTypes(url);
			System.out.println("**************************************");
			System.out.println("All the types Of the subject are:");
			System.out.println("**************************************");
			for(String i : list_alltypes)
				System.out.println(i);
            
			
            /*******************************************************************************
			 * test of function isOfType:
			 */
            /*			
			if(a.isOfType(url_shadi, url_Scientist)){
				System.out.println("**************************************");
				System.out.println("shadi have a type of scientist");
				System.out.println("**************************************");
			}else{
				System.out.println("**************************************");
				System.out.println("shadi don't have a type of scientist");
				System.out.println("**************************************");
			}

			if(a.isOfType(url_madamic, url_Scientist)){
				System.out.println("**************************************");
				System.out.println("madamic have a type of scientist");
				System.out.println("**************************************");
			}else{
				System.out.println("**************************************");
				System.out.println("madamic don't have a type of scientist");
				System.out.println("**************************************");
			}
			if(a.isOfType(url_shadi, url_Human)){
				System.out.println("**************************************");
				System.out.println("shadi have a type of Human");
				System.out.println("**************************************");
			}else{
				System.out.println("**************************************");
				System.out.println("shadi don't have a type of Human");
				System.out.println("**************************************");
			}

			if(a.isOfType(url_madamic, url_Human)){
				System.out.println("**************************************");
				System.out.println("madamic have a type of Human");
				System.out.println("**************************************");
			}else{
				System.out.println("**************************************");
				System.out.println("madamic don't have a type of Human");
				System.out.println("**************************************");
			}
            */

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		}

}

	
