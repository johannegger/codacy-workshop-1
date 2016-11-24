name := """ex0-macros"""

version := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.8"

lazy val root = project.in(file("."))
  .dependsOn(macros)

lazy val macros = project.in(file("macros"))
  .dependsOn(common)
  .settings(libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value)

lazy val common = project.in(file("common"))