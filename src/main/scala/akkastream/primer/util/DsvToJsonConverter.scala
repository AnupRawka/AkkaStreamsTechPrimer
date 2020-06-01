package akkastream.primer.util

import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import scala.collection.mutable
import akkastream.primer._
object DsvToJsonConverter {
  def apply(schema : Desc) : DsvToJsonConverter = {
    new DsvToJsonConverter(schema)
  }

  class DsvToJsonConverter (schema: Desc) {

    private val resultMap = new mutable.HashMap[String, String] ()
    private val fieldsList: Array[String] = schema.fields.map (field => field.name).toArray

    /**
     * Method to convert a delimited message to Json based on provided schema.
     *
     * @param input
     * @return [String]
     */

    def convert (input: String) : String = {
      implicit val formats = DefaultFormats
      val tokenizedString = input.split(",")
      resultMap.clear()
      for (i <- tokenizedString.indices) {
        resultMap(fieldsList(i)) = (tokenizedString(i).trim)
      }
      write{Map(
       "schema" -> schema.schemaName,
       "data" -> List(resultMap)
      )
      }
    }
  }
}
