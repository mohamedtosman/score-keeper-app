# OnlinePajak

## Project Overview
Imagine that a coworking space has a foosball table in the common room. In order to settle the often asked question of who is the foosball champion, the team has elected to store the results of every game. To facilitate this process a new system will be built. The Foosball Ranking System.

## Project Requirements
As a user I can record the results of matches (no login requirements)
Able to enter the participants and the winner(s)
Assume the date of entry is the date of the match
Handle X vs X matches (1v1, 1v2, 2v2 etc)
As a user I can view the win rates
The overall win loss rate of a participant
The win loss rate of a participant against another specific participant

## Tech Stack
Java11
Vue.js
SpringBoot

## Running
1. Download repo
2. `cd fronend-vue`
3. `npm run serve`
4. Run OnlinepajakScorekeeperApplication from your preferred IDE

## Features
### Home page
- Home page displays all scores
- Clicking on a team under `First team` or `Second team` will take you to another page that displays number of wins/loses for that team on a donut chart
- Clicking on `Delete` will delete a score
- Clicking on `Add` will take you to a page where you have the option to enter

### Score page
- Score page allows user to enter a score by filling all fields
- `Id` and `Date` will be filled in automatically
- `Final Score`must be in format of `Number-Number`
- Hitting `Save` will submit the form and take you back to home page
- Hitting `Home` will take you to home page

### Compare page
- Select 2 Winners by clicking the checkbox and hit compare. This will take you to the compare page where a win/loss rate donut chart will be displayed of face to face scores against each other.
- Hitting `Home` will take you to home page
