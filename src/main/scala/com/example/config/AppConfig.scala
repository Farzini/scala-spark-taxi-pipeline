package com.example.config

import pureconfig._
import pureconfig.generic.auto._

final case class AppSection(
  input_path: String,
  output_path: String,
  partitions: List[String],
  sample: Boolean
)
final case class SparkSection(master: String, app_name: String)
final case class Root(app: AppSection, spark: SparkSection)

object AppConfig {
  def load(): Root = pureconfig.ConfigSource.default.loadOrThrow[Root]
}

