import Vue from 'vue'
import PortfolioManager from './vue/portfolio-manager.vue'

Vue.component('portfolio-manager', PortfolioManager);

new Vue({
    el: '#app',
    component: { PortfolioManager }
})