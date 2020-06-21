import Vue from "vue";
import Router from "vue-router";

// 布局组件
import Layout from '@/components/Layout.vue'

import MY from '@/views/spectrum/my.vue'
import LX from '@/views/spectrum/lx.vue'
import YG from '@/views/spectrum/yg.vue'
import ZGF from '@/views/spectrum/zgf.vue'
import OM from '@/views/spectrum/om.vue'
import RH from '@/views/spectrum/rh.vue'


Vue.use(Router);

export default new Router({
  routes: [
    {
      // 基础布局
      path: '/',
      name: 'layout',
      component: Layout,
      redirect: '/my',// 重定向到子路由
    },
    {
      //吉他谱-民谣
      path: '/my',
      component: Layout,
      children: [
        {
          path: '/',
          component: MY,
          meta: { title: '民谣' }
        }
      ]
    },
    {
      //吉他谱-流行
      path: '/lx',
      component: Layout,
      children: [
        {
          path: '/',
          component: LX,
          meta: { title: '流行' }
        }
      ]
    },
    {
      //吉他谱-摇滚
      path: '/yg',
      component: Layout,
      children: [
        {
          path: '/',
          component: YG,
          meta: { title: '摇滚' }
        }
      ]
    },
    {
      //吉他谱-中国风
      path: '/zgf',
      component: Layout,
      children: [
        {
          path: '/',
          component: ZGF,
          meta: { title: '中国风' }
        }
      ]
    },
    {
      //吉他谱-欧美
      path: '/om',
      component: Layout,
      children: [
        {
          path: '/',
          component: OM,
          meta: { title: '欧美' }
        }
      ]
    },
    {
      //吉他谱-日韩
      path: '/rh',
      component: Layout,
      children: [
        {
          path: '/',
          component: RH,
          meta: { title: '日韩' }
        }
      ]
    },

  ],
});
