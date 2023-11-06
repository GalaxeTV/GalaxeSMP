# GalaxeSMP

A general plugin to help manage the GalaxeSMP

Compatible on Paper 1.19.4+

Code of Conduct is located in the [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) file.

## Building

Requirements:

- Java 17 JDK

Steps:

1. Clone repository
2. `./gradlew spotlessApply`
3. `./gradlew shadowJar`

## Code conventions/quality gates

This repository currently has a couple of checks in place to ensure code quality. These are:

- Syntax check powered by [Spotless](https://github.com/diffplug/spotless)
- [CodeQL](https://codeql.github.com/) analysis on pull requests
- [Dependabot](https://github.com/dependabot) to keep dependencies up to date

There is also no allowing of force pushing to the main branch. Everything has to be done through a pull request. If you have a new feature that you want to add, please create a new branch and open a pull request. This will allow for code review and discussion before merging.

## Commands

### `/invisibleitemframe`

Toggles currently looked at item frame, is aliased to be:

* `/iif`  
* `/invisframe`  
* `/iframe`  
* `/ifr`  
* `/invframe`  

Permission is set to be on for all players.

### `/help`

GUI Help Menu.

Permission is set to be on for all players.
