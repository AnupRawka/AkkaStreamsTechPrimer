package akkastream.primer.util

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods.parse
import scala.io.Source
import akkastream.primer._
trait InitHelper {

  implicit val system = ActorSystem("AkkaStreamsPrimer")
  implicit val materializer = ActorMaterializer()


  val resourcesPath = getClass.getResource("/FL_insurance_sample.csv")
  val schemaPath = getClass.getResource("/fl_insurance_schema.csv.js").getPath

  println(resourcesPath.getPath)

  val src = Source.fromFile(schemaPath)
  val rawSchema = src.getLines.mkString
  src.close()
  implicit val format = DefaultFormats
  val schema = parse(rawSchema).extract[Desc]
  val converter = DsvToJsonConverter(schema)
}
