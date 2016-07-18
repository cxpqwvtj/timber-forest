var webpack = require('webpack')
var webpackDevMiddleware = require('webpack-dev-middleware')
var webpackHotMiddleware = require('webpack-hot-middleware')
var proxy = require('http-proxy-middleware')
var config = require('../../../webpack.config')

var app = new (require('express'))()
var port = 3000

var compiler = webpack(config)
app.use(webpackDevMiddleware(compiler, { noInfo: true, publicPath: config.output.publicPath }))
app.use(webpackHotMiddleware(compiler))
app.use('/api', proxy({target: 'http://localhost:8080', changeOrigin: false}))

app.get('/file*', function(req, res) {
  console.log('[req]', req.url)
  res.setHeader('Content-Type', 'text/html')
  res.send(compiler.outputFileSystem.readFileSync(compiler.outputPath + '/index.html'))
})

app.listen(port, function(error) {
  if (error) {
    console.error(error)
  } else {
    console.info("==> 🌎  Listening on port %s. Open up http://localhost:%s/ in your browser.", port, port)
  }
})