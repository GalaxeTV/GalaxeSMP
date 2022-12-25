# GalaxeSMP

A general plugin to help manage the GalaxeSMP

Compatible on Paper 1.19.2+

Code of Conduct is located in the [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) file.

## Building

Requirements:

- Java 17
- Gradle

Steps:

1. Clone repository
2. `./gradlew shadowJar`

The reason for shadowing is an issue with the [Twitch4J](https://github.com/twitch4j/twitch4j) library used for Twitch integration. This results in a larger plugin however it remains similarly functional.

## Dependencies for your Minecraft Server

This plugin requires the following plugins to be installed on your server:

- LuckPerms

## Code conventions/quality gates

This repository currently has a couple of checks in place to ensure code quality. These are:

- Syntax check powered by [Spotless](https://github.com/diffplug/spotless)
- [CodeQL](https://codeql.github.com/) analysis on pull requests
- [Dependabot](https://github.com/dependabot) to keep dependencies up to date
- [Qodana](https://www.jetbrains.com/qodana/) to check for code smells

There is also no allowing of force pushing to the main branch. Everything has to be done through a pull request. If you have a new feature that you want to add, please create a new branch and open a pull request. This will allow for code review and discussion before merging.

## Twitch.TV integration

To utilize the Twitch integration, you need to create an application in the Twitch developer console.

To do this:
1. Go to the [Twitch Developer Console](https://dev.twitch.tv/console/apps)
2. Click "Register Your Application"
3. Fill out the form with the following:
    - Name: GalaxeSMP
    - OAuth Redirect URLs: `https://localhost`
    - Category: Game Integration
4. Click "Register"
5. Copy the Client ID and Client Secret into the `config.yml` file in their respective spots

## Default Configuration

```yaml
# Twitch Integration
twitch:
  channel: ""
  client_id: ""
  client_secret: ""
```

## Commands

### `/invisibleitemframe`

Toggles currently looked at item frame, is aliased to be:

* `/invisframe`
* `/iframe`
* `/ifr`
* `/invframe`

Permission is set to be on for all players by default.

### `/twitch`

Checks if the current streamer is live.

Permission is set to be on for all players by default.
