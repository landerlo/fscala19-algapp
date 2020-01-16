# fscala19-algapp

Algebraic application demo for Functional Scala 2019

Demonstrates the potential of Union types if we adopt a more flexible
application that applies to disjunctions if any of the branches could be
applied by the function. I called this algebraic application.

The slides are worksheets that can be executed in VSCode with the dotty
extension.

The code in this demo requires a fix for dotty issue [#4867](https://github.com/lampepfl/dotty/issues/4867)

I have sent a [PR](https://github.com/lampepfl/dotty/pull/7829) to fix this but it hasn't been approved so a version with this PR needs to be used.

### HOW-TO Build and publish a local patched Dotty version

1. git clone https://github.com/landerlo/dotty.git

2. git checkout fix-#4867-versioned

3. sbt dotty-bootstrapped/publishLocal

### HOW-TO launch worksheets in dotty to reproduce demo or play with the code
1. git clone https://github.com/landerlo/fscala19-algapp.git

1. cd fscala19-algapp

2. sbt launchIDE

This will launch VSCode with the patched dotty version
The slides are the worsheets in the slides folder.

VSCode will show a 'Run this worksheet' button on top of each worksheet.
