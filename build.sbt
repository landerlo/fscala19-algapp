// Dotty master won't work because this demo requires the fix for issue #4867
// https://github.com/lampepfl/dotty/issues/4867
// I have a PR for this, but it hasn't been merged so in the meantime a patched dotty version is required
// The README.md includes the instructions on how to build the version.
//
// PR: https://github.com/lampepfl/dotty/pull/7829
val dottyVersion = "0.22.0-fix4867-bin-SNAPSHOT"

lazy val root = project
  .in(file("."))
  .settings(
    name := "algae",
    version := "0.1.0",

    scalaVersion := dottyVersion,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
