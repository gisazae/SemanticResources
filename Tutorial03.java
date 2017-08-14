/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javajena;

/**
 *
 */
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;


/** Tutorial 3 Statement attribute accessor methods
 *
 * @author  bwm - updated by kers/Daniel
 * @version Release='$Name: not supported by cvs2svn $' Revision='$Revision: 1.3 $' Date='$Date: 2005-10-06 17:49:05 $'
 */
public class Tutorial03 extends Object {
    public static void main (String args[]) {
    
        // some definitions
        String personURI    = "http://somewhere/JohnSmith";
        String givenName    = "John";
        String familyName   = "Smith";
        String fullName     = givenName + " " + familyName;
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource johnSmith 
          = model.createResource(personURI)
                 .addProperty(VCARD.FN, fullName)
                 .addProperty(VCARD.N, 
                              model.createResource()
                                   .addProperty(VCARD.Given, givenName)
                                   .addProperty(VCARD.Family, familyName));
        
        // list the statements in the graph
        StmtIterator iter = model.listStatements();
        
        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement();         // get next statement
            Resource  subject   = stmt.getSubject();   // get the subject
            Property  predicate = stmt.getPredicate(); // get the predicate
            RDFNode   object    = stmt.getObject();    // get the object
            
            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }
}
