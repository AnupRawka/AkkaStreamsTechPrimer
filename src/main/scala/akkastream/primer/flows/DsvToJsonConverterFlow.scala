package akkastream.primer.flows

import akka.NotUsed
import akka.stream.scaladsl.Flow
import akkastream.primer.util.DsvToJsonConverter.DsvToJsonConverter

object DsvToJsonConverterFlow {

  def apply(converter: DsvToJsonConverter): Flow[String, String, NotUsed] ={
    Flow [String] map {
      msg => converter.convert(msg)
    }

  }
}
