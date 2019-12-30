import Vue from 'vue'
import VueResource from 'vue-resource'
import global from './utils/global'
import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './styles/element-variables.scss'

import '@/styles/index.scss' // global css
import '@/assets/icon-third/iconfont.css'

import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'

import App from './App'
import store from './store'
import router from './router'

import VideoPlayer from 'vue-video-player'
require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')

import './icons' // icon
import './permission' // permission control
import './utils/error-log' // error log

import * as filters from './filters' // global filters

import Print from 'vue-print-nb'
Vue.use(Print); //打印

import LightTimeline from 'vue-light-timeline';

Vue.use(LightTimeline);
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
import {
  mockXHR
} from '../mock'
if (process.env.NODE_ENV === 'production') {
  mockXHR()
}

Vue.use(VideoPlayer)

Vue.use(Viewer, {
  defaultOptions: {
    zIndex: 9999
  }
})

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})
Vue.use(VueResource)
Vue.use(global)
// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false;

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
