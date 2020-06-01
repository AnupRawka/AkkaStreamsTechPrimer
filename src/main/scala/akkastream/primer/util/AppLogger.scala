package akkastream.primer.util

import org.slf4j.{LoggerFactory,Logger}
trait AppLogger {
 @transient protected lazy val logger: Logger = LoggerFactory.getLogger(getClass.getName)
}
