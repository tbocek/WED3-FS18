const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    context: __dirname + '/src', // `__dirname` is root of project and `src` is source
    entry: {
        app: './main/app.js',
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
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        }
    },
    //plugins: [
    //    new UglifyJSPlugin()
    //]
};