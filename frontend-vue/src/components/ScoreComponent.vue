<template>
    <div>
        <h3>Score</h3>
        <div class="container">
            <form @submit="validateAndSubmit">
                <div v-if="errors.length">
                    <div class="alert alert-warning" v-bind:key="index" v-for="(error, index) in errors">{{error}}</div>
                </div>
                <fieldset class="form-group">
                    <label>Id</label>
                    <input type="text" class="form-control" v-model="id" disabled>
                </fieldset>
                <fieldset class="form-group">
                    <label>Final Score</label>
                    <input type="text" class="form-control" v-model="finalScore">
                </fieldset>
                <fieldset class="form-group">
                    <label>Winner</label>
                    <v-select v-model="winner" :options="teamList"></v-select>
                </fieldset>
                <fieldset class="form-group">
                    <label>First Team</label>
                    <v-select v-model="firstTeam" :options="teamList"></v-select>
                </fieldset>
                <fieldset class="form-group">
                    <label>Second Team</label>
                    <v-select v-model="secondTeam" :options="teamList"></v-select>
                </fieldset>
                <fieldset class="form-group">
                    <label>Date</label>
                    <input type="text" class="form-control" v-model="date" disabled>
                </fieldset>
                <button class="btn btn-success" type="submit">Save</button>
                <div class="divider"/>
                <button class="btn btn-success" v-on:click="goHome()">Home</button>
            </form>
        </div>
    </div>
</template>

<script>
    import ScoreDataService from "../service/ScoreDataService";

    export default {
        name: "scoreDetails",
        data() {
            return {
                s : {},
                finalScore: "",
                winner: "",
                firstTeam: "",
                secondTeam: "",
                date: "",
                teamList: ["TEAM1", "TEAM2", "TEAM3", "TEAM4"],
                //selectedWinner: null,
                errors: []
            };
        },
        computed: {
            id() {
                return this.$route.params.id;
            }
        },
        methods: {
            refreshScoreDetails() {
                ScoreDataService.retrieveScore(this.id)
                .then(response => {
                    this.s = response.data;
                    this.finalScore = response.data.finalScore;
                    this.winner = response.data.winner;
                    this.firstTeam = response.data.firstTeam;
                    this.secondTeam = response.data.secondTeam;
                    this.date = response.data.date;
                });
            },
            /**
             * Validates form and submits
             * @param e
             */
            validateAndSubmit(e) {
                this.errors = [];
                let today = new Date();
                let dd = String(today.getDate()).padStart(2, '0');
                let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                let yyyy = today.getFullYear();

                if(!this.finalScore) {
                    this.errors.push('Score required');
                } else if (!this.validateScore(this.finalScore)) {
                    this.errors.push('Score must be in "Number-Number" format');
                }

                if(!this.firstTeam || !this.secondTeam || !this.secondTeam) {
                    this.errors.push('You must select teams for all drop down fields.')
                }

                if(this.errors.length === 0) {
                    if (this.id == -1) {
                        ScoreDataService.createScore({
                            id: this.id,
                            finalScore: this.finalScore,
                            winner: this.winner,
                            firstTeam: this.firstTeam,
                            secondTeam: this.secondTeam,
                            date: yyyy + "-" + mm + "-" + dd
                        })
                            .then(() => {
                                this.$router.push('/');
                            });
                    }
                }
                e.preventDefault();
            },
            /**
             * Validates the score field by using regex
             * @param finalScore
             * @returns {boolean}
             */
            validateScore(finalScore) {
                let re = /^[0-9]+-[0-9]+/;
                return re.test(finalScore);
            },
            /**
             * Goes to home page
             */
            goHome() {
                this.$router.push(`/`);
            }
        },
        created() {
            this.refreshScoreDetails();
        }
};
</script>

<style scoped>

</style>