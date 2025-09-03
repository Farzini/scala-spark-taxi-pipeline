# Scala–Spark Taxi Pipeline (Starter)

A minimal, production-leaning **Scala + Apache Spark** project you can use as a portfolio piece.

## Features
- ETL pipeline (extract → clean → enrich → write Parquet)
- Config-driven (`conf/application.conf`) via PureConfig
- Simple data quality check
- KPIs aggregation
- Unit tests with ScalaTest
- Ready for fat-jar (`sbt-assembly`) and optional Docker

## Quickstart
```bash
# 1) Run tests and app (Spark local mode)
sbt test
sbt run

# 2) Build fat-jar and run with spark-submit
sbt assembly
spark-submit --class com.example.Main target/scala-2.12/scala-spark-taxi-pipeline-assembly.jar
```

## Data
Download a few **NYC Yellow Taxi** CSVs into `data/raw/nyc_taxi/`.
(Or swap in any CSVs with pickup/dropoff timestamps & fares — adjust columns accordingly.)

## Config
Edit `conf/application.conf`:
```hocon
app {
  input_path = "data/raw/nyc_taxi/*.csv"
  output_path = "data/curated/nyc_taxi_parquet"
  partitions = ["year","month"]
  sample = false
}
spark {
  master = "local[*]"
  app_name = "TaxiPipeline"
}
```

## Next steps
- Replace quality checks with Great Expectations or custom rules
- Write to Delta Lake and demonstrate partition pruning
- Add GitHub Actions CI for tests
- Optional tiny REST API to serve KPIs
