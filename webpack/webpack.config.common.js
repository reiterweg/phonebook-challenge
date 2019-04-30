'use strict';

const webpack = require('webpack');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const FilterWarningsPlugin = require('webpack-filter-warnings-plugin');

const helpers = require('./helpers');
const isDev = process.env.NODE_ENV !== 'production';

module.exports = {
  entry: {
    vendor: './src/main/webapp/vendor.ts',
    polyfills: './src/main/webapp/polyfills.ts',
    main: isDev ? './src/main/webapp/main.ts' : './src/main/webapp/main.aot.ts'
  },
  resolve: {
    extensions: ['.ts', '.js', '.scss']
  },
  module: {
    rules: [
      {
        test: /\.html$/,
        loader: 'html-loader'
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
        include: helpers.root('src', 'main', 'webapp', 'assets')
      },
      {
        test: /\.(png|jpe?g|gif|ico)$/,
        loader: 'file-loader?name=assets/[name].[hash].[ext]'
      },
      {
        test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url-loader?limit=10000&mimetype=application/font-woff'
      },
      {
        test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url-loader?limit=10000&mimetype=application/font-woff'
      },
      {
        test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url-loader?limit=10000&mimetype=application/octet-stream'
      },
      {
        test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'file-loader'
      },
      {
        test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url-loader?limit=10000&mimetype=image/svg+xml'
      },
      {
        test: /\.(scss|sass)$/,
        use: [
          { loader: 'style-loader', options: { sourceMap: isDev } },
          { loader: 'css-loader', options: { sourceMap: isDev } },
          { loader: 'sass-loader', options: { sourceMap: isDev } }
        ],
        include: helpers.root('src', 'main', 'webapp', 'assets')
      },
      {
        test: /\.(scss|sass)$/,
        use: [
          'to-string-loader',
          { loader: 'css-loader', options: { sourceMap: isDev } },
          { loader: 'sass-loader', options: { sourceMap: isDev } }
        ],
        include: helpers.root('src', 'main', 'webapp', 'app')
      },
      // Ignore warnings about System.import in Angular
      { test: /[\/\\]@angular[\/\\].+\.js$/, parser: { system: true } }
    ]
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080'
      }
    }
  },
  plugins: [
    new CleanWebpackPlugin(
      { root: helpers.root(), verbose: true }),
    new HtmlWebpackPlugin({
      template: 'src/main/webapp/index.html'
    }),
    new FilterWarningsPlugin({
      exclude: /Critical dependency: the request of a dependency is an expression/
    }),
    new webpack.ProvidePlugin({
      jQuery: 'jquery'
    })
  ]
};