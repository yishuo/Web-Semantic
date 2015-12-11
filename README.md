<h1>Web-Semantic</h1>
<h3>Objectives</h3>
<p>The final goal is to produce a web page using information available on the Web of Data. We have to extract structured data from 
an existing web page or RDF document to discover more data and eventually generate information on specific things mentioned in the data.</p>
<h3>Extract relevant classes</h3>
<h6>Add an interface to my project by copying and updating the interface Classifier.</h6>
<h6>Create a class that implements this interface (e.g., ClassifierImpl). </h6>
<h6>ClassifierImpl implements the same algorithm as in Session 3 except that:</h6>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.In Step 1 of the algorithm, parse the file as Turtle, and if it does not work, try RDF/XML, then try
HTML+RDFa.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.The method retrieveTypes implements steps 1 and 2 by taking the URL of a Web page as parameter and returns the collection of IRIs that are classes of the input URL in the extracted RDF graph.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.The method retrieveSuperClasses implements sub-steps 2 and 3 within Step 4 by taking an IRI as a parameter and returning the collection of IRIs that are superclasses of the input IRI in the extracted RDF graph. Again, try parsing Turtle first, then RDF/XML, then HTML+RDFa.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.The method getAllTypes implements the overall algorithm, reusing the two previous methods and returning the complete set of classes that the input IRI instantiate.
</p>

<h3>Navigating the model</h3>
<h6>Using the methods of the previous section, implement a method that returns true whenever an entity (given by its IRI) is of a certain type (given by the IRI of a class). Test if http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi is of type http://www.emse.fr/~zimmermann/Teaching/SemWeb/vocab.ttl#Scientist (later abbreviated as v:Scientist). It should return true. Then test if http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#madamic is a v:Scientist. It should return false. Do the same tests with the class http://www.emse.fr/~zimmermann/Teaching/SemWeb/other.ttl#Human instead (later abbreviated as o:Human). It should return true for #shadi and false for #madamic.</h6>
<br><h6>Implement the interface PersonData as indicated by the description of the methods in the source code.</h6>
<br><h6>(Optional) If you are fast, implement the interface PlaceData as indicated by the description of the methods in the source code.</h6>

<h3>Extract relevant data</h3>
<p>Here, we extract data from a given IRI and display portions of it.Create a subclass PersonExtractor of the abstract class RDFExtractor and implement the given method according to the comment in the source code.
Write a method showPersonData that does the following: If the primary topic of the entity is of type foaf:Person or schema:Person (http://schema.org/Person) (determined using the method isOfType implemented before), then display on the console its name, birth date, death date and picture URL.
If no primary topic is found (i.e., the method primaryTopic returns null), then for all subjects found in the RDF graph, test if it is of type foaf:Person or schema:Person and if it is, display on the console the name, birth date, death date of picture URL.
(Optional) if you are fast, do something similar for a class PlaceExtractor, displaying useful data for things of type dbo:Place or schema:Place.
You can test you class with http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html, or with http://dbpedia.org/page/Albert_Einstein, for instance. You can also try http://dbpedia.org/page/Saint-%C3%89tienne.</p>

<h3>Generate the relevant HTML code</h3>
<p>Modify the class from the previous section such that, instead of displaying information in the console, it generates an HTML+RDFa web page with the relevant information.</p>
