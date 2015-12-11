package semweb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;

import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class PersonExtractor extends  RDFExtractor  {
	String name;
	String birthday;
	String deathday;
	String img;
	String subject;
	String type;
	
	protected PersonExtractor() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	    String name=null;
		String birthday=null;
		String deathday=null;
		String img=null;
		String subject=null;
		String type =null;
		
	}

	@Override
	public RDFNode primaryTopic(String iri) {
		// TODO Auto-generated method stub
		JenaRdfaReader.inject();
		Model m = ModelFactory.createDefaultModel();
		RDFNode primaryTopic = null;
		String foaf ="http://xmlns.com/foaf/0.1/";
		m.setNsPrefix("foaf",foaf);
		
		
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
		Property predicate = m.createProperty(foaf+"primaryTopic");

		StmtIterator iter = m.listStatements(subject,predicate,(RDFNode)null );

		if (iter.hasNext()) {
			System.out.println("The model contains primaryTopic");
			 while (iter.hasNext()) {
				 primaryTopic = iter.nextStatement().getObject();
				 System.out.println(primaryTopic.toString());			
			 }
			 
		}else {
            System.out.println("No primaryTopic were found in the model");
        }
		return primaryTopic;		 
	}
	
	
	public void showPersonData(String iri){
		JenaRdfaReader.inject();
		String String_primaryTopic=null;
		Model m = ModelFactory.createDefaultModel();
		if(this.primaryTopic(iri)!=null){
			String_primaryTopic=this.primaryTopic(iri).toString();
			try {
				m = ModelFactory.createDefaultModel();
				m.read(String_primaryTopic,"TTL");			
			} catch ( org.apache.jena.riot.RiotException e) {
				// TODO Auto-generated catch block			
				try {
					m = ModelFactory.createDefaultModel();
					m.read(String_primaryTopic,"RDF/XML");				
					} catch ( org.apache.jena.riot.RiotException a) {
						// TODO Auto-generated catch block
						try {
							m = ModelFactory.createDefaultModel();
							m.read(String_primaryTopic,"RDFA");	
							} catch ( org.apache.jena.riot.RiotException c) {
								c.printStackTrace();							
							}
						}
				}finally{
//					m.write(System.out);
				}
			
		}

		RDFNode primaryTopic=this.primaryTopic(iri);
		String foaf_Person ="http://xmlns.com/foaf/0.1/Person";
		String schema_Person ="http://schema.org/Person";
		Resource property_foaf_Person= m.createResource(foaf_Person);
		Resource property_schema_Person=m.createResource(schema_Person);
		PersonDataImpl personDataImpl;
		try {
			personDataImpl = new PersonDataImpl();
			ClassifierImpl classifierImpl;
			try {
				classifierImpl = new ClassifierImpl();
				if(classifierImpl.isOfType(String_primaryTopic, foaf_Person)||classifierImpl.isOfType(String_primaryTopic, schema_Person)){
					System.out.println("your primaryTopic is a person");
					this.name=personDataImpl.name(primaryTopic,m);
					this.birthday=personDataImpl.birth(primaryTopic,m);
					this.deathday=personDataImpl.death(primaryTopic,m);
					this.img=personDataImpl.picture(primaryTopic,m);															
					
				}else{
					System.out.println("your primaryTopic isn't a person");
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
//							m.write(System.out);
						}
									
					StmtIterator iter_foaf_person = m.listStatements(null,RDF.type,property_foaf_Person);
					StmtIterator iter_schema_person = m.listStatements(null,RDF.type,property_schema_Person);
					if (iter_foaf_person.hasNext()) {
						System.out.println("***********************************************************");						
						System.out.println("We have found the person from your url with foaf:person :");
						System.out.println("***********************************************************");						
						 while (iter_foaf_person.hasNext()) {
							 primaryTopic = iter_foaf_person.nextStatement().getSubject();
							 this.name=personDataImpl.name(primaryTopic,m);
							 this.birthday=personDataImpl.birth(primaryTopic,m);
							 this.deathday=personDataImpl.death(primaryTopic,m);
							 this.img=personDataImpl.picture(primaryTopic,m);		
						 }
					}
					if (iter_schema_person.hasNext()) {
						System.out.println("***********************************************************");						
						System.out.println("We have found the person from your url with schema:person :");
						System.out.println("***********************************************************");						
						 while (iter_schema_person.hasNext()) {
							 primaryTopic = iter_schema_person.nextStatement().getSubject();
							 this.name=personDataImpl.name(primaryTopic,m);
							 this.birthday=personDataImpl.birth(primaryTopic,m);
							 this.deathday=personDataImpl.death(primaryTopic,m);
							 this.img=personDataImpl.picture(primaryTopic,m);		
						 }
					}				
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	public String getname(){
		return this.name;
	}
	
	public String getbirthday(){
		return this.birthday;
	}
	
	public String getdeathday(){
		return this.deathday;		
	}
	
	public String getimage(){
		return this.img;		
	}
}
