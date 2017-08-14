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

public class RDFWriter {
    
  public static void main (String args[]) {
  String personURI  = "http://amitKumar";
  String givenName  = "Amit";
  String familyName = "Kumar";
  String fullName = givenName+" "+familyName;

  Model model = ModelFactory.createDefaultModel();

  Resource node = model.createResource(personURI)
 .addProperty(VCARD.FN, fullName)
 .addProperty(VCARD.N,
  model.createResource()
 .addProperty(VCARD.Given, givenName)
 .addProperty(VCARD.Family, familyName));
  try{
  FileOutputStream fout=new FileOutputStream(
  "amitKumar.xml");
  model.write(fout);
  }catch(IOException e){
  System.out.println("Exception caught"+e.getMessage());
  }
  }
}

