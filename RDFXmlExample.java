/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javajena;

/**
 *
 */

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.FileOutputStream;
import java.io.IOException;

public class RDFXmlExample {
    public static void main( String[] args ) {
        new RDFXmlExample().run();
    }

    public void run() {
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM );
        String NS = "http://example.com/test#";

        OntClass a = m.createClass( NS + "A" );
        OntClass b = m.createClass( NS + "B" );

        a.addSubClass( b );

        OntProperty c = m.createOntProperty( NS + "c" );
        c.addRange( a );

        m.write( System.out, "RDF/XML-ABBREV" );
        
        try{
  FileOutputStream fout=new FileOutputStream(
  "SubClases.xml");
  m.write(fout);
  }catch(IOException e){
  System.out.println("Exception caught"+e.getMessage());
  }
    }
}
    
