SonarQube Java Properties Plugin
====================

[![Build Status](https://api.travis-ci.org/racodond/sonar-jproperties-plugin.svg)](https://travis-ci.org/racodond/sonar-jproperties-plugin)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=org.codehaus.sonar-plugins.jproperties:jproperties)](https://nemo.sonarqube.org/overview?id=org.codehaus.sonar-plugins.jproperties%3Ajproperties)

Plugin versions and compatibility with SonarQube versions: [http://docs.sonarqube.org/display/PLUG/Plugin+Version+Matrix](http://docs.sonarqube.org/display/PLUG/Plugin+Version+Matrix)

## Description
This plugin enables code QA analysis of [Java Properties files](https://en.wikipedia.org/wiki/.properties) within [SonarQube](http://www.sonarqube.org):

 * Computes metrics: lines of code, comments lines, etc.
 * Performs more than [20 checks](#available-checks)
 * Provide the ability to write your own checks

## Usage
1. [Download and install](http://docs.sonarqube.org/display/SONAR/Setup+and+Upgrade) SonarQube
2. Install the Java Properties plugin either by a [direct download](https://github.com/racodond/sonar-jproperties-plugin/releases) or through the [Update Center](http://docs.sonarqube.org/display/SONAR/Update+Center).
3. [Install your favorite analyzer](http://docs.sonarqube.org/display/SONAR/Analyzing+Source+Code#AnalyzingSourceCode-RunningAnalysis) (SonarQube Scanner, Maven, Ant, etc.) and analyze your code. Note that starting at version 2.0, Java 8 is required to run an analysis.

## Notes

 * The following kind of error may indicate that:
  * You did not properly set the `sonar.sourceEncoding` property. Prior to version 2.0, only ISO-8859-1 was supported.
  * Or that the last line of your file is missing a line ending character (LF / CR / CRLF)

``` 
Caused by: java.lang.IllegalArgumentException: Unable to highlight file \[moduleKey=xxx, relative=xxx, basedir=xxx\] from offset 808 to offset 876
at org.sonar.api.batch.sensor.highlighting.internal.DefaultHighlighting.highlight(DefaultHighlighting.java:85)
at org.sonar.batch.source.DefaultHighlightable$DefaultHighlightingBuilder.highlight(DefaultHighlightable.java:79)
at org.sonar.jproperties.ast.visitors.SyntaxHighlighterVisitor.visitNode(SyntaxHighlighterVisitor.java:93)
at com.sonar.sslr.impl.ast.AstWalker.visitNode(AstWalker.java:114)
...
```

## Available Checks

####Checks

 * "FIXME" tags should be handled
 * "TODO" tags should be handled
 * All comments should be formatted consistently
 * All property definitions should start at column 1
 * Byte Order Mark (BOM) should not be used for UTF-8 files
 * Credentials should not be hard-coded
 * Different keys having the same value should be merged
 * Duplicated keys across files should be removed
 * Duplicated keys should be removed
 * End-line characters should be consistent
 * File names should comply with a naming convention
 * Files not defining any properties should be removed
 * Files should contain an empty new line at the end
 * Keys should follow a naming convention
 * Lines should not be too long
 * Number of keys should be reduced
 * Property with empty value should be removed
 * Sections of code should not be commented out
 * Separators should follow a convention
 * Tabulation characters should not be used

#### Templates

 * Regular expression on comment
 * Regular expression on key
 * Regular expression on value


## Custom Checks

You're thinking of new valuable rules? Version 2.1 or greater provides an API to write your own custom checks.
A sample plugin with detailed explanations is available [here](https://github.com/racodond/sonar-jproperties-custom-rules-plugin).
If your custom rules may benefit the community, feel free to create a pull request in order to make the rule available in the Java Properties plugin.

You're thinking of new rules that may benefit the community but don't have the time or the skills to write them? Feel free to create an [issue](https://github.com/racodond/sonar-jproperties-plugin/issues) for your rules to be taken under consideration.
