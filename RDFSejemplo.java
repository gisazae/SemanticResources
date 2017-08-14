/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javajena;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.VCARD;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public class RDFSejemplo {
    
     public static void main (String args[]) {
        // some definitions
       // URI declarations
String familyUri = "http://family/";
String relationshipUri = "http://purl.org/vocab/relationship/";

// Create an empty Model
Model model = ModelFactory.createDefaultModel();

// Create a Resource for each family member, identified by their URI
Resource adam = model.createResource(familyUri+"adam");
Resource beth = model.createResource(familyUri+"beth");
Resource chuck = model.createResource(familyUri+"chuck");
Resource dotty = model.createResource(familyUri+"dotty");
Resource edward = model.createResource(familyUri+"edward");
Resource fran = model.createResource(familyUri+"fran");

// and so on for other family members

// Create properties for the different types of relationship to represent
Property childOf = model.createProperty(relationshipUri,"childOf");
Property parentOf = model.createProperty(relationshipUri,"parentOf");
Property siblingOf = model.createProperty(relationshipUri,"siblingOf");
Property spouseOf = model.createProperty(relationshipUri,"spouseOf");

// Add properties to adam describing relationships to other family members
adam.addProperty(siblingOf,beth);
adam.addProperty(spouseOf,dotty);
adam.addProperty(parentOf,edward);

// Can also create statements directly . . .
Statement statement = model.createStatement(adam,parentOf,fran);

// but remember to add the created statement to the model
model.add(statement);
         
model.write(new PrintWriter(System.out));

try{
  FileOutputStream fout=new FileOutputStream(
  "RDFS.xml");
  model.write(fout);
  }catch(IOException e){
  System.out.println("Exception caught"+e.getMessage());
  }
        
      }
    
}
