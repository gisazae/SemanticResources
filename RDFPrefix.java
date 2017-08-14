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

public class RDFPrefix extends Object {
  public static void main (String args[]) {
 
 Model model = ModelFactory.createDefaultModel();
 Resource root = model.createResource(
   "http://amit/Kumar#"+"root");
 Property property = model.createProperty(
   "http://amit/Kumar#"+"Property");
 Resource child = model.createResource(
   "http://amit/Kumar#" +"child");
 model.add(root,property,child);
 model.write( System.out );
 try{
  FileOutputStream fout=new FileOutputStream(
  "prefix.xml");
  model.write(fout);
  }catch(IOException e){
  System.out.println("Exception caught"+e.getMessage());
  }
  }
}
