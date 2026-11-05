
# After the Makefile of astrapi69/silly-io (#36). No JDK is chosen here: the Gradle toolchain
# (build.gradle, java.toolchain, JDK 25 from gradle.properties projectSourceCompatibility) compiles,
# tests, runs PIT and writes the javadoc, so the JDK that runs Gradle only has to be one Gradle 9 runs
# on, 17 or later. check-reproducible is the exception: it builds on the JDK .sdkmanrc names, the one
# CI builds and releases with, since the javadoc differs between patch levels (#14)
GRADLE := ./gradlew

# the JDK .sdkmanrc names, as SDKMAN installs it
SDKMAN_JDK := $(HOME)/.sdkman/candidates/java/$(shell sed -n 's/^java=//p' .sdkmanrc)

# the version a release tag would carry
VERSION := $(shell sed -n 's/^projectVersion=//p' gradle.properties)

.DEFAULT_GOAL := help

.PHONY: help build build-fast test clean spotless spotless-java spotless-misc pitest \
	jacoco-coverage jacoco-report check-reproducible jar javadoc dependencies dependency-updates \
	version-catalog-format version-catalog-update publish-local tag-release

help:
	@echo "silly-strings - make targets"
	@echo ""
	@echo "  build                   clean build: tests + Jacoco + Spotless check (the everyday gate)"
	@echo "  build-fast              assemble without running tests"
	@echo "  test                    tests only"
	@echo "  clean                   remove build output"
	@echo ""
	@echo "  spotless                format and apply license headers (run before committing)"
	@echo "  spotless-java           Java sources only"
	@echo "  spotless-misc           everything else"
	@echo ""
	@echo "  pitest                  PIT mutation testing, fails below mutationThreshold (build.gradle)"
	@echo "  jacoco-coverage         verify the coverage thresholds"
	@echo "  jacoco-report           write the coverage report"
	@echo "  check-reproducible      build the published artifacts twice and compare them (JDK of .sdkmanrc)"
	@echo ""
	@echo "  jar                     the library jar"
	@echo "  javadoc                 the javadoc"
	@echo "  dependencies            print the dependency tree"
	@echo "  dependency-updates      report newer versions of dependencies and plugins"
	@echo "  version-catalog-format  sort and format gradle/libs.versions.toml"
	@echo "  version-catalog-update  write the newer versions into the catalog"
	@echo ""
	@echo "  publish-local           install into the local Maven repository"
	@echo "  tag-release             create the RELEASE tag; pushing it publishes (CONFIRM=yes, no SNAPSHOT)"

build:
	$(GRADLE) clean build

build-fast:
	$(GRADLE) assemble -x test

test:
	$(GRADLE) test

clean:
	$(GRADLE) clean

spotless:
	$(GRADLE) spotlessApply

spotless-java:
	$(GRADLE) spotlessJavaApply

spotless-misc:
	$(GRADLE) spotlessMiscApply

# --rerun-tasks --no-build-cache because a cached PIT run reports success in milliseconds without
# producing numbers
pitest:
	$(GRADLE) pitest --rerun-tasks --no-build-cache

jacoco-coverage:
	$(GRADLE) jacocoTestCoverageVerification

jacoco-report:
	$(GRADLE) jacocoTestReport

# the five artifacts a release publishes, built twice from the checked-out commit and compared by
# SHA-256 (scripts/check-reproducible.sh, #14)
check-reproducible:
	@test -x "$(SDKMAN_JDK)/bin/javac" || { \
		echo "check-reproducible: $(SDKMAN_JDK) holds no JDK; install the one .sdkmanrc names (sdk env install)"; \
		exit 1; }
	scripts/check-reproducible.sh "$(SDKMAN_JDK)"

jar:
	$(GRADLE) jar

javadoc:
	$(GRADLE) javadoc

dependencies:
	$(GRADLE) dependencies

dependency-updates:
	$(GRADLE) dependencyUpdates

version-catalog-format:
	$(GRADLE) versionCatalogFormat

version-catalog-update:
	$(GRADLE) versionCatalogUpdate

publish-local:
	$(GRADLE) publishToMavenLocal

# Tagging is publishing: .github/workflows/publish.yml triggers on 'RELEASE-*' tag pushes and
# uploads to Maven Central (README.md, "Releasing"). Decide whether to upload BEFORE the tag, not
# after it. An upload leaves only from CI, so there is no target that uploads from here.
tag-release:
	@case "$(VERSION)" in *-SNAPSHOT) \
		echo "tag-release refuses $(VERSION): a release tag carries a release version (README.md, \"Releasing\")"; \
		exit 1;; esac
	@test "$(CONFIRM)" = "yes" || { \
		echo "tag-release creates a RELEASE tag, and pushing it triggers publish.yml, which uploads"; \
		echo "to Maven Central. Re-run with CONFIRM=yes if that is intended."; \
		exit 1; }
	$(GRADLE) tagRelease
