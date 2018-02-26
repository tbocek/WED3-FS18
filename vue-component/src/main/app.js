import Vue from 'vue'
import Ticker from './vue/ticker.vue'

Vue.component('ticker', Ticker);

new Vue({
    el: '#app',
    component: { Ticker }
})