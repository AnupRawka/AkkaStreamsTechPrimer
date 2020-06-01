package akkastream.primer.sinks

import java.nio.file.Paths
import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Flow, Keep, Sink}
import akka.util.ByteString
import scala.concurrent.Future

/**
 * [[FileWriterSink()]] provides a Sink that takes a msg of type String as an input and writes
 * to an output file
 * https://doc.akka.io/docs/akka/current/stream/stream-flows-and-basics.html
 */
object FileWriterSink {
  def apply(targetFileName: String): Sink[String, Future[IOResult]] = {
    {
      Flow[String].map(
        str => ByteString(str + "\n"))
        .toMat(FileIO.toPath(Paths.get(targetFileName))) (Keep.right)
    }
  }
}
