name := "omnicart-data-prep"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.2.0"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

// https://mvnrepository.com/artifact/net.liftweb/lift-json
libraryDependencies += "net.liftweb" %% "lift-json" % "3.3.0"

//libraryDependencies += "org.apache.hadoop" % "hadoop-core" % "1.2.1"

// https://mvnrepository.com/artifact/org.apache.hbase/hbase-client
//libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.4.7"
libraryDependencies += "org.apache.hbase" % "hbase-client" % "2.1.0"

// https://mvnrepository.com/artifact/org.apache.hbase/hbase-common
//libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.4.7"
libraryDependencies += "org.apache.hbase" % "hbase-common" % "2.1.0"

//libraryDependencies += "com.hortonworks" %% "shc" % "1.1.3-2.3-s_2.11"
//libraryDependencies += "com.hortonworks" %% "shc-core" % "1.1.1-2.1-s_2.11"
// https://mvnrepository.com/artifact/org.apache.phoenix/phoenix-core
//libraryDependencies += "org.apache.phoenix" % "phoenix-core" % "4.7.0-HBase-1.1"
//libraryDependencies += "org.apache.hbase" %% "hbase-spark" % "1.2.0-cdh5.7.0"

// https://mvnrepository.com/artifact/org.apache.hbase/hbase-mapreduce
//libraryDependencies += "org.apache.hbase" % "hbase-mapreduce" % "2.1.0"

// https://mvnrepository.com/artifact/org.apache.hbase/hbase-server
//libraryDependencies += "org.apache.hbase" % "hbase-server" % "1.1.2"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.7"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.7"

//libraryDependencies += "it.nerdammer.bigdata" % "spark-hbase-connector_2.10" % "1.0.3"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}