# EverCraft Backend

## Overview
The following repo contains backend for training Evercraft kata.

## Guidelines
Use this document to track planned features to be added as well as documenting how to run the application in case new individuals join the practice

## Future Features
- add local database connection using file storage. Used this url for reference: https://www.baeldung.com/spring-boot-h2-database
- Setup character entity to be used in the database, spend some time deciding what features the data needs for current planned features to reduce excess rework. Implement using a JPA setup, using this as reference: https://spring.io/guides/gs/accessing-data-jpa
- Implement Dice functionallity. Create an end point for all kinds of dice (d2, d3, d4, d6, d8, d10, d12, d20, d100). Each endpoint should call proper behavior for its dice type which should use a extended base class of Dice (ex. d4 would use DiceFour class extending Dice, Dice would have functionallity for rolling, rolling with advantage, and rolling with disadvantage and a field for max value of dice, DiceFour sets max value of itself and otherwise uses existing dice functionallity).
