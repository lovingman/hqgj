import {
  constantRoutes
} from '@/router'
import {
  getMenu
} from '@/api/user'
const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({
    commit
  }, roles) {
    return new Promise(resolve => {
      getMenu().then(response => {
        let routers = formatRoutes(response.data, true);
        console.info(routers);
        commit('SET_ROUTES', routers);
        resolve(routers);
      }).catch(error => {
        console.info(error)
      })

    })
  }
}


export const formatRoutes = (menus, isRoot) => {
  var trees = []
  if (!menus) {
    return [];
  }
  menus.forEach(e => {
    if (e.children && e.children.length > 0) {
      if (isRoot) {
        trees.push({
          path: e.href,
          name: e.text,
          // redirect:e.href,
          component: () => import('@/layout'),
          meta: {
            title: e.text,
            icon: e.icon
          },
          children: formatRoutes(e.children, false)
        });
      } else {
        trees.push({
          path: e.href,
          name: e.text,
          // redirect:e.href,
          component: () => import('@/components/SecondLayout'),
          meta: {
            title: e.text,
            icon: e.icon
          },
          children: formatRoutes(e.children, false)
        });
      }
    } else {
      trees.push({
        path: e.href,
        name: e.text,
        hidden: (e.src == '4'),
        component: resolve => (require(['@/views' + e.href], resolve)),
        // component: () => import('@/views'+e.href),
        meta: {
          title: e.text,
          icon: e.icon
        }
      });
    }

  })
  return trees
}
export default {
  namespaced: true,
  state,
  mutations,
  actions
}