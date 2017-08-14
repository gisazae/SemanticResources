/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javajena;

/**
 *
 */
import java.io.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;

public class RDFIterator extends Object {
  public static void main (String args[]) {
  String personURI  = "http://amitkumar";
  String givenName  = "Amit";
  String familyName = "Kumar";
  String fullName = givenName + " " + familyName;
  Model model = ModelFactory.createDefaultModel();
  Resource node = model.createResource(personURI)
   .addProperty(VCARD.FN, fullName)
  .addProperty(VCARD.N, 
   model.createResource()
 .addProperty(VCARD.Given, givenName)
  .addProperty(VCARD.Family, familyName));
  StmtIterator iter = model.listStatements();
  
  
  while (iter.hasNext()) {
  Statement stmt  = iter.nextStatement(); 
  Resource  subject = stmt.getSubject(); 
  Property  predicate = stmt.getPredicate();  
  RDFNode object  = stmt.getObject();  
  System.out.print(subject.toString());
  System.out.print(" " + predicate.toString() + " ");
  if (object instanceof Resource) {
  System.out.print(object.toString());
  } else {
  System.out.print("\"" + object.toString() + "\"");
  }
  System.out.println(".");
  }
  
  
  }
}
