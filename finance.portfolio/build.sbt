name := "finance.portfolio"

organization := "net.gadgil"

version := "0.1.0-SNAPSHOT"

publishTo := Some(Resolver.file("file",  new File( "./releases" )) )

//libraryDependencies += "org.osgi" % "org.osgi.core" % "4.3.0" % "provided"

osgiSettings

OsgiKeys.exportPackage := Seq("net.gadgil.finance.portfolio.*")

resolvers += "mvnepository" at "http://mvnrepository.com/artifact"

scalaVersion := "2.10.1"

useGpg := true


libraryDependencies ++= Seq(
   "joda-time" % "joda-time" % "2.2",
   "net.sf.supercsv" % "super-csv" % "2.1.0",
   "com.ning" % "async-http-client" % "1.7.15",
   "org.apache.camel" % "camel-http4" % "2.11.0",
   "org.joda" % "joda-convert" % "1.3.1",
  "junit" % "junit" % "4.11" % "test",
  "org.specs2" %% "specs2" % "1.14" % "test",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

OsgiKeys.additionalHeaders := Map(
	"Service-Component" -> "*",
	"Conditional-Package" -> "scala.*"
)

OsgiKeys.importPackage := Seq(
	"sun.misc;resolution:=optional",
	"!aQute.bnd.annotation.*", 
	"org.apache.camel.*", 
	"*"
)

scalacOptions += "-feature" 

scalacOptions += "-deprecation" 

