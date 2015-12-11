package semweb;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDFS;

public class GenRDF {

	public static void main(String[] args) {
		JenaRdfaReader.inject();

		Model m = ModelFactory.createDefaultModel();		
		String w3cstaff= "http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html";
		String ex = "http://example.com/";
		String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
		String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
		String xsd = "http://www.w3.org/2001/XMLSchema#";
		String v = "http://www.emse.fr/~zimmermann/Teaching/SemWeb/vocab.ttl#";
		m.read(w3cstaff,"RDFA");
		m.write(System.out);
		
		m.setNsPrefix("ex", ex);
		m.setNsPrefix("rdfs", rdfs);
		m.setNsPrefix("geo", geo);
		m.setNsPrefix("xsd", xsd);
		m.setNsPrefix("v", v);
		
		//subject property resource
		Resource spatialThing = m.createResource(geo + "SpatialThing");
		Resource r;

		Property p_lat = m.createProperty(geo + "lat");
		Property p_long = m.createProperty(geo + "long");
		
		try (BufferedReader br = new BufferedReader(new FileReader("../export-TER-GTFS-LAST/stops.txt"))) {
		    String line;
		    String [] columns;
		    line = br.readLine(); //Skip the first line
		    
		    while ((line = br.readLine()) != null) {
		    	columns = line.split(",");
		      	
		      	r = m.createResource(ex + columns[0].replace(" ", "_"), spatialThing);
		      	
		      	r.addProperty(p_long, columns[4], XSDDatatype.XSDdecimal);
		      	
		      	r.addProperty(p_lat, columns[3], XSDDatatype.XSDdecimal);
		      	
		      	r.addProperty(RDFS.label, columns[1], XSDDatatype.XSDstring);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter file_out = new PrintWriter("../genRDF.ttl");
			m.write(file_out, "Turtle");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//m.write(System.out, "Turtle");
	}

}
