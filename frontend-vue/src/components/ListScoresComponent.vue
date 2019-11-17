<template>
    <div class="container">
        <h3>All Scores</h3>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th></th>
                    <th>Id</th>
                    <th>Final Score</th>
                    <th>Winner</th>
                    <th>First team</th>
                    <th>Second team</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                    <tr v-for="(score, index) in scores" v-bind:key="score.id">
                        <label><input type="checkbox" :value="index" name="teams" v-model="selectedTeams" :disabled="selectedTeams.length >= max && selectedTeams.indexOf(index) == -1" /></label>
                        <td>{{score.id}}</td>
                        <td>{{score.finalScore}}</td>
                        <td>{{score.winner}}</td>
                        <td @click="winRates(score.firstTeam)">
                            <a href="#">{{score.firstTeam}}</a>
                        </td>
                        <td @click="winRates(score.secondTeam)">
                            <a href="#">{{score.secondTeam}}</a>
                        </td>
                        <td>{{score.date}}</td>
                        <td><button class="btn btn-warning" v-on:click="deleteScoreClicked(score.id)">Delete</button></td>
                    </tr>
                </tbody>
            </table>
            <div class="row">
                <button class="btn btn-success" v-on:click="addScore()">Add</button>
                <div class="divider"/>
                <button class="btn btn-success" v-on:click="compareTeams(selectedTeams)">Compare</button>
            </div>
        </div>
    </div>
</template>

<script>
    import ScoreDataService from "../service/ScoreDataService";
    export default {
        name: "ScoresList",
        data() {
            return {
                selectedTeams: [],
                max: 2,
                scores: [],
                message: ""
            };
        },
        methods: {
            /**
             * This method gets called on page creation
             */
            refreshScores() {
                ScoreDataService.retrieveAllScores()
                    .then(response => {
                        this.scores = response.data;
                    });
            },
            /**
             * Gets called upon hitting the delete button to delete a score
             * @param id
             */
            deleteScoreClicked(id) {
                ScoreDataService.deleteScore(id)
                .then(() => {
                    this.refreshScores();
                });
            },
            /**
             * Gets called upon hitting the add button to add a score
             */
            addScore() {
                this.$router.push(`/details/-1`);
            },
            /**
             * Gets called upon selecting a team to get win rates and route to a different page
             * @param teamName
             */
            winRates(teamName) {
                ScoreDataService.retrieveWinRates(teamName)
                    .then(() => {
                        this.$router.push(`/rates/${teamName}`);
                    });
            },
            /**
             * Gets called when the compare button is clicked to compare face to face matches between 2 teams
             * @param selectedTeams
             */
            compareTeams(selectedTeams) {
                if(this.scores[selectedTeams[[0]]].winner === this.scores[selectedTeams[[1]]].winner) {
                    alert("You can't compare the same team!");
                    return;
                }
                ScoreDataService.compareTeams(this.scores[selectedTeams[[0]]].winner, this.scores[selectedTeams[[1]]].winner)
                    .then(() => {
                        this.$router.push(`/compare?firstTeam=${this.scores[selectedTeams[[0]]].winner}&secondTeam=${this.scores[selectedTeams[[1]]].winner}`)
                    });
            }
        },
        created() {
            this.refreshScores();
        }
    };
</script>

<style>
</style>