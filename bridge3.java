// Modificado por: G. Isaza Octubre 2009 - GITIR 
// JESS Bridge Inferencia para OntoIDPSMA.owl - Reglas reducidas
// ********************************************************************************************************
// ********************************************************************************************************
import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFIndividual;
import edu.stanford.smi.protegex.owl.*;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.repository.impl.LocalFolderRepository;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.bridge.jess.SWRLJessBridge;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.repository.impl.LocalFolderRepository;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.bridge.jess.SWRLJessBridge;
import java.io.PrintWriter;
import java.util.*; 
import java.io.FileWriter;
import java.lang.Long;
import java.lang.String;
import java.lang.Object;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.ontology.OntClass.*;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.ModelLoader;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.rdf.model.ModelFactoryBase;
import com.hp.hpl.jena.query.*; 
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Set;
import javax.swing.JOptionPane;
import java.io.OutputStream;
import java.io.FileOutputStream;
import jess.*;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.repository.impl.LocalFolderRepository;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.bridge.jess.SWRLJessBridge;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import jess.Rete;
import java.util.ArrayList;
import java.util.Collection;
import com.hp.hpl.jena.util.FileUtils;
import edu.stanford.smi.protegex.owl.writer.rdfxml.rdfwriter.OWLModelWriter;


public class bridge3
	{
    public static void main(String[] args)
	{	
		try {
			// Modelo OWL
			FileInputStream is = new FileInputStream("OntoIDSSMACopiaSWRL.owl");
			OWLModel owlModel = ProtegeOWL.createJenaOWLModelFromInputStream(is);
					
			Rete engine = new Rete();
			// Crea el puente
			SWRLJessBridge bridge = new SWRLJessBridge(owlModel,engine);
			// "Resetea" el engine de inferencia
			bridge.reset();
			// Obtiene las regla definidas en OWL/SWRL para ser procesadas por el JESS engine
			bridge.importSWRLRulesAndOWLKnowledge();
			System.out.println("Imported Axioms :" +bridge.getImportedAxioms());
			System.out.println("Imported Classes :" +bridge.getImportedClasses());
			System.out.println("Imported Individuals :" +bridge.getImportedIndividuals());
			System.out.println("Imported SWRLRules :" +bridge.getImportedSWRLRules());
			// Ejecuta el motor de inferencia 
			bridge.run();
		        System.out.println("Inferred Axioms :" +bridge.getInferredAxioms());
			System.out.println("Inferred Individuals :" +bridge.getInferredIndividuals());

			// Retorna lo inferido al modelo OWL
			bridge.writeInferredKnowledge2OWL();	
		       //Guarda el archivo OWL 
			FileWriter writer = new FileWriter("OntoIDSSMACopiaSWRL2.owl");
			OWLModelWriter omw = new OWLModelWriter(owlModel,owlModel.getTripleStoreModel().getActiveTripleStore(), writer);
            omw.write();  
			writer.close();
				
	} catch (Exception e) { System.out.println("Exception: " + e);	}
}
}
