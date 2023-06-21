package example

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler, RequestStreamHandler}
import io.cucumber.core.cli.Main
import io.cucumber.core.internal.com.fasterxml.jackson.databind.{JsonDeserializer, JsonSerializer, ObjectMapper}
import io.cucumber.core.internal.com.fasterxml.jackson.databind.annotation.JsonSerialize

import java.io.{InputStream, OutputStream}
import java.util
import scala.io.Source

class Lambda extends RequestHandler[java.util.Map[String, String], Unit] {

  override def handleRequest(input: util.Map[String, String], context: Context): Unit = {
    val name = input.get("name")
    val file = input.get("file")
    Main.run("-p", "pretty", "--name", s"^$name$$", "--glue", "steps", getClass.getResource(s"/features/$file").getPath)
  }
}
