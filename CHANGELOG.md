## Change log
----------------------

Version 10-SNAPSHOT
-------------

BREAKING:

- Java 25 is the minimum, up from 17: the family builds, tests and releases on JDK 25
  (astrapi69/mystic-crypt#234), so the next release is 10 instead of 9.3 (8ec0895)

ADDED:

- a test of `StringExtensions` for the Unicode conversion of the em dash, U+2014 (95d7b38)

CHANGED:

- the Gradle wrapper is 9.8.2, up from 8.10.2, and every dependency and plugin is on its latest release (95d7b38,
  8ec0895); commons-lang3 3.18.0 (dfcdc3c)
- the workflows build on exactly the JDK `.sdkmanrc` names, Temurin 25.0.4+7, and check that they do; they set up
  JDK 17 while the toolchain fetched 25 (#12)
- the grgit and hierynomus license plugins are gone: `tagRelease` runs git itself (`gradle/tagging.gradle`), and
  Spotless writes the MIT header from `src/main/resources/license-header.txt`, which 11 of the 13 Java files besides
  package-info carried and the 2 test classes `CaseExtensionsTest` and `CaseExtensionsParameterizedTest` now carry
  as well. The two plugins brought the old `spring-core` and `plexus-utils` of the 11 Dependabot alerts on the
  build classpath; none of them was at runtime (#12)
- a test class that leaves no result fails the build (#10)
- releases go to Maven Central through the Central Portal: the nmcp settings plugin uploads, `USER_MANAGED`, from
  `publish.yml` on a `RELEASE-*` tag, signed by the release subkey; a release without a key, an upload from a local
  tree and a release from a branch are refused. OSSRH, which Sonatype shut down in June 2025, its repository and
  its credentials are gone, and snapshots resolve from the Central Portal's snapshot repository (#13)
- a build of the same commit gives the same bytes: the manifest no longer carries the build time, the operating
  system and the JDK's patch level (`Build-Jdk-Spec: 25` in their place); `javac --release 25` keeps the patch
  level out of `module-info.class`; every archive has fixed entry times and a fixed entry order; the javadoc is
  written in English whatever the locale of the machine; and the local Maven repository is used only with
  `-PuseMavenLocal`, since it came before Maven Central. `scripts/check-reproducible.sh` builds the five published
  artifacts twice and compares them (#14)
- mutation testing with PIT: the CI build fails below 96% of the mutants detected and publishes the score as a
  badge; PIT detects 181 of the 185 mutants, 98%, up from 160, and the 4 left are equivalent, as `build.gradle`
  says. The build also fails below 99% of the lines or 98% of the branches, what the tests reach: 330 of 331 lines
  and 137 of 139 branches. The tests the survivors showed to be missing check, among others, the parts of
  `getTripleFromMessage`, which its test only printed (#15)
- the Gradle dependency graph is submitted to GitHub, for Dependabot (#1)
- the README replaces its dead badges and links (#8), names the family's funding channels and no cryptocurrency
  addresses (#6), and `/gradle` is committed, no longer ignored (#4)

FIXED:

- `StringExtensions.readLine` returns a string without a line break as it is, the one line it holds, where it threw
  a `StringIndexOutOfBoundsException` (#20)


Version 9.2
-------------

ADDED:

- new test dependency junit-jupiter-params in new version 5.11.2 for enable ParameterizedTest feature

CHANGED:

- rename of module to new name 'io.github.astrapisixtynine.silly.strings'
- update of gradle to new version 8.10.2
- update of test dependency file-worker to new version 17.4
- dependencies are managed now over bundles

Version 9.1
-------------

ADDED:

- new libs.versions.toml file for new automatic catalog versions update
- new dependency commons-text in version 1.12.0
- new extension class CaseExtensions that provides methods for converting strings between different case formats

CHANGED:

- update of gradle to new version 8.8
- update of gradle-plugin dependency 'com.github.ben-manes.versions.gradle.plugin' to new version 0.51.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.2.2
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 7.0.0.BETA1
- update of test dependency file-worker to new version 17.2
- update of test dependency junit-jupiter to new version 5.11.0-M2
- update of dependency silly-collection in new version 27.1
- update of test dependency commons-collections4 to new version 4.5.0-M2
- update of test dependency test-object to new version 8.2

Version 9
-------------

ADDED:

- new extension class CharsetExtensions

CHANGED:

- update to jdk version 17
- update of gradle to new version 8.4
- update of gradle-plugin dependency 'com.github.ben-manes.versions.gradle.plugin' to new version 0.49.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.2.0
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 6.22.0
- update of dependency commons-lang to new version 3.13.0
- update of dependency silly-collection in new version 27
- update of test dependency commons-io to new version 2.14.0
- update of test dependency file-worker to new version 17
- update of test dependency test-object to new version 8
- update of test dependency comparator-extensions to new version 1.5
- update of test dependency meanbean to new version 3.0.0-M9
- replace of test dependency testng with junit-jupiter in version 5.10.0


Version 8.2
-------------

ADDED:

- new gradle-plugin dependency of 'org.ajoberstar.grgit:grgit-gradle' in version 5.0.0 for create
  git release tags
- new gradle-plugin dependency of 'com.diffplug.spotless:spotless-plugin-gradle' in version 6.11.0
  for format the source files
- Factory method for create new ArrayList of unique characters from the given text sorted with the natural order
- new method for create a String from a List of characters
- new file module-info.java

CHANGED:

- update to jdk version 11
- update gradle to new version 7.5.1
- removed class StringOutputStream
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.42.0
- update of dependency silly-collection in new version 20.1
- update of dependency silly-bean in new version 2
- update of test dependency test-object to new version to 7.1

Version 8.1
-------------

ADDED:

- Factory method for create new ArrayList of unique characters from the given text sorted with the given Comparator

Version 8
-------------

ADDED:

- new method that gets a List of all unique characters from the given String object

CHANGED:

- update gradle to new version 7.2
- update gradle-plugin dependency of gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin to new version
  0.16.1
- update of test dependency test-objects to new version to 5.5
- update of test dependency commons-io to new version to 2.11.0
- update of test dependency file-worker to new version 5.10

Version 5.6
-------------

ADDED:

- new extension class for split string objects

CHANGED:

- update of gradle to new version 6.9
- changed to new package io.github.astrapi69
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.39.0
- update of dependency commons-lang3 in new version 3.12.0
- update of dependency silly-collections in new version 8.7
- update of test dependency testng to new version 7.4.0
- update of test dependency test-objects to new version to 5.4
- update of test dependency commons-io to new version to 2.10.0
- moved test dependency silly-beans to dependencies
- update of dependency silly-beans in new version 1.2

Version 5.5
-------------

ADDED:

- new build system gradle

CHANGED:

- removed maven build system and all related files
- removed all lombok dependent imports

Version 5.4
-------------

ADDED:

- new method created that removes the first and the last characters of a string object

CHANGED:

- update of parent version to 5.5
- update of silly-collections version to 5.6
- update of test-objects version to 5.2

Version 5.3.1
-------------

CHANGED:

- update of silly-collections version to 5.2.1

Version 5.3
-------------

ADDED:

- new method created that filter a given list of strings with a given separator

CHANGED:

- moved class RegExExtensions from jcommons-lang project to this project
- moved class StringOutputStream from jcommons-lang project to this project
- update of commons-lang3 version to 3.9

Version 5.2
-------------

ADDED:

- this changelog file
- created PULL_REQUEST_TEMPLATE.md file
- created CODE_OF_CONDUCT.md file
- created CONTRIBUTING.md file
- provide package.html for the javadoc of packages
- moved classes from obsolet jcommons-lang project
