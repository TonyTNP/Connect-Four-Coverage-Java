# Connect Four — Shared Rules, Split Paths (Java, JUnit 5)

This repository contains two independent implementations of the classic **Connect Four** game in Java, built to demonstrate **software testing concepts** including **statement coverage**, **per-test coverage reasoning**, and **basic-block analysis**.

The project was created as part of a software testing exercise where the same requirements (“shared rules”) are implemented via different internal control flows (“split paths”), then validated using a coverage-driven test suite.

## What I built

### Two implementations with the same external behavior
- **ConnectFourA**: win detection uses a direction-counting strategy (`countInDirection`) that counts from the placed token outward and combines opposite directions with overlap correction.
- **ConnectFourB**: win detection uses a different structure (`fourInLine` + `countStep`) that counts from adjacent cells outward and avoids overlap correction by design.

Although both versions follow the same rules and produce the same results (`INVALID`, `OK`, `WIN`, `DRAW`), their internal branching, loops, and helper-method structure differ — which is useful for comparing coverage behavior.

### Coverage-driven test suite (JUnit 5)
The JUnit tests were designed to exercise key execution paths:
- Invalid column inputs (range validation)
- Full-column invalid move
- Win detection:
    - Horizontal
    - Vertical
    - Diagonal down-right
    - Diagonal down-left
- **Draw scenario** using a verified 42-move sequence that fills the board without producing a win

## Tech stack
- Java 21
- Maven
- JUnit 5 (junit-jupiter)
- IntelliJ IDEA

## How to run

### Run tests (Maven)
```bash
mvn test