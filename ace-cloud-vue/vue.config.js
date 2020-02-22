//vue.config.js
const path = require('path')
const webpack = require('webpack');
module.exports = {
  //1.从vue cli3.3开始已弃用，请使用publicPath
  /*
    2.1部署应用包时的基本URL。用法和webpack本身的output.publicPath一致。
但在cli的其它地方也会用到这个值，所以请不要直接修改webpack的output.publicPath。
    2.2默认情况下,Vue Cli会假设你的应用是被部署在一个域名的根路径。
如我本地的应用的路径是“D:\WORK\study\vue\vue_cli3_test\demo1”，则在这个应用中，根路径就是“D:\WORK\study\vue\vue_cli3_test\demo1”
  */
  // publicPath: '/webapp/server',
  // publicPath: '/webapp',
  publicPath: '/www',

  //3.当运行vue-cli-service build时生成的生产环境构建环境的目录。用法和webpack的output.path一样，不要修改output.path
  outputDir: 'server',

  //4.放置打包后生成的静态资源（js、css、img、fonts）的目录，该目录相对于outputDir。
  assetsDir: 'assets',

  //5.指定生成的index.html的输出路径，相对于outputDir。也可以是一个绝对路径。
  indexPath: 'index.html',
  devServer: {
    disableHostCheck: true
  },
  lintOnSave: false,
  runtimeCompiler: true, // 是否使用包含运行时编译器的 Vue 构建版本
  productionSourceMap: false, // 生产环境的 source map
  /*configureWebpack: {
    plugins: [
      new webpack.DllReferencePlugin({
        context: process.cwd(),
        manifest: require("./public/vendor/vendor-manifest.json")
      })
    ]
  },*/
  chainWebpack: config => {
    config.plugin('prefetch').tap(options => {
      options[0].fileBlacklist = options[0].fileBlacklist || []
      options[0].fileBlacklist.push(/myasyncRoute(.)+?\.js$/)
      return options
    });
       /*
    修改node_modules _lib-flexible@0.3.2@lib-flexible flexible.js
    我们把代码改一下
    function refreshRem(){
        var width = docEl.getBoundingClientRect().width;
        var rem = width / 10;
        docEl.style.fontSize = "22" + 'px';
        flexible.rem = win.rem = rem;
    }
    */
			config.module
			.rule('sass')
			.oneOf('vue')
			.use('px2rem-loader')
			.loader('px2rem-loader')
			.before('postcss-loader') //19.2 this makes it work.
			.options({ remUnit:19.2, remPrecision: 8 })
			.end()
			config.module
			.rule('css')
			.oneOf('vue')
			.use('px2rem-loader')
			.loader('px2rem-loader')
			.before('postcss-loader') // this makes it work.
			.options({ remUnit:19.2, remPrecision: 8 })
			.end()
			  config.module
				.rule('less')
				.oneOf('vue')
				.use('px2rem-loader')
				.loader('px2rem-loader')
				.before('postcss-loader') // this makes it work.
				.options({ remUnit:19.2, remPrecision: 8 })
				.end()
			config.module
			.rule('scss')
			.oneOf('vue')
			.use('px2rem-loader')
			.loader('px2rem-loader')
			.before('postcss-loader') // this makes it work.
			.options({ remUnit:19.2, remPrecision: 8 })
			.end()
		console.log(config)
    const svgRule = config.module.rule('svg')
    // 清除已有的所有 loader。
    // 如果你不这样做，接下来的 loader 会附加在该规则现有的 loader 之后。
    svgRule.uses.clear()
    svgRule
      .test(/\.svg$/)
      .include.add(path.resolve(__dirname, './src/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
    const fileRule = config.module.rule('file')
    fileRule.uses.clear()
    fileRule
      .test(/\.svg$/)
      .exclude.add(path.resolve(__dirname, './src/icons'))
      .end()
      .use('file-loader')
      .loader('file-loader')
  }

}