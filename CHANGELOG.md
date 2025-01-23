## Change log
----------------------

Version 9.3-SNAPSHOT
-------------

## [Unreleased]

### Added
- **Tests**: Added a new test case in `StringExtensionsTest` for validating Unicode conversion of the Em Dash (U+2014) character.

### Changed
- **Gradle Wrapper**: Upgraded from version `8.10.2` to `8.12` for improved performance and compatibility.
- **Dependencies**: Updated the following dependencies in `libs.versions.toml`:
  - `commons-collections4` updated to `4.5.0-M3`
  - `commons-io` updated to `2.18.0`
  - `commons-text` updated to `1.13.0`
  - `file-worker` updated to `19.0`
  - `junit-jupiter`, `junit-jupiter-params`, and `junit-platform-launcher` updated to `5.11.4`
  - Gradle plugins:
    - Spotless plugin updated to `7.0.2`
    - Version catalog update plugin updated to `0.8.5`
    - Gradle versions plugin updated to `0.52.0`
- **Test Enhancements**: Improved `StringExtensionsTest` with additional cases for Unicode character conversion.

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
