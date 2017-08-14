/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javajena;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;
import java.io.PrintWriter;

/**
 *
 * @author 
 */
public class Tutorial02 {
    
      public static void main (String args[]) {
        // some definitions
        String personURI    = "http://anyuri.com";
        String givenName    = "Some";
        String familyName   = "Body";
        String fullName     = givenName + " " + familyName;

        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource Me  = model.createResource(personURI)
             .addProperty(VCARD.FN, fullName)
             .addProperty(VCARD.N, 
                      model.createResource()
                           .addProperty(VCARD.Given, givenName)
                           .addProperty(VCARD.Family, familyName)
                           .addProperty(VCARD.CATEGORIES, "Asociado"));
        
        model.write(new PrintWriter(System.out));
      System.out.println();
        
      }
}
