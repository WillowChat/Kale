# Kale
Kotlin/JVM IRC message parsing, serialising and notifying. Provides useful abstractions with the intention of splitting message parsing and IRC state management.

[Warren](https://github.com/WillowChat/Warren) is the state tracking counterpart. If you're interested in making something for IRC, you probably want it instead. Kale by itself does not track any IRC state.

[Thump](https://github.com/WillowChat/Thump) is the primary upstream project - a bridge that lets people chat between Minecraft and IRC whilst they play.

[![codecov](https://codecov.io/gh/WillowChat/Kale/branch/develop/graph/badge.svg)](https://codecov.io/gh/WillowChat/Kale)

## Features

Warren and Kale have a few interesting features:

* The responsibilities of parsing and state management are separated
* Both parsing and state management are verified by hundreds of unit tests
* Messages, and state handlers, are individually encapsulated

Planned releases (and their features) are tracked in [Projects](https://github.com/WillowChat/Kale/projects).

## Support

<a href="https://patreon.com/carrotcodes"><img src="https://s3.amazonaws.com/patreon_public_assets/toolbox/patreon.png" align="left" width="160" ></a>
If you use this library and you'd like to support my open-source work, please consider tipping through [Patreon](https://patreon.com/carrotcodes).

## TODO
* [RFC 1459](https://tools.ietf.org/html/rfc1459)
 * Pretty much done for majority of usage - consider being more complete
* [IRC v3](http://ircv3.net/irc/)
 * Messages for extensions listed on the IRCv3 libraries site: http://ircv3.net/software/libraries.html

## Code License
The source code of this project is licensed under the terms of the ISC license, listed in the [LICENSE](LICENSE.md) file. A concise summary of the ISC license is available at [choosealicense.org](http://choosealicense.com/licenses/isc/).

## Building
This project uses Gradle and IntelliJ IDEA for pretty easy setup and building.

The general idea:
* **Setup**: `./gradlew clean idea`
* **Building**: `./gradlew build`
* **Producing an all-in-one Jar**: `./gradlew build shadowJar`

If you run in to odd Gradle issues, doing `./gradlew clean` usually fixes it.
