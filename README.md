# Overview

<div style="text-align: center">

[![Build Status](https://travis-ci.com/astrapi69/silly-strings.svg?branch=develop)](https://travis-ci.com/astrapi69/silly-strings)
[![Coverage Status](https://coveralls.io/repos/github/astrapi69/silly-strings/badge.svg?branch=develop)](https://coveralls.io/github/astrapi69/silly-strings?branch=develop)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/silly-strings.svg?style=flat)](https://github.com/astrapi69/silly-strings/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/silly-strings/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/silly-strings)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/silly-strings.svg)](http://www.javadoc.io/doc/io.github.astrapi69/silly-strings)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

</div>

Utility library that provides utility classes for the java core class String

# Support this project

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
<a class="github-button" href="https://github.com/astrapi69/silly-strings" data-icon="octicon-star" aria-label="Star astrapi69/silly-strings on GitHub">
> Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this
> project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
>
> or for more donation options go the [donations section](#Donations)

## Import dependencies to your project

<details>
  <summary>gradle (click to expand)</summary>

## gradle dependency

Replace the variable ${latestVersion} with the current latest version: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/silly-strings/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/silly-strings)

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of silly-strings:

define version in file gradle.properties

```
sillyStringsVersion=${latestVersion}
```

or in build.gradle ext area

```
    sillyStringsVersion = "${latestVersion}"
```

then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:silly-strings:$sillyStringsVersion")
```

# with new libs.versions.toml file

If you use the new libs.versions.toml file for new automatic catalog versions update

```
[versions]
silly-strings-version=${latestVersion}

[libraries]
silly-strings = { module = "io.github.astrapi69:silly-strings", version.ref = "silly-strings-version" }
```
then add the dependency to the dependencies area

```
    implementation libs.silly.strings
```
</details>

<details>
  <summary>Maven (click to expand)</summary>

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~silly-strings~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of silly-strings:

Then you can add the dependency to your dependencies:

    <properties>
        ...
```xml
        <!-- silly-strings version -->
        <silly-strings.version>${latestVersion}</silly-strings.version>
```
        ...
    </properties>
        ...
        <dependencies>
        ...
```xml
            <!-- silly-strings DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>silly-strings</artifactId>
                <version>${silly-strings.version}</version>
            </dependency>
```
        ...
        </dependencies>
</details>


<details>
  <summary>Snapshots (click to expand)</summary>

## 📸 Snapshots

[![Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://oss.sonatype.org/service/local/repositories/snapshots/content/io/github/astrapi69/silly-strings/maven-metadata.xml&label=snapshot&color=red&query=.//versioning/latest)](https://oss.sonatype.org/content/repositories/snapshots/io/github/astrapi69/silly-strings/)

This section describes how to import snapshot versions into your project.
Add the following code snippet to your gradle file in the repositories section:
```
repositories {
   //...
```
```groovy
    maven {
        name "Sonatype Nexus Snapshots"
        url "https://oss.sonatype.org/content/repositories/snapshots"
        mavenContent {
            snapshotsOnly()
        }
    }
```
```
}
```
</details>

## Support this project

If silly-strings is useful to you, a star on [GitHub](https://github.com/astrapi69/silly-strings) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making silly-strings great for all types of applications.

## Semantic Versioning

The versions of silly-strings are maintained with the Simplified Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning for this project you can visit
this [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Simplified-Semantic-Versioning).

## Want to Help and improve it? ###

The source code for silly-strings are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/silly-strings/fork](https://github.com/astrapi69/silly-strings/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/silly-strings/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the silly-strings developers with your questions, concerns, comments, bug reports, or feature
requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/silly-strings/issues).

## Credits

|                                                           **Travis CI**                                                           |
|:---------------------------------------------------------------------------------------------------------------------------------:|
|                 [![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)](https://travis-ci.com)                 |
| Special thanks to [Travis CI](https://travis-ci.com) for providing a free continuous integration service for open source projects |
|                                                         <img width=1000/>                                                         |

|                                                                                                   **Nexus Sonatype repositories**                                                                                                    |
|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| [![sonatype repository](https://img.shields.io/nexus/r/https/oss.sonatype.org/io.github.astrapi69/silly-strings.svg?style=for-the-badge)](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~silly-strings~~~) |
|                                               Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects                                               |
|                                                                                                          <img width=1000/>                                                                                                           |

|                                                                               **coveralls.io**                                                                               |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| [![Coverage Status](https://coveralls.io/repos/github/astrapi69/silly-strings/badge.svg?branch=develop)](https://coveralls.io/github/astrapi69/silly-strings?branch=develop) |
|                              Special thanks to [coveralls.io](https://coveralls.io) for providing a free code coverage for open source projects                              |
|                                                                              <img width=1000/>                                                                               |

|                                                                **javadoc.io**                                                                 |
|:---------------------------------------------------------------------------------------------------------------------------------------------:|
| [![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/silly-strings.svg)](http://www.javadoc.io/doc/io.github.astrapi69/silly-strings) |
|           Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects           |
|                                                               <img width=1000/>                                                               |
