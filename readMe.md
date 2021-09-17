# Batch Processsing with Akka Streams
Overview:
Akka Streams is a toolkit for event based processing of streaming data using bounded buffer space. It is an actor based implementation of reactive streams specifications. It not only provides out of the box back-pressuring but also promises type safety at every stage of the streamed pipeline.
The main goal of Reactive Streams is to govern the exchange of stream data across an asynchronous boundary (i.e. between producer and consumer in below Flow) — think of it as passing elements on to another thread or thread-pool while ensuring that the receiving side is not forced to buffer arbitrary amounts of data (example, more than N messages). In other words, back pressure is an integral part of this model in order to allow the queues which mediate between threads to be bounded.

## What you'll build
Akka streams provides both Java and Scala based API, for this primer we will be using Scala. This Primer
will walk you through the steps to build a simple streaming Data Transformation and processing flow. The primer shows an example
of processing CSV data through file.
It takes an CSV data, converts it to Json record based on provided schema and write it back to a file. I tried
to document every function and concept.

## What you'll need
* About 60 mins
* Favorite IDE (I prefer Intellij)
* Scala 2.11+, Akka Streams 2.6.5+.
* Basic undersating of Scala.
* Please check build.sbt to find all the dependencies of tech stack used in this primer.

I reccomend going through the blog [Akka Streams: Our Journey for Fast Data Processing and Tech Primer](https://anuprawka.medium.com/?p=e87d76c9dfca) and below mentioned references before getting started with the primer.

## Getting started:

If you are new to Scala and SBT please follow [this] (https://docs.scala-lang.org/getting-started/intellij-track/building-a-scala-project-with-intellij-and-sbt.html) guide to setup your intellij for Scala and SBT project. You can also go thorugh few basic level courses on Scala on any of the online leanring platform.

In this primer we will take a look into the basics concepts of Akka streams Source, Flow and Sink. We will also look into how to connect all 3 to create a working graph. We will use a CSV dataset as our source and create a Flow which will convert CSV to JSON data and use Akka Streams Sink to write back json message to a file. We are using a static data source for our example howeve we can easily change the Graph to use Kafka as a source and Sink and convert it to an streaming graph. 

The test data file is kept in `resources` package.Once the project compiles successfully you should be able to see below structure (note the color of resources and scala package). 

![Project Structure](https://user-images.githubusercontent.com/8251662/133840041-9a3293fc-8bd3-4c3c-b802-3178dd7d81ee.png)

Please follow the primer launcher object /src/main/scala/akkastream/primer/AkkaStreamsPrimerLauncher.scala to get started. Code is very well documented and self directed. 

Please comment below if you ahve any issues using the primer or any suggesstions.



## Refrences
* [Getting started with Akka Streams](https://stackoverflow.com/questions/35120082/how-to-get-started-with-akka-streams)
* [A journey into Akka Streams] (https://blog.redelastic.com/a-journey-into-reactive-streams-5ee2a9cd7e29?gi=6a8e997f29ea)
* [Akka streams official Doc] (https://doc.akka.io/docs/akka/current/stream/stream-introduction.html)
* [Why we picked Akka streams] (https://tech.iheart.com/why-we-picked-akka-cluster-as-our-microservice-framework-bbf3019a3217)
* [The introduction to Reactive Programming you've been missing](https://gist.github.com/staltz/868e7e9bc2a7b8c1f754/)
* [Reactive Streams] (https://developer.lightbend.com/docs/akka-platform-guide/concepts/reactive-streams.html)
