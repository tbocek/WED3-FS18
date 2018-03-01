const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    entry: {
        app: __dirname + '/src/main/app.js',
    },
    output: {
        path: __dirname + '/src/main/resources/static/dist', // `dist` is the destination
        publicPath: "/dist/",
        filename: 'bundle.js',
    },
    module: {
        loaders: [{
            test: /\.vue$/,
            loader: 'vue-loader',
        },]
    },
    plugins: [
        new UglifyJSPlugin()
    ],
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        }
    },
};