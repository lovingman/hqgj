//vue.config.js
const path = require('path')
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
  chainWebpack: config => {
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
