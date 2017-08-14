/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javajena;

/**
 *
 */
import java.util.*;

import com.hp.hpl.jena.rdf.model.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A small family tree held in a Jena Model
 */
public class FamilyModel {
  
  // Namespace declarations
  static final String familyUri = "http://family/";
  static final String relationshipUri = "http://purl.org/vocab/relationship/";

  // Jena model representing the family
  private Model model;

  /**
   * Creates a model and populates it with family members and their
   * relationships
   */
  private FamilyModel() {
    
    // Create an empty Model
    model = ModelFactory.createDefaultModel();
    
    // Create the types of Property we need to describe relationships
    // in the model
    Property childOf = model.createProperty(relationshipUri,"childOf");
    Property parentOf = model.createProperty(relationshipUri,"parentOf");
    Property siblingOf = model.createProperty(relationshipUri,"siblingOf");
    Property spouseOf = model.createProperty(relationshipUri,"spouseOf");
    
    // Create resources representing the people in our model
    Resource adam = model.createResource(familyUri+"adam");
    Resource beth = model.createResource(familyUri+"beth");
    Resource chuck = model.createResource(familyUri+"chuck");
    Resource dotty = model.createResource(familyUri+"dotty");
    Resource edward = model.createResource(familyUri+"edward");
    Resource fran = model.createResource(familyUri+"fran");
    Resource greg = model.createResource(familyUri+"greg");
    Resource harriet = model.createResource(familyUri+"harriet");

    // Add properties to describing the relationships between them
    adam.addProperty(siblingOf,beth);
    adam.addProperty(spouseOf,dotty);
    adam.addProperty(parentOf,edward);
    adam.addProperty(parentOf,fran);

    beth.addProperty(siblingOf,adam);
    beth.addProperty(spouseOf,chuck);

    chuck.addProperty(spouseOf,beth);

    dotty.addProperty(spouseOf,adam);
    dotty.addProperty(parentOf,edward);
    dotty.addProperty(parentOf,fran);

    // Statements can also be directly created ...
    Statement statement1 = model.createStatement(edward,childOf,adam);
    Statement statement2 = model.createStatement(edward,childOf,dotty);
    Statement statement3 = model.createStatement(edward,siblingOf,fran);

    // ... then added to the model:
    model.add(statement1);
    model.add(statement2);
    model.add(statement3);

    // Arrays of Statements can also be added to a Model:
    Statement statements[] = new Statement[5];
    statements[0] = model.createStatement(fran,childOf,adam);
    statements[1] = model.createStatement(fran,childOf,dotty);
    statements[2] = model.createStatement(fran,siblingOf,edward);
    statements[3] = model.createStatement(fran,spouseOf,greg);
    statements[4] = model.createStatement(fran,parentOf,harriet);
    model.add(statements);

    // A List of Statements can also be added
    List list = new ArrayList();

    list.add(model.createStatement(greg,spouseOf,fran));
    list.add(model.createStatement(greg,parentOf,harriet));

    list.add(model.createStatement(harriet,childOf,fran));
    list.add(model.createStatement(harriet,childOf,greg));
      
    model.add(list);
  }
  
  /**
   * Creates a FamilyModel and dumps the content of its RDF representation
   */
  public static void main(String args[]) {

    // Create a model representing the family
    FamilyModel theFamily = new FamilyModel();

    // Dump out a String representation of the model
    System.out.println(theFamily.model);
    
    theFamily.model.write(new PrintWriter(System.out));
    
    try{
  FileOutputStream fout=new FileOutputStream(
  "Family.xml");
  theFamily.model.write(fout);
  }catch(IOException e){
  System.out.println("Exception caught"+e.getMessage());
  }
  }
}
