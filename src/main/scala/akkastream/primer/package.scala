package akkastream

package object primer {
  val DEFAULT_MAX_FRAME_LENGTH = 1024 * 100

  case class Fields(name: String, fieldType: String, encrypt: String)
  case class Desc (schemaName: String, version: String, fields: List[Fields])
}
