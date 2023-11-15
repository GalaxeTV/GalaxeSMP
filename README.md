# GalaxeSMP

A general plugin to help manage the GalaxeSMP

Compatible on Paper 1.20.2+

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

## Required plugin dependencies

- [LuckPerms](https://luckperms.net/)
  - Used for setting pronouns as a suffix for the server.

## Configuration

There is a configuration file that you can edit.

Default configuration is below:

```yml
pronouns:
  max: 3
  valid:
    - 'He'
    - 'Him'
    - 'She'
    - 'Her'
    - 'They'
    - 'Them'
    - 'Star'
    - 'Stars'
    - 'Ey'
    - 'Em'
    - 'Fae'
    - 'Faer'
    - 'We'
    - 'Us'
    - 'It'
    - 'Its'
    - 'Ze'
    - 'Zem'
    - 'Genderfluid'
    - 'Any'
    - 'Ask'
```

You can specify the amount of pronouns that can be used, as well as a valid list of pronouns to be allowed. This can be helpful for keeping chat readable and preventing abuse of the pronouns system.

## Commands

### `/invisibleitemframe`

Toggles currently looked at item frame's visibility, is aliased to be:

* `/iif`
* `/invisframe`
* `/iframe`
* `/ifr`
* `/invframe`

Permission is set to be on for all players.

### `/help`

GUI Help Menu.

Permission is set to be on for all players.

### `/galaxereload`

Reloads the plugin configuration file.

### `/pronouns`

You can set, view, or clear your pronouns for yourself or other players.

This is aliased to be `/pronoun`

* `/pronouns set [pronoun pronoun pronoun]`

Sets your pronouns, the list must be space separated and the limit is defined in the configuration file.

* `/pronouns view`

Views your currently set pronouns.

* `/pronouns clear`

Clears your set pronouns.

There are other options for staff members or trusted members of a server to utilize.

* `/pronouns setother [username] [pronoun pronoun pronoun]`

Sets pronouns of another player, the list must be space separated and the limit is defined in the configuration file.

* `/pronouns clearother [username]`

Clears another players pronouns.

* `/pronouns list`

View all available pronouns as set in the configuration file.

## Permissions

There are some permission nodes that should be noted.

* `galaxesmp.reload`

Permission to reload the configuration file.

Default it is set to `op`.

* `galaxesmp.staff`

Permission for staff permissions, currently used to give players kill buffs.

Default it is set to `op`.

* `galaxesmp.pronouns`

Permission for pronouns.

Default it is set for all players.

* `galaxesmp.pronouns.other`

Permission for players to change other players pronouns.

Default it is set to `op`.

* `galaxesmp.pronouns.trusted`

Permission for trusted players to bypass the pronoun filter.

Default it is set to `op`.
