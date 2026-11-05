# silly-strings

**Java helpers for strings: unicode escapes both ways, replacing and splitting, the case of identifiers,
lines and words, regular expressions with wildcards, and charsets by name.**

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/silly-strings/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/silly-strings/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/silly-strings/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/silly-strings)
[![Mutation Score](https://img.shields.io/endpoint?url=https%3A%2F%2Fraw.githubusercontent.com%2Fastrapi69%2Fsilly-strings%2Fgh-pages%2Fbadges%2Fmutation-score-develop.json)](https://pitest.org/)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/silly-strings.svg?style=flat)](https://github.com/astrapi69/silly-strings/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/silly-strings.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.astrapi69/silly-strings)
[![Javadoc](https://www.javadoc.io/badge/io.github.astrapi69/silly-strings.svg)](https://www.javadoc.io/doc/io.github.astrapi69/silly-strings)
[![MIT license](https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-25-orange)](https://openjdk.org/projects/jdk/25/)

</div>

silly-strings collects the operations on strings that the libraries of the
[astrapi69](https://github.com/astrapi69) family need again and again: file-worker resolves an encoding name
to its charset with it, crypt-data splits a public key into the 64-character lines of PEM. It is small, two
packages, and builds on [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) and
[Apache Commons Text](https://commons.apache.org/proper/commons-text/).

## Why silly-strings

Each of its methods is a few lines that otherwise get written again: escaping a string as a properties file
does, or every character as `\uxxxx`, and reading such an escape back; splitting a string into parts of a fixed
length; turning `silly-strings-version` into `sillyStringsVersion`, or `silly-strings` into `SILLY_STRINGS`;
counting the words of a text; turning `*` and `?` into the wildcards of a regular expression or of SQL. The tests
cover 99.7% of the lines and 99.4% of the branches, and detect 98% of the mutants PIT makes; the build fails below
99%, 98% and 96%
([Quality](#quality)).

If the JDK covers what you need, use it: `String.replace`, `String.split`, `String.strip` and
`String.repeat` need no dependency. For much more than silly-strings has, Apache Commons Lang (`StringUtils`)
and Apache Commons Text (`CaseUtils`, `StringEscapeUtils`, `WordUtils`) are the better choice; silly-strings
uses both itself.

## Table of contents

- [Why silly-strings](#why-silly-strings)
- [Requirements](#requirements)
- [Installation](#installation)
- [Quick start](#quick-start)
- [Features](#features)
- [Quality](#quality)
- [Verifying the release signatures](#verifying-the-release-signatures)
- [Releasing](#releasing)
- [Contributing](#contributing)
- [License](#license)
- [Support this project](#support-this-project)
- [Credits](#credits)

## Requirements

**JDK 25 or newer** from version 10 on, at build and at runtime: the jar holds Java 25 bytecode and does not load
on an older JVM. Up to 9.2 it needed Java 17. On the module path its name is
`io.github.astrapisixtynine.silly.strings`.

silly-strings has four runtime dependencies, which Gradle and Maven fetch with it: commons-lang3 and commons-text
of Apache Commons, and silly-collection and silly-bean of the same maintainer. silly-bean is there only because
silly-collection's `MapFactory` takes and returns a type of it without declaring it for compiling against
([silly-collection#95](https://github.com/astrapi69/silly-collection/issues/95)). With their own dependencies
they put 7 jars on the runtime class path.

## Installation

Latest release on Maven Central: 10

10 has one breaking change against 9.2, which the [CHANGELOG](CHANGELOG.md#version-10) lists under BREAKING: it
needs Java 25 instead of 17. Its fixes change what a caller sees where a method did not do what its name or its
Javadoc said, for example `kebabToCamelCase`, which gave PascalCase, and `replaceAll` and `splitByFixedLength`,
which ran out of memory for an empty search string and a length of 0; the CHANGELOG names them under FIXED.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/silly-strings.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.astrapi69/silly-strings)

**Gradle** (version catalog, `gradle/libs.versions.toml`):

```toml
[versions]
silly-strings-version = "10"

[libraries]
silly-strings = { module = "io.github.astrapi69:silly-strings", version.ref = "silly-strings-version" }
```

```groovy
dependencies {
    implementation libs.silly.strings
}
```

**Gradle** (plain):

```groovy
dependencies {
    implementation "io.github.astrapi69:silly-strings:10"
}
```

**Maven**:

```xml
<dependency>
    <groupId>io.github.astrapi69</groupId>
    <artifactId>silly-strings</artifactId>
    <version>10</version>
</dependency>
```

<details>
  <summary>Snapshots (click to expand)</summary>

A snapshot of `develop` goes to the snapshot repository of the Central Portal when `publish.yml` runs by hand.
To use one, add the repository:

```groovy
repositories {
    maven {
        name = "Central Portal Snapshots"
        url = "https://central.sonatype.com/repository/maven-snapshots/"
        mavenContent {
            snapshotsOnly()
        }
    }
}
```

</details>

## Quick start

The examples are checked: each block runs in
[`ReadmeExamplesTest`](src/test/java/io/github/astrapi69/ReadmeExamplesTest.java), with the values its comments
name as assertions, and the test fails when a block here and there differ. Where a value holds a backslash, the
comment gives it as a Java string literal.

**Unicode escapes, both ways.** `toUnicodeChars` escapes as a properties file escapes a value: printable ASCII
stays, except that `=`, `:`, `#`, `!`, the backslash and a space at the start get a backslash; tab, line feed,
carriage return and form feed become `\t`, `\n`, `\r` and `\f`; every other character becomes `\uxxxx`.
`toUnicode` writes every character as an escape, one for each UTF-16 code unit, and
`convertUnicodeStringToCharacter` reads one back.

```java
String escaped = StringExtensions.toUnicodeChars("Größe = 1", false);
// "Gr\\u00F6\\u00DFe \\= 1", the escaping of a value in a properties file
String everyCharacter = StringExtensions.toUnicode("Ωmega", false);
// "\\u03A9\\u006D\\u0065\\u0067\\u0061", one escape for each character
Character omega = StringExtensions.convertUnicodeStringToCharacter("\\u03A9"); // 'Ω'
```

**Replacing and splitting.** `replaceAll` replaces literally, as `String.replace` does; `splitByFixedLength`
leaves the rest in the last part and refuses a length below 1; `readLine` ends at the line separator, a line feed
or a carriage return, and returns a string without one as it is.

```java
String dashed = StringExtensions.replaceAll("a, b, c", ", ", "-");      // a-b-c
String last = StringExtensions.replaceLast("one-two-three", "-", "+");  // one-two+three
List<String> parts = StringExtensions.splitByFixedLength("abcdefg", 3); // [abc, def, g]
String firstLine = StringExtensions.readLine("first\nsecond");          // first
```

**The case of identifiers.** From kebab-case to camelCase, UPPER_SNAKE_CASE and dot.case, and from snake_case to
PascalCase.

```java
String camel = CaseExtensions.kebabToCamelCase("silly-strings-version"); // sillyStringsVersion
String constant = CaseExtensions.kebabToUpperSnakeCase("silly-strings"); // SILLY_STRINGS
String pascal = CaseExtensions.snakeToPascalCase("silly_strings");       // SillyStrings
String property = CaseExtensions.kebabToDotCase("silly-strings");        // silly.strings
```

**Words, patterns and charsets.** `splitToWordsAndCount` counts the words of a text, sorted;
`wildcardCriterionSQL` turns `*` and `?` into the `%` and `_` of SQL's `LIKE`; `getCharset` answers empty for a
name it does not know, instead of throwing as `Charset.forName` does.

```java
Map<String, Integer> counts = SplitStringExtensions.splitToWordsAndCount("to be or not to be");
// {be=2, not=1, or=1, to=2}
int matches = RegExExtensions.countMatches("o", "foo boo");               // 4
String sqlPattern = RegExExtensions.wildcardCriterionSQL("Mül*er?");      // Mül%er_
Optional<Charset> utf8 = CharsetExtensions.getCharset("UTF-8");           // Optional[UTF-8]
Optional<Charset> none = CharsetExtensions.getCharset("no such charset"); // Optional.empty
```

## Features

| Class | What it holds |
|---|---|
| `io.github.astrapi69.string.StringExtensions` | unicode escapes (`toUnicode`, `toUnicodeChars`, `convertUnicodeStringToCharacter`); replacing (`replaceAll` with one, several or a map of search strings, `replaceLast`, `replaceEach` by a regular expression); splitting and lines (`splitByFixedLength`, `readLine`, `removeNewlineCharacters`, `removeEmptyString`, `toIntegerArray`); quotation marks (`addDoubleQuotationMarks`, `addSingleQuotationMarks`, `removeQuotationMarks`); the first and the last character (`firstCharacterToUpperCase`, `firstCharacterToLowerCase`, `lastCharacterToUpperCase`, `lastCharacter`, `removeFirstAndLastCharacter`); names with brackets or underscores (`getAttributName`, `getIndex`, `getItemNumber`, `getStringBeforeUnderscore`, `getStringAfterUnderscore`); checks (`isNullOrEmpty`, `isNumber`); characters and bytes (`newCharacterList`, `toString(List<Character>)`, `convertToBytearray`, `convertToCharArray`) |
| `io.github.astrapi69.string.CaseExtensions` | kebab-case to camelCase, dot.case, snake_case and UPPER_SNAKE_CASE, snake_case to PascalCase |
| `io.github.astrapi69.string.SplitStringExtensions` | the words of a text with their count, split at a space, by a regular expression or by search strings; a chat line `[date] name: message` as a `Triple` |
| `io.github.astrapi69.string.CharsetExtensions` | `getCharset`: the charset of a name, or empty |
| `io.github.astrapi69.string.MalformedUnicodeException` | what `convertUnicodeStringToCharacter` throws for a `\u` escape with a character that is no hex digit or with fewer than four, for a backslash at the end and for an empty string |
| `io.github.astrapi69.regex.RegExExtensions` | `countMatches`, `isMatching`, and `*` and `?` as the wildcards of a regular expression (`replaceWildcardsWithRE`) or of SQL (`wildcardCriterionSQL`) |

The [Javadoc](https://www.javadoc.io/doc/io.github.astrapi69/silly-strings) has the details.

## Quality

As of 10, measured on the tree of `RELEASE-10` with JDK 25, by `./gradlew test --rerun` and `./gradlew build`, and
for the mutation score by the mutation testing workflow on GitHub:

| | |
|---|---|
| tests | 103 in 7 test classes, none failing, none skipped |
| line and branch coverage (JaCoCo) | 338 of 339 lines (99.7%), 156 of 157 branches (99.4%); the build fails below 99% of the lines or 98% of the branches |
| mutation score (PIT 1.30.1) | 198 of 202 mutants detected (98%); the build fails below 96%, and `build.gradle` says why the 4 left are equivalent |
| javadoc | no warning; the build fails on one (`-Werror`), and the javadoc is written in English whatever the locale |
| reproducible build | two builds of the same commit give the same five artifacts, byte for byte (`make check-reproducible`) |

Two workflows run on every push to `develop` and `master` and on every pull request into them, on exactly the JDK
`.sdkmanrc` names: `gradle.yml` builds, runs the tests and checks the coverage, which it uploads to codecov.io;
`mutation-testing.yml` runs PIT, keeps its report as an artifact, lists the mutants left in the summary of the run
and publishes the mutation score. The two badges above show `develop`. PIT runs on GitHub only: it starts several
JVMs, and a mutant that makes a loop endless can take the memory of a workstation; `make pitest` starts the
workflow for the pushed branch and follows it.

## Verifying the release signatures

The releases on Maven Central are signed with the OpenPGP key

```text
5B2F A6B1 1E29 8BC0 9287  F423 D8C4 0351 8C49 CA75
```

From 10 on they are signed by its signing subkey

```text
6D67 F844 2A6F CC96 BD7C  1B5E 9FCF 7C97 10E2 BD8D
```

valid until 2028-10-09; 9.2 is signed by the primary key itself. The key, with the subkey, is on
keys.openpgp.org. Fetch it once, then verify the signature next to each file:

```sh
gpg --keyserver hkps://keys.openpgp.org --recv-keys 5B2FA6B11E298BC09287F423D8C403518C49CA75
gpg --verify silly-strings-10.jar.asc silly-strings-10.jar
```

gpg answers `Good signature` and prints the `Primary key fingerprint`, and for a release signed by the subkey the
`Subkey fingerprint`; both have to be the ones above. The warning that the key "is not certified with a trusted
signature" only says that you have not marked it as trusted in your own keyring.

## Releasing

A release leaves only from CI: `publish.yml` runs on a `RELEASE-*` tag, signs with the release subkey and uploads
to the Central Portal, where the maintainer publishes it. It reads the repository secrets
`CENTRAL_PORTAL_TOKEN_USERNAME`, `CENTRAL_PORTAL_TOKEN_PASSWORD`, `GPG_KEY_ID`, `GPG_PRIVATE_KEY` and
`GPG_PASSPHRASE`. In the order it is run:

1. **One pull request** with the CHANGELOG entry and `projectVersion` without `-SNAPSHOT`
   (`docs: changelog for X, and the version that carries it`), `make build` and the two workflows green on it, and
   `make check-reproducible` reporting the five artifacts identical.
2. **Tag the merge commit** with `make tag-release CONFIRM=yes`, which names it `RELEASE-X` and refuses a
   `-SNAPSHOT` version, and push the tag. A pushed tag is never moved or deleted; a failed run is fixed on the
   branch and re-run.
3. **Verify on Central**: the jar, pom, `.module`, `-sources.jar` and `-javadoc.jar`, their sha1, and
   `gpg --verify` on the `.asc`.
4. **GitHub release** from the CHANGELOG entry.
5. **`master` follows the release** by a merge of the tag: on `master`,
   `git merge --no-ff "RELEASE-X^{commit}" -m "Merge tag RELEASE-X into master"`. Before `master` is pushed,
   `git diff RELEASE-X master` has to be empty; if it is not, nothing is pushed. Never a force push: `master` is
   always the latest release, nothing else.
6. **Open the next cycle**: `projectVersion` to the next `-SNAPSHOT`.

## Contributing

Pull requests are welcome: fork [astrapi69/silly-strings](https://github.com/astrapi69/silly-strings/fork) and
[open a pull request](https://github.com/astrapi69/silly-strings/pull/new/develop) against `develop`.

Please add tests for your change; the coverage and mutation floors above hold for it too. The `Makefile` runs the
usual tasks (`make help` lists them):

```shell
make build      # clean build: tests, coverage check, formatting check
make spotless   # format, and write the license headers
make pitest     # mutation testing on GitHub for the pushed branch, fails below the floor
```

The versions follow the
[Simplified Semantic Versioning](https://github.com/lightblueseas/mvn-parent-projects/wiki/Simplified-Semantic-Versioning)
guidelines, `<major>.<minor>.<patch>` without the parts that are zero at the end, so 10 is 10.0.0 and 9.2 is
9.2.0. Questions, bug reports and feature requests belong on the
[issues page](https://github.com/astrapi69/silly-strings/issues).

## License

MIT - see [LICENSE](LICENSE).

## Support this project

If silly-strings is useful to you, a star on [GitHub](https://github.com/astrapi69/silly-strings) helps others
find it. Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which reads
[.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Credits

| | |
|---|---|
| [Maven Central](https://central.sonatype.com/artifact/io.github.astrapi69/silly-strings) | for hosting the artifacts of this open source project |
| [codecov.io](https://codecov.io) | for free code coverage reporting |
| [javadoc.io](https://www.javadoc.io) | for free javadoc hosting |

No animals were harmed in the making of this library.
