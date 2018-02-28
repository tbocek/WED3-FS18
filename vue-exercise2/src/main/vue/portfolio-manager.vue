<template>
    <div>
    <p v-for="item in userCoinPortfolio">
        <input v-model="item.symbol" placeholder="BTC">
        <input v-model="item.amount" placeholder="0">
        {{item.amount * find(item.symbol)}}
        <button @click="remove(item.symbol)">remove</button>
    </p>
    <p>Total: {{total | round}} USD</p>
    <select v-model="selected">
        <option disabled value="">Please select one</option>
        <option v-for="item in coinmarketcapData" :value="item.symbol">
            {{ item.name }}
        </option>
    </select>
    <button @click="userCoinPortfolio.push({'symbol' : selected, 'amount': '0'})">add</button>
    </div>
</template>
<style>
    button {
        background-color: #4CAF50; /* Green */
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
    }
</style>
<script>
    export default {
        data: function () {
            return {
                coinmarketcapData: [],
                userCoinPortfolio: [],
                uuid: '',
                selected: ''
            }
        },
        created: function () {
            if ('serviceWorker' in navigator) {
                navigator.serviceWorker.register('service-worker.js');
            }
            this.fetch()
            setInterval(this.fetch, 30 * 1000)
            if(window.location.hash.length == 0) {
                //from: https://stackoverflow.com/questions/105034/create-guid-uuid-in-javascript
                this.uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                    return v.toString(16);
                });
            } else {
                this.uuid = window.location.hash.substring(1)
            }
        },
        methods: {
            fetch: function () {
                fetch('https://api.coinmarketcap.com/v1/ticker/?limit=10')
                    .then(response => response.json())
                    .then(body => { this.coinmarketcapData = body });
            },
            find: function(symbol) {
                if(this.coinmarketcapData.length > 0) {
                    let found = this.coinmarketcapData.find(function(element) {
                        return element.symbol === symbol
                    });
                    return parseFloat(found.price_usd)
                } else return 0;
            },
            remove: function(symbol) {
               this.userCoinPortfolio = this.userCoinPortfolio.filter(item => item.symbol !== symbol)
            }
        },
        computed: {
            total: function () {
                return this.userCoinPortfolio.reduce((total, item) => {
                    return total + (item.amount * this.find(item.symbol));
                }, 0)
            }
        },
            watch: {
                uuid: function(newUuid, oldUuid) {
                    window.location.hash = '#' + newUuid
                        fetch('/portfolio/'+this.uuid)
                        .then(response => response.json())
                        .then(body => {
                            this.userCoinPortfolio = body;
                        });
                },
                userCoinPortfolio: {
                    handler: function(newUuid) {
                        fetch('/portfolio/' + this.uuid, {
                            method: 'POST',
                            body: JSON.stringify(this.userCoinPortfolio)
                        })
                    }, deep: true
                }
            },
            filters: {
                round: function (value) {
                    return Math.round(value)
                }
            }
    }
</script>
