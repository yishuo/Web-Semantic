
package semweb;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.VCARD;


public class AlgoRDF {
	
	static final String inputFileName = "../person.ttl";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// create an empty model
		Model m = ModelFactory.createDefaultModel();
		
		// read the TTL file
		m.read(inputFileName);
		
		// select all the resources with a VCARD.FN property
        ResIterator iter = m.listResourcesWithProperty(RDF.type);
        if (iter.hasNext()) {
            System.out.println("The model contains those rdf:type :");
            while (iter.hasNext()) {
                System.out.println("  " + iter.nextResource()
                                              .getRequiredProperty(RDF.type) );
            }
        } else {
            System.out.println("No rdf:type were found in the model");
        } 
		
		Map<String, String> prefixMap = m.getNsPrefixMap();
		System.out.println(prefixMap.values());
		// write it to standard out
		m.write(System.out);

	}

}
