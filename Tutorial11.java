/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javajena;

/**
 *
 */
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.io.PrintWriter;

/** Tutorial 11 - more on literals
 *
 * @author  bwm - updated by kers/Daniel
 * @version Release='$Name: not supported by cvs2svn $' Revision='$Revision: 1.5 $' Date='$Date: 2007-11-14 09:51:57 $'
 */
public class Tutorial11 extends Object {
    
      public static void main (String args[]) {
        // create an empty graph
        Model model = ModelFactory.createDefaultModel();

       // create the resource
       Resource r = model.createResource();                                     

      // add the property
      r.addProperty(RDFS.label, model.createLiteral("chat", "en"))
       .addProperty(RDFS.label, model.createLiteral("chat", "fr"))
       .addProperty(RDFS.label, model.createLiteral("<em>chat</em>", true));
      
       r.addProperty(RDFS.label, model.createLiteral("tillo", "en"))
       .addProperty(RDFS.label, model.createLiteral("tillo", "fr"))
       .addProperty(RDFS.label, model.createLiteral("<em>tillo</em>", true));
       
       
      
      // write out the graph
      model.write(new PrintWriter(System.out));
      System.out.println();
      
      // create an empty graph
      model = ModelFactory.createDefaultModel();

       // create the resource
       r = model.createResource();                                     

      // add the property
      r.addProperty(RDFS.label, "11")
       .addLiteral(RDFS.label, 11);
      
      // write out the graph
      model.write( System.out, "N-TRIPLE");
      }
}
