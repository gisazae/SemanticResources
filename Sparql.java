import com.hp.hpl.jena.query.*; 
import com.hp.hpl.jena.ontology.*; 
import com.hp.hpl.jena.query.QueryFactory; 
import com.hp.hpl.jena.rdf.model.ModelFactory; 
import com.hp.hpl.jena.rdf.model.*; 
import com.hp.hpl.jena.vocabulary.OWL; 
import com.hp.hpl.jena.vocabulary.RDFS; 

public class Sparql { 
public static void main(String[] args) 
{ 
String foaf="http://xmlns.com/foaf/0.1/"; 
String NS="http://ingenieria.ucaldas.edu.co/gisaza/2014/WebSemantica/JavaJena/ejemplo1.owl#"; 
OntModel modelo = ModelFactory.createOntologyModel(); 
OntClass persona=modelo.createClass(NS+"Persona") ; 
OntClass estudiante=modelo.createClass(NS+"Estudiante") ; 
OntClass materia=modelo.createClass(NS+"Materia") ; 
persona.addSuperClass(OWL.Thing); 
persona.addSubClass(estudiante) ; 
persona.addDisjointWith(materia); 
ObjectProperty cursa=modelo.createObjectProperty(NS+"cursa") ; 
cursa.addProperty(RDFS.range,materia) ; 
cursa.addProperty(RDFS.domain,estudiante) ; 
ObjectProperty es_cursada=modelo.createObjectProperty(NS+"es_cursada") ; 
es_cursada.addProperty(RDFS.domain,materia) ; 
es_cursada.addProperty(RDFS.range,estudiante) ; 
cursa.addProperty(OWL.inverseOf, es_cursada); 
String queryString1 = 
"select * "+
"where { "+ 
"<http://ingenieria.ucaldas.edu.co/gisaza/2014/WebSemantica/JavaJena/ejemplo1.owl#Estudiante> ?i ?o "+ 
"} \n "; 
Query query1 = QueryFactory.create(queryString1); 
System.out.println("----------------------"); 
System.out.println("Resultado"); 
System.out.println("----------------------"); 
QueryExecution qe = QueryExecutionFactory.create(query1,modelo); 
ResultSet results = qe.execSelect(); 
// Output query results 
 ResultSetFormatter.out(System.out, results, query1); 
 qe.close(); 
} 
} 

