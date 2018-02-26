<template>
    <p>{{price}} USD</p>
</template>
<style>
    body{
        background-color:#f0f0f0;
    }
</style>
<script>
    export default {
        props: ['symbol'],
        data: function () {
            return {
                coinmarketcapData: {}
            }
        },
        created: function () {
            fetch('https://api.coinmarketcap.com/v1/ticker/?limit=10')
                .then(response => response.json())
                .then(body => {
                    this.coinmarketcapData = body.reduce(function(map, obj) {
                        map[obj.symbol] = obj;
                        return map;
                    }, {});
                });
        },
        computed: {
            price: function() {
                if (typeof this.coinmarketcapData[this.symbol] !== 'undefined') {
                    return this.coinmarketcapData[this.symbol].name + " " + this.coinmarketcapData[this.symbol].price_usd;
                }
            }
        }
    }
</script>
