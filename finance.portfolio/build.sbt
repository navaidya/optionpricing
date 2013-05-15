name := "finance.portfolio"

organization := "net.gadgil"

version := "0.1.0-SNAPSHOT"

publishTo := Some(Resolver.file("file",  new File( "./releases" )) )

//libraryDependencies += "org.osgi" % "org.osgi.core" % "4.3.0" % "provided"

osgiSettings

OsgiKeys.exportPackage := Seq("net.gadgil.finance.portfolio")


scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
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
	"*"
)

