var webpack = require('webpack')
var HtmlWebpackPlugin = require('html-webpack-plugin')

const DEBUG = !process.argv.includes('--release')
const VERBOSE = process.argv.includes('--verbose')
let CONTEXT_PATH = `${(process.env.CONTEXT_PATH || '')}`

module.exports = {
  context: __dirname + '/src/main/client',
  entry: {
    'js/bundle': [...(DEBUG ? ['webpack-hot-middleware/client'] : []), './index.js']
  },
  output: {
    path: __dirname + '/src/main/resources/static',
    filename: '[name].js',
    publicPath: `${CONTEXT_PATH}/`
  },
  resolve: {
    extensions: ['', '.js', '.jsx']
  },
  devtool: DEBUG ? 'cheap-module-eval-source-map' : false,
  plugins: [
    new webpack.DefinePlugin({
      'process.env.CONTEXT_PATH': `"${CONTEXT_PATH}"`,
      'process.env.NODE_ENV': `"${process.env.NODE_ENV || (DEBUG ? 'development' : 'production')}"` 
    }),
    ...(DEBUG ? [new webpack.HotModuleReplacementPlugin()] : [
      new webpack.optimize.OccurenceOrderPlugin(),
      new webpack.optimize.DedupePlugin(),
      new webpack.optimize.AggressiveMergingPlugin(),
      new webpack.optimize.UglifyJsPlugin({ compress: { screw_ie8: true, warnings: VERBOSE } })
    ]),
    new HtmlWebpackPlugin({
      title: 'timber-forest',
      template: 'index.ejs'
    })
  ],
  module: {
    loaders: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        loader: 'babel'
      },
      {
        test: /\.css?$/,
        loaders: [ 'style', 'raw' ],
        include: __dirname
      },
      {
        test: /\.html$/,
        loader: 'file?name=[name].[ext]'
      }
    ]
  }
}
