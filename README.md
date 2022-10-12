# GalaxeSMP

A general plugin to help manage the GalaxeSMP

Compatible on Paper 1.19.2+

## Building

Requirements:

- Java 17
- Gradle

Steps:

1. Clone repository
2. `./gradlew shadowJar`

The reason for shadowing is an issue with the Twitch4J library used for Twitch integration. This results in a larger plugin however it remains similarly functional.

## Code conventions

Attempts to follow [Google Java Styleguide](https://google.github.io/styleguide/javaguide.html), with a check if there are issues powered by [Spotless](https://github.com/diffplug/spotless).

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
