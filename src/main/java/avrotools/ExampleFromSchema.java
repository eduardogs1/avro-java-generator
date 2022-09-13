package avrotools;


import java.util.Iterator;
import java.util.logging.Logger;

import com.inditex.mecc.mecprwat.api.avro.v2.ProductChangesEnvelope;

import org.apache.avro.Schema;
import org.apache.avro.util.RandomData;

public class ExampleFromSchema {

  private static Logger logger = Logger.getLogger(Avro2SchemaRegistry.class.toString());

  public static void main(String [] args){
    ProductChangesEnvelope avroEnvelope = new ProductChangesEnvelope();

    Schema schema = avroEnvelope.getSchema();
    Iterator<Object> it = new RandomData(schema, 1).iterator();
    logger.info(it.next().toString());
}


}

