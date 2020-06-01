package akkastream.primer

import akka.stream.scaladsl._
import akka.util.ByteString
import akkastream.primer.flows.{DsvToJsonConverterFlow, StreamingGraph}
import akkastream.primer.sinks.FileWriterSink
import akkastream.primer.util.{AppLogger, InitHelper}

object AkkaStreamsPrimerLauncher extends InitHelper with AppLogger{
  def main(args: Array[String]): Unit = {
    /**
     * A source stage with exactly one output, emitting records whenever
     * downstream processing stages are ready to receive them.
     * https://doc.akka.io/docs/akka/current/stream/stream-flows-and-basics.html
     */
    val fileSource = FileIO.fromPath(resourcesPath).via (Framing.delimiter(ByteString("\n"),
      maximumFrameLength = DEFAULT_MAX_FRAME_LENGTH, allowTruncation = true))
      .map(_.toString)
    logger.info("File source created, initializing the flow now ...")
    val dsvToJsonFlow = DsvToJsonConverterFlow(converter)

    logger.info("Successfully created flow, initializing the sink now")
    val sink = FileWriterSink("fl_insurance_json.json")

    logger.info("Successfully created sink, creating graph now...")
    val streamingClient = StreamingGraph(fileSource, dsvToJsonFlow, sink)

    import system.dispatcher
    val startTime = System.currentTimeMillis()
    logger.info(s"Start time is : $startTime")
    logger.info("Executing graph now")
    val futureOfRun = streamingClient.run(materializer)

    futureOfRun.onComplete{
      val endTime = System.currentTimeMillis()
      logger.info(s"Total time take is: ${endTime - startTime}")
          logger.info("Cleaning up the resources now..")
      _ => system.terminate()
    }


  }
}
