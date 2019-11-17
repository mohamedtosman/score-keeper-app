import axios from "axios";

const SCORE_API_URL = "http://localhost:8080";

class ScoreDataService {
    retrieveAllScores() {
        return axios.get(`${SCORE_API_URL}/scores`);
    }

    deleteScore(id) {
        return axios.delete(`${SCORE_API_URL}/${id}`)
    }

    retrieveScore(id) {
        return axios.get(`${SCORE_API_URL}/details/${id}`)
    }

    createScore(score) {
        return axios.post(`${SCORE_API_URL}/`, score);
    }

    retrieveWinRates(teamName) {
        return axios.get(`${SCORE_API_URL}/rates/${teamName}`, teamName);
    }

    compareTeams(firstTeam, secondTeam) {
        return axios.get(`${SCORE_API_URL}/compare`,
            {
                params: {
                    firstTeam: firstTeam,
                    secondTeam: secondTeam
                }
            });
    }
}

export default new ScoreDataService();