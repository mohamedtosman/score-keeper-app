import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode: "history", // Use browser history
    routes: [
        {
            path: "/",
            name: "Home",
            component: () => import("./components/ListScoresComponent")
        },
        {
            path: "/details/:id",
            name: "Score Details",
            component: () => import("./components/ScoreComponent")
        },
        {
            path: "/rates/:teamName",
            name: "Win Rates",
            component: () => import("./components/WinRatesComponent")
        },
        {
            //path: "/compare?firstTeam=:firstTeam&secondTeam=:secondTeam",
            path: "/compare",
            name: "Compare Teams",
            component: () => import("./components/CompareTeamsComponent")
        }
    ]
});

export default router;