import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "openoptions"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
      "org.apache.commons" % "commons-math3" % "3.0",
    jdbc,
    anorm
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
      resolvers += "JBoss repository" at "https://repository.jboss.org/nexus/content/repositories/"
  )

}
