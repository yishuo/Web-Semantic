package semweb;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import javax.security.auth.Subject;

import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class ClassifierImpl  implements Classifier  {

	protected ClassifierImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<String> retrieveTypes(String iri) {
		// TODO Auto-generated method stub
		JenaRdfaReader.inject();
		Model m = null;
		Collection <String> list_resource = new HashSet <String>();
		RDFNode r;				
		try {
			m = ModelFactory.createDefaultModel();
			m.read(iri,"TTL");			
		} catch ( org.apache.jena.riot.RiotException e) {
			// TODO Auto-generated catch block			
			try {
				m = ModelFactory.createDefaultModel();
				m.read(iri,"RDF/XML");				
				} catch ( org.apache.jena.riot.RiotException a) {
					// TODO Auto-generated catch block
					try {
						m = ModelFactory.createDefaultModel();
						m.read(iri,"RDFA");	
						} catch ( org.apache.jena.riot.RiotException c) {
							c.printStackTrace();							
						}
					}
			}finally{
//				m.write(System.out);
			}
		Resource subject = m.createResource(iri);							
		StmtIterator iter = m.listStatements(subject,RDF.type,(RDFNode)null );
		if (iter.hasNext()) {
			System.out.println("The model contains those rdf:type :");
//			int n=0;
			 while (iter.hasNext()) {
				 r = iter.nextStatement().getObject();
//				 System.out.println("  " + r);
				 String resource =r.toString();
				 list_resource.add(resource);
//				 n++;
//				 System.out.println(n);
			 }
			 
		}else {
            System.out.println("No rdf:type were found in the model");
        }
		
		for(String i:list_resource ){
			System.out.println(i);			
		}
		return list_resource;		 
	}

	public Collection<String> retrieveSuperClasses(String iri) {
		// TODO Auto-generated method stub
		JenaRdfaReader.inject();
		Model m = null;
		Collection <String> list_resource = new HashSet <String>();
		Collection <String> list_subClassOf = new HashSet <String>();		
		RDFNode r;				
		try {
			m = ModelFactory.createDefaultModel();
			m.read(iri,"TTL");			
		} catch ( org.apache.jena.riot.RiotException e) {
			// TODO Auto-generated catch block			
			try {
				m = ModelFactory.createDefaultModel();
				m.read(iri,"RDF/XML");				
				} catch ( org.apache.jena.riot.RiotException a) {
					// TODO Auto-generated catch block
					try {
						m = ModelFactory.createDefaultModel();
						m.read(iri,"RDFA");	
						} catch ( org.apache.jena.riot.RiotException c) {
							c.printStackTrace();							
						}
					}
			}finally{
//				m.write(System.out);
			}
		Resource subject = m.createResource(iri);				
		StmtIterator iter = m.listStatements(subject ,RDFS.subClassOf,(RDFNode)null );
		if (iter.hasNext()) {
			System.out.println("The model contains those rdfs:subClassOf :");
			 while (iter.hasNext()) {				 
				 r = iter.nextStatement().getObject();
				 String resource =r.toString();
				 System.out.println(resource);
				 list_resource.add(resource);
			 }
			 
		}else {
            System.out.println("No rdfs:subClassOf were found in the model");
        }
		list_subClassOf.addAll(list_resource);
		for(String i:list_resource ){
			System.out.println("_________________________");
			System.out.println("I will do the next research with iri");
			System.out.println(i);
			System.out.println("result:");
			list_subClassOf.addAll(this.retrieveSuperClasses(i));			
		}
		return list_subClassOf;
	}
		


	public Collection<String> getAllTypes(String url) {
		// TODO Auto-generated method stub
		Collection <String> list_type = new HashSet <String>();
		Collection <String> list_alltype = new HashSet <String>();		
		list_type=this.retrieveTypes(url);
		list_alltype.addAll(list_type);		
		for(String iri : list_type ){
			list_alltype.addAll(this.retrieveSuperClasses(iri));
		}
		return list_alltype;
	}

	public boolean isOfType(String entityIRI, String classIRI) {
		// TODO Auto-generated method stub
		Collection <String> list_alltypes = new HashSet <String>();	
		Boolean result=false;
		if((entityIRI==null)||(classIRI==null)){
			return result;			
		}else{
			list_alltypes=this.getAllTypes(entityIRI);
			for(String i : list_alltypes){
				if(i.equals(classIRI)){
					result=true;
					break;
					}			
				}
			return result;
			}
		}
	}
