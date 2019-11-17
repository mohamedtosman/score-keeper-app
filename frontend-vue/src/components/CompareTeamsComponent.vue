<template>
    <div>
        <h1>Compare Rates</h1>

        <template v-if="series[0] === 0 && series[1] === 0">
            <h6>The teams selected have never competed against each other!</h6>
        </template>
        <template v-else>
            <apexchart type=pie width='50%' :options="chartOptions" :series="series" />
        </template>

        <div class="row">
            <button class="btn btn-success" v-on:click="goHome()">Home</button>
        </div>
    </div>
</template>

<script>
    import ScoreDataService from "../service/ScoreDataService";

    export default {
        name: "compareTeams",
        data() {
            return {
                firstRate: 0,
                secondRate: 0,
                series: [],
                chartOptions: {
                    labels: [this.$route.query.firstTeam + " Wins", this.$route.query.secondTeam + " Wins"],
                    theme: {
                        monochrome: {
                            enabled: true
                        }
                    },
                    title: {
                        text: this.$route.params.teamName
                    },
                    responsive: [{
                        breakpoint: 480,
                        options: {
                            chart: {
                                width: 200
                            },
                            legend: {
                                position: 'bottom'
                            }
                        }
                    }]
                }
            };
        },
        computed: {
            /**
             * Initializes variable name from query variable
             * @returns {string | (string | null)[]}
             */
            firstTeam() {
                return this.$route.query.firstTeam;
            },
            /**
             * Initializes variable name from query variable
             * @returns {string | (string | null)[]}
             */
            secondTeam() {
                return this.$route.query.secondTeam;
            }
        },
        methods: {
            /**
             * This method gets called on page creation to fill out the needed data
             */
            refreshCompareTeams() {
                ScoreDataService.compareTeams(this.firstTeam, this.secondTeam)
                    .then(response => {
                        this.firstRate = response.data.firstTeam;
                        this.secondRate = response.data.secondTeam;
                        this.series = [this.firstRate, this.secondRate];
                    });
            },
            /**
             * Go back to main page
             */
            goHome() {
                this.$router.push(`/`);
            },
        },
        created() {
            this.refreshCompareTeams();
        }
    }
</script>

<style scoped>

</style>