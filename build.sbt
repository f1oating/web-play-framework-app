name := """webapp"""
organization := "com.f1oating"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.12"

libraryDependencies += guice

libraryDependencies ++= Seq(
  javaJdbc
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.33"
)