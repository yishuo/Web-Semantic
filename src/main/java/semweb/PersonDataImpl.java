package semweb;

import java.rmi.RemoteException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class PersonDataImpl implements PersonData {
	protected PersonDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name(RDFNode person, Model model) {
		// TODO Auto-generated method stub
		String foaf= "http://xmlns.com/foaf/0.1/";
		String schema ="http://schema.org/";
		model.setNsPrefix("foaf", foaf);
		model.setNsPrefix("schema", schema);
		Property name_foaf_name = model.createProperty(foaf+"name");
		Property name_schema_name = model.createProperty(schema+"name");
		Property name_foaf_firstname = model.createProperty(foaf+"firstName");
		Property name_schema_givenName = model.createProperty(schema+"givenName");
		Property name_foaf_lastname = model.createProperty(foaf+"lastName");
		Property name_schema_familyName = model.createProperty(schema+"familyName");
		StmtIterator iter_first_name;
		StmtIterator iter_last_name;

		
		RDFNode r;
		String name = null;
		StmtIterator iter = model.listStatements((Resource) person,name_foaf_name,(RDFNode)null );
		if (iter.hasNext()) {
			System.out.println("The model contains foaf:name :");
				 r = iter.nextStatement().getObject();
				 System.out.println("  " + r);
				 name =r.toString();
		}else{
			iter = model.listStatements((Resource) person,name_schema_name,(RDFNode)null );			
//            System.out.println("No rdf:type were found in the model");
			if (iter.hasNext()) {
				System.out.println("The model contains schema:name :");
				 r = iter.nextStatement().getObject();
				 System.out.println("  " + r);
				 name =r.toString();
				 }else{
					 iter_first_name = model.listStatements((Resource) person,name_foaf_firstname,(RDFNode)null );
					 iter_last_name = model.listStatements((Resource) person,name_foaf_lastname,(RDFNode)null );
					 if(iter_first_name.hasNext()||iter_last_name.hasNext()){
						System.out.println("The model contains foaf:firstname + foaf:lastname :");
						 name = iter_first_name.nextStatement().getObject().toString()+" "+iter_last_name.nextStatement().getObject().toString();
						 System.out.println("  " + name);
					 }else{
						 iter_first_name = model.listStatements((Resource) person,name_schema_givenName,(RDFNode)null );
						 iter_last_name = model.listStatements((Resource) person,name_schema_familyName,(RDFNode)null );
						 if(iter_first_name.hasNext()||iter_last_name.hasNext()){
								System.out.println("The model contains schema:givenName + schema:familyName :");
								 name = iter_first_name.nextStatement().getObject().toString()+" "+iter_last_name.nextStatement().getObject().toString();
								 System.out.println("  " + name);
							 }else{
									iter = model.listStatements((Resource) person,RDFS.label,(RDFNode)null );
									if (iter.hasNext()) {
										System.out.println("The model contains rdfs:label :");
										 r = iter.nextStatement().getObject();
										 System.out.println("  " + r);
										 name =r.toString();
										 }else{
											 System.out.println("we don't have object about name :");
										 }																	 
							 }						 
					 }
				 }
        }
		return name;		 
	}


	@Override
	public String birth(RDFNode person, Model model) {
		// TODO Auto-generated method stub
		String dbo = "http://dbpedia.org/ontology/";
		String vcard ="http://www.w3.org/2006/vcard/ns#";
		String schema ="http://schema.org/";
		model.setNsPrefix("dbo", dbo);
		model.setNsPrefix("vcard", vcard);
		model.setNsPrefix("schema", schema);
		Property birth_dbo = model.createProperty(dbo+"birthDate");
		Property birth_vcard = model.createProperty(vcard+"bday");
		Property birth_schema = model.createProperty(schema+"birthDate");
		
		RDFNode r;
		String birthday = null;
		StmtIterator iter_birth = model.listStatements((Resource) person,birth_dbo,(RDFNode)null );
		if (iter_birth.hasNext()) {
			System.out.println("The model contains birth_dbo :");
				 r = iter_birth.nextStatement().getObject();
				 System.out.println("  " + r);
				 birthday =r.toString();
		}else{
			iter_birth = model.listStatements((Resource) person,birth_vcard,(RDFNode)null );			
			if (iter_birth.hasNext()) {
				System.out.println("The model contains birth_vcard :");
				 r = iter_birth.nextStatement().getObject();
				 System.out.println("  " + r);
				 birthday =r.toString();
				 }else{
					 iter_birth = model.listStatements((Resource) person,birth_schema,(RDFNode)null );
					 if(iter_birth.hasNext()){
						System.out.println("The model contains birth_schema :");
						 birthday = iter_birth.nextStatement().getObject().toString().getClass().toString();
						 System.out.println("  " + birthday);
						 }else{
							 System.out.println("we don't have object about birthdate :");
							 }						 
					 }
			}
		return birthday;
	}


	@Override
	public String death(RDFNode person, Model model) {
		// TODO Auto-generated method stub
		String dbo = "http://dbpedia.org/ontology/";
		String schema ="http://schema.org/";
		model.setNsPrefix("dbo", dbo);
		model.setNsPrefix("schema", schema);
		Property death_dbo = model.createProperty(dbo+"deathDate");
		Property death_schema = model.createProperty(schema+"deathDate");
		
		RDFNode r;
		String deathdate = null;
		StmtIterator iter_death = model.listStatements((Resource) person,death_dbo,(RDFNode)null );
		if (iter_death.hasNext()) {
			System.out.println("The model contains death_dbo :");
				 r = iter_death.nextStatement().getObject();
				 System.out.println("  " + r);
				 deathdate =r.toString();
		}else{
			iter_death = model.listStatements((Resource) person,death_schema,(RDFNode)null );			
			if (iter_death.hasNext()) {
				System.out.println("The model contains death_schema :");
				 r = iter_death.nextStatement().getObject();
				 System.out.println("  " + r);
				 deathdate =r.toString();
				 }else{
					 System.out.println("we don't have object about deathdate :");
					 }					 
			}
		return deathdate;
		}
		
	@Override
	public String picture(RDFNode person, Model model) {
		// TODO Auto-generated method stub
		String foaf ="http://xmlns.com/foaf/0.1/";
		String schema ="http://schema.org/";
		model.setNsPrefix("foaf", foaf);
		model.setNsPrefix("schema", schema);
		Property img_foaf = model.createProperty(foaf+"img");
		Property depiction_foaf = model.createProperty(foaf+"depiction");
		Property image_schema = model.createProperty(schema+"image");
		
		RDFNode r;
		String image_url = null;
		StmtIterator iter_img = model.listStatements((Resource) person,img_foaf,(RDFNode)null );
		if (iter_img.hasNext()) {
			System.out.println("The model contains img_foaf :");
				 r = iter_img.nextStatement().getObject();
				 System.out.println("  " + r);
				 image_url =r.toString();
		}else{
			iter_img = model.listStatements((Resource) person,depiction_foaf,(RDFNode)null );			
			if (iter_img.hasNext()) {
				System.out.println("The model contains depiction_foaf :");
				 r = iter_img.nextStatement().getObject();
				 System.out.println("  " + r);
				 image_url =r.toString();
				 }else{
					 iter_img = model.listStatements((Resource) person,image_schema,(RDFNode)null );
					 if(iter_img.hasNext()){
						System.out.println("The model contains image_schema :");
						image_url = iter_img.nextStatement().getObject().toString().getClass().toString();
						 System.out.println("  " + image_url);
						 }else{
							 System.out.println("we don't have object about image :");
							 }
					 }
			}
		return image_url;
	}
		
		


}
