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
 * @authors  
 */
public class Tutorial01 {
    // some definitions
    static String personURI    = "http://anyuri.com";
    static String fullName     = "Curso Web Semantica";
    
      public static void main (String args[]) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

       // create the resource
       Resource Me = model.createResource(personURI);

      // add the property
      Me.addProperty(VCARD.FN, fullName);
      Me.addProperty(VCARD.EMAIL, "me@anyuri.com");
      Me.addProperty(VCARD.NAME, "Me");
      model.write(new PrintWriter(System.out));
      System.out.println();
      }
}
