<template>
    <div>
        <h1>Win Rates</h1>
        <apexchart type=pie width='50%' :options="chartOptions" :series="series" />
        <div class="row">
            <button class="btn btn-success" v-on:click="goHome()">Home</button>
        </div>
    </div>
</template>

<script>
    import ScoreDataService from "../service/ScoreDataService";

    export default {
        name: "winRates",
        data() {
            return {
                wins: 0,
                games: 0,
                series: [],
                chartOptions: {
                    labels: ["Wins", "Loses"],
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
            teamName() {
                return this.$route.params.teamName;
            }
        },
        methods: {
            refreshWinRates() {
                ScoreDataService.retrieveWinRates(this.teamName)
                    .then(response => {
                       this.wins = response.data.wins;
                       this.games = response.data.games;
                       this.series = [this.wins, this.games-this.wins];
                    });
            },
            goHome() {
                this.$router.push(`/`);
            },
        },
        created() {
            this.refreshWinRates();
        }
    }
</script>

<style scoped>

</style>