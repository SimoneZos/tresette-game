# 🎴 TreSette Game - Italian Card Game

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://java.com)
[![Swing](https://img.shields.io/badge/GUI-Swing-4FC08D?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?style=for-the-badge&logo=github)](https://github.com/SimoneZos/tresette-game)

A complete implementation of the traditional Italian card game "TreSette" developed in Java with Swing GUI.

## 🎯 Features

- **Complete Game**: Faithful implementation of TreSette rules
- **Graphical Interface**: Intuitive UI developed with Java Swing
- **Profile System**: User statistics and profile management
- **Sound Effects**: Immersive audio for better user experience
- **Solid Architecture**: MVC, Observer, Singleton, Factory patterns

## 🏗️ Project Architecture

### Implemented Patterns
- **MVC (Model-View-Controller)**: Clear separation of concerns
- **Observer/Observable**: Real-time UI updates
- **Singleton**: Navigation management between screens
- **Simple Factory**: Player and card deck creation

## 🎮 Implemented Rules

- **Players**: 4 players (1 human + 3 CPU) in 2 teams
- **Deck**: 40 Italian cards (Coins, Cups, Swords, Clubs)
- **Scoring**: Traditional point system with bonus for last trick
- **Gameplay**: Counter-clockwise turns, must follow suit

## 🚀 Installation & Execution

### Prerequisites
- Java JDK 17 or higher
- Git

### Quick Start
```bash
# Clone the repository
git clone https://github.com/SimoneZos/tresette-game.git
cd tresette-game

# Import as existing project in Eclipse/IntelliJ
# Run src/launcher/Main.java or JTresette.java
