/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javajena;

/**
 *
 */


import java.io.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;

public class RDFQuery 
{
  public static void main (String args[]) {
  String personURI  = "http://amitKumar";
  String givenName  = "Amit";
  String familyName = "Kumar";
  String fullName = givenName +" "+ familyName;
  Model model = ModelFactory.createDefaultModel();
  Resource node = model.createResource(personURI)
 .addProperty(VCARD.FN, fullName)
 .addProperty(VCARD.N,
  model.createResource()
 .addProperty(VCARD.Given, givenName)
 .addProperty(VCARD.Family, familyName));
  ResIterator iter= model.listSubjectsWithProperty(VCARD.FN);
  
  
  while(iter.hasNext()){
  Resource res=iter.nextResource();
  System.out.println("Property  "+
  res.getProperty(VCARD.FN).getString());
  System.out.println("Resource URI  "+res.getURI());  
  }
  }
}
 
