package akkastream.primer.flows

import akka.NotUsed
import akka.stream.scaladsl.{Flow, GraphDSL,RunnableGraph,Sink, Source}
import scala.concurrent.Future
import akka.stream.{ClosedShape, Graph, IOResult, Materializer}

object StreamingGraph {
  def apply(source: Source[String, Future[IOResult]],
            downStreamWorkerFlow: Flow[String, String, NotUsed],
            sink: Sink[String, Future[IOResult]]): StreamingGraph =
    new StreamingGraph(source, downStreamWorkerFlow, sink)

  /**
   * Class [[StreamingGraph]] creates the graph by connecting Source, Flow and Sink together
   * and provides a method [[run]] to execute it
   * https://doc.akka.io/docs/akka/current/stream/stream-graphs.html
   * @param source
   * @param downStreamWorkerFlow
   * @param targetSink
   */

  class StreamingGraph(source: Source[String, Future[IOResult]],
                       downStreamWorkerFlow: Flow[String, String, NotUsed],
                       targetSink: Sink[String, Future[IOResult]]) {
    val streamingGraph = RunnableGraph.fromGraph(GraphDSL.create(targetSink) {
      implicit builder => sink =>
        import GraphDSL.Implicits._
        source ~> downStreamWorkerFlow.async ~> sink
        ClosedShape
    })

    /**
     * https://doc.akka.io/docs/akka/current/stream/stream-graphs.html
     * @param materializer
     * @return
     */
    def run (implicit materializer: Materializer) = streamingGraph.run()
  }
}
