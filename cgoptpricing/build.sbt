name := "cgoptpricing"

organization := "net.gadgil"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.1"

organization := "net.gadgil.cgoptprice"

name := "options.pricing"

version := "0.0.1"

//libraryDependencies += "org.osgi" % "org.osgi.core" % "4.3.0" % "provided"

osgiSettings

OsgiKeys.exportPackage := Seq("net.gadgil.cgoptprice")

//OsgiKeys.bundleActivator := Option("com.typesafe.sbt.osgidemo.internal.Activator")

libraryDependencies ++= Seq(
	"org.osgi" % "org.osgi.core" % "4.3.0" % "provided",
	"org.apache.felix" % "org.apache.felix.framework" % "4.0.3" % "runtime",
      "org.apache.commons" % "commons-math3" % "3.0",
  "junit" % "junit" % "4.11" % "test",
  "org.specs2" %% "specs2" % "1.14" % "test",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

OsgiKeys.additionalHeaders := Map(
	"Service-Component" -> "*",
	"Conditional-Package" -> "scala.*"
)

