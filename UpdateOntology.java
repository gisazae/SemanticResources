/**
 * @author Gustavo A. Isaza 
 * Universidad de Caldas
 * Abril de 2010
 * 
*/

package utiles;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.ontology.OntClass.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.ModelLoader;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.ontology.Individual.*;
import com.hp.hpl.jena.db.*;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.ontology.Individual;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;

import com.hp.hpl.jena.vocabulary.*;

import com.hp.hpl.jena.util.FileManager;

import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.lang.Long;
import java.lang.String;
import java.lang.Object;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

/**
 * @author Gustavo A. Isaza 
 * Universidad de Caldas
 * /

public class UpdateOntology {

	public static String raiz_AIU;
	public static ArrayList<String> lista_NS;
	public static ArrayList<String> ListaAlertas;
	public static ArrayList lista_conceptos;
	public static String prefijo_ontologia;		
	public static String nombre_clase_nodo;	
	public static String prefijo_sparql;
        public String descripcion;

	public UpdateOntology() {
	}



//---Inicio Main TestOntology1 la Ontologia
    public UpdateOntology(String eventId) {

        String owlFile = "/home/gisaza/Tools/Jena-2.5.1/src-examples/jena/examples/ontology/leerOntologia/CargarOntologia/src/cargarontologia/OntoIDPSMA.owl";
        String indName = "http://ingenieria.ucaldas.edu.co/gisaza/OntoIDPSMA.owl#TrafficSignatures";
        String EventoCID = "Ataque2";
	String cid = "2";
	String EventoSensor = " ";
        //iniciar(owlFile, indName,EventoCID, cid, EventoSensor);
    }


	public static void main(String[] args) {
        //TestOntology1();
    //public static void iniciar(int dato){
		//try {
        PrintWriter out = new PrintWriter(System.out);
		/*if (args.length != 3)
	      throw new 
			IllegalArgumentException ("uso: TestOntology1 <OWLFile> <URI_Completo_Ontologia#Clase> <Patron_Ataque>");*/
		
		/*String owlFile = args[0];
		String indName = args[1];
		String patronAtaque = args[2];*/

        String owlFile = "/home/gisaza/Tools/Jena-2.5.1/src-examples/jena/examples/ontology/leerOntologia/CargarOntologia/src/cargarontologia/OntoIDPSMA.owl";
        String indName = "http://ingenieria.ucaldas.edu.co/gisaza/OntoIDPSMA.owl#Event";
        String Evento = "Ataque2";
	String cid = "2";
	//iniciar(owlFile,indName,Evento,cid,"1","b2","Sens1","tcp","192.1.x","80","200.21.x","25","EAECX23220232122","2009-04-22");
        //iniciar(owlFile, indName,Evento, cid);
        }

     public static void iniciar(String owlFile, String indName, String EventoCID, String cid, String EventoSensor, String EventoPrior, String EventoName, String EventoProt, String EventoSIP, String EventoSP, String EventoDIP, String EventoDP, String EventoData, String EventoTime) {
		System.out.println("Cargando Archivo de Ontologia " + owlFile);
        ListaAlertas = new ArrayList<String>();
		Model model = FileManager.get().loadModel(owlFile);


		OntModel ontModel =  ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,model);
		String padreName="";

		
		OntClass ind=ontModel.getOntClass(indName);

/*		if (ind == null) {
		    System.out.println("Clase " + indName + " not found!");
		} else {
			System.out.println("Clase seleccionada existe: " +ind);
			System.out.println("Clase: " + ind.getLocalName());

			System.out.println("==================================");
        		System.out.println("Imprimiendo Instancias ");
		        System.out.println("==================================");


		
			    ExtendedIterator iteratorInstances = ind.listInstances();
			      while (iteratorInstances.hasNext()){
			   	System.out.println("tiene: "+iteratorInstances.next());
      			    }
			    System.out.println("");
                          }*/

//----------------------------------------------------------------------------------------
// Crear la estructura de PREFIX para el SPARQL y ARQ
//----------------------------------------------------------------------------------------
	String SOURCE = "http://ingenieria.ucaldas.edu.co/gisaza/OntoIDPSMA.owl";
	String NS = SOURCE + "#";
	prefijo_ontologia= "";
	prefijo_ontologia = NS;
	String cadena = NS + ind.getLocalName();
	//String eventID = "eventID";

	String OWL_NS = ontModel.getNsPrefixURI("owl");
	String RDF_NS = ontModel.getNsPrefixURI("rdf");
	String DEFAULT_NS = ontModel.getNsPrefixURI("");
	//System.out.println("NS :" +OWL_NS);
	//System.out.println("RDF: " +RDF_NS);
	//System.out.println("DEFAULT_NS: " +DEFAULT_NS);

// Se crea la tripleta Clase + Propiedad + Instancia + Valor
// EventoCID,  EventoSensor, EventoPrior, EventoName, EventoProt, EventoSIP, EventoSP, EventoDIP, EventoDP, EventoData, EventoTime

        OntClass c =ontModel.getOntClass(indName);
	Resource recurso = ontModel.getResource(NS + "Event");
	DatatypeProperty pro = ontModel.getDatatypeProperty(NS + "eventID");
	pro.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoCID == null )
		EventoCID = "-";
	Literal literal = ontModel.createLiteral(EventoCID);
	Individual indu=ontModel.createIndividual(NS + cid,c);
	//Literal literal = ontModel.createLiteral("Ataque2");
	//Individual indu=ontModel.createIndividual(NS + "Event_2",c);
	indu.setPropertyValue(pro, literal);

	DatatypeProperty pro1 = ontModel.getDatatypeProperty(NS + "SensorID");
	pro1.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoSensor == null )
		EventoSensor = "-";
        Literal literal1 = ontModel.createLiteral(EventoSensor);
	Individual indu1=ontModel.createIndividual(NS + cid,c);
	indu1.setPropertyValue(pro1, literal1);

	DatatypeProperty pro2 = ontModel.getDatatypeProperty(NS + "Priority");
	pro2.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoPrior == null )
		EventoPrior = "-";
        Literal literal2 = ontModel.createLiteral(EventoPrior);
	Individual indu2=ontModel.createIndividual(NS + cid,c);
	indu2.setPropertyValue(pro2, literal2);

	DatatypeProperty pro3 = ontModel.getDatatypeProperty(NS + "NameEvent");
	pro3.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoName == null )
		EventoName = "-";
        Literal literal3 = ontModel.createLiteral(EventoName);
	Individual indu3=ontModel.createIndividual(NS + cid,c);
	indu3.setPropertyValue(pro3, literal3);

	DatatypeProperty pro4 = ontModel.getDatatypeProperty(NS + "ProtocolEvent");
	pro4.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoProt == null )
		EventoProt = "-";
        Literal literal4 = ontModel.createLiteral(EventoProt);
	Individual indu4=ontModel.createIndividual(NS + cid,c);
	indu4.setPropertyValue(pro4, literal4);

	DatatypeProperty pro5 = ontModel.getDatatypeProperty(NS + "SrcIP");
	pro5.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoSIP == null )
		EventoSIP = "-";
        Literal literal5 = ontModel.createLiteral(EventoSIP);
	Individual indu5=ontModel.createIndividual(NS + cid,c);
	indu5.setPropertyValue(pro5, literal5);

	DatatypeProperty pro6 = ontModel.getDatatypeProperty(NS + "srcPort");
	pro6.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoSP == null )
		EventoSP = "-";
        Literal literal6 = ontModel.createLiteral(EventoSP);
	Individual indu6=ontModel.createIndividual(NS + cid,c);
	indu6.setPropertyValue(pro6, literal6);

	DatatypeProperty pro7 = ontModel.getDatatypeProperty(NS + "dstIP");
	pro7.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoDIP == null )
		EventoDIP = "-";
        Literal literal7 = ontModel.createLiteral(EventoDIP);
	Individual indu7=ontModel.createIndividual(NS + cid,c);
	indu7.setPropertyValue(pro7, literal7);

	DatatypeProperty pro8 = ontModel.getDatatypeProperty(NS + "dstPort");
	pro8.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoDP == null )
		EventoDP = "-";
        Literal literal8 = ontModel.createLiteral(EventoDP);
	Individual indu8=ontModel.createIndividual(NS + cid,c);
	indu8.setPropertyValue(pro8, literal8);

	DatatypeProperty pro9 = ontModel.getDatatypeProperty(NS + "data");
	pro9.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoData == null )
		EventoData = "-";
        Literal literal9 = ontModel.createLiteral(EventoData);
	Individual indu9=ontModel.createIndividual(NS + cid,c);
	indu9.setPropertyValue(pro9, literal9);

	DatatypeProperty pro10 = ontModel.getDatatypeProperty(NS + "timestamp");
	pro10.addDomain(ontModel.getOntClass( NS + "Event"));
	if (EventoTime == null )
		EventoTime = "-";
        Literal literal10 = ontModel.createLiteral(EventoTime);
	Individual indu10=ontModel.createIndividual(NS + cid,c);
	indu.setPropertyValue(pro10, literal10);


	
	
	
String OutOnto = "/home/gisaza/Tools/Jena-2.5.1/src-examples/jena/examples/ontology/leerOntologia/CargarOntologia/src/cargarontologia/OntoIDPSMA.owl";
        try
        {
	  FileOutputStream outputOntology = new FileOutputStream(OutOnto);
	 //OutputStream out = new OutputStream(outputFile);
	 ontModel.write(outputOntology, "RDF/XML-ABBREV");
         outputOntology.close();
	 ontModel.close();
        }
	catch (Exception e) {
	  System.out.println("Error generando archivo.");
	}

 }
}

