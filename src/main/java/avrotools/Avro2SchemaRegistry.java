package avrotools;

import java.io.IOException;
import java.util.logging.Logger;

import com.inditex.mecc.mecprwat.api.avro.v2.ProductChangesEnvelope;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.avro.Schema;

public class Avro2SchemaRegistry {

  private final OkHttpClient httpClient = new OkHttpClient();

  private static Logger logger = Logger.getLogger(Avro2SchemaRegistry.class.toString());

  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  public static final String URL_SCHEMA_REGISTRY_VALIDATION = "http://localhost:8082/compatibility/"
      + "subjects/meccano.productandcatalog.product.v6-value/versions/latest";

  public static void main(String[]  args) {
    Avro2SchemaRegistry obj = new Avro2SchemaRegistry();
    ProductChangesEnvelope avroEnvelope = new ProductChangesEnvelope();
    Schema schema = avroEnvelope.getSchema();
    String schemaString = "{\"schema\": \""+schema.toString().replaceAll("\"","\\\\\"")+"\"}";
    logger.info("Schema to validate: " + schema);
    try {
      String response = obj.post(schemaString);
      logger.info(response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  String post(String json) throws IOException {
    RequestBody body = RequestBody.create(json, JSON);
    Request request = new Request.Builder()
        .url(URL_SCHEMA_REGISTRY_VALIDATION)
        .post(body)
        .build();
    Response response = httpClient.newCall(request).execute();
    return response.body().string();
  }

}

