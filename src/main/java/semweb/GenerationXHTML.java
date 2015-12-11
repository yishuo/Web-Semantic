package semweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class GenerationXHTML {
	protected GenerationXHTML() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void gerneration(String iri){
		PersonExtractor persponExtrator;
		try {
			persponExtrator = new PersonExtractor();
			persponExtrator.showPersonData(iri);			
			String file_html=
					"<!DOCTYPE html>\r"
					+ "<html>\r"
					+"<head>\r"
					+ "<title>a new rdfa page</title>\r"
					+ "</head>\r"
					+ "<body>\r"
					+ "<div vocab=\"http://xmlns.com/foaf/0.1/\"typeof=\"Person\">\r"
					+ "<div Resource=result typeof=\"Person\">\r"
					+ "<div>\r"
					+ "<img property=\"image\" src=\""+persponExtrator.getimage()+"\" height=400>\r"
					+ "</div>\r"
					+ "<div>\r"
					+ "Name:<span property=\"name\">"+persponExtrator.getname()+"</span> \r"
					+ "</div>\r"
					+ "<div>\r"
					+ "Birthday: <span property=\"birthDate\">"+persponExtrator.getbirthday()+"</span>\r"
					+ "</div>\r"
					+ "<div>\r"
					+ "Deathday: <span property=\"deathDate\" >"+persponExtrator.getdeathday()+"</span>\r"
					+ "</div>\r"
					+ "</div>\r"
					+ "</div>\r"
					+ "</body>\r"
					+ "</html>\r";
			System.out.println(file_html);
			File fileName = new File("./test.html");
			PrintWriter outFile = null;
			try {
				outFile = new PrintWriter(fileName);
				outFile.write(file_html);
				outFile.flush();
				outFile.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}

	

}
