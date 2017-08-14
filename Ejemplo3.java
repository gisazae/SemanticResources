/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javajena;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.VCARD;
import java.io.PrintWriter;

/**
 *
 */
public class Ejemplo3 {
    
      public static void main (String args[]) {
        // some definitions
        String personURI    = "http://www.luisfercastillo.co";
        String givenName    = "Luis";
        String familyName   = "Castillo";
        String fullName     = givenName + " " + familyName;

        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource luis  = model.createResource(personURI)
             .addProperty(VCARD.FN, fullName)
             .addProperty(VCARD.N, 
                      model.createResource()
                           .addProperty(VCARD.Given, givenName)
                           .addProperty(VCARD.Family, familyName)
                           .addProperty(VCARD.CATEGORIES, "Asociado"));
        
        
         // list the statements in the graph
        StmtIterator iter = model.listStatements();
        
        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement();         // get next statement
            Resource  subject   = stmt.getSubject();   // get the subject
            Property  predicate = stmt.getPredicate(); // get the predicate
            RDFNode   object    = stmt.getObject();    // get the object
            
            System.out.print("sujeto:"+ subject.toString());
            System.out.print(" predicado: " + predicate.toString() + " Objeto");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
        
        
        model.write(new PrintWriter(System.out));
      System.out.println();
        
      }
}
