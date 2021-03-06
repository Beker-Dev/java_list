import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/especialidade/listar',
    name: 'especialidade-listar',
    component: () => import(/* webpackChunkName: "list" */ '../views/especialidade/especialidade-list.vue')
  },
  {
    path: '/especialidade/cadastrar',
    name: 'especialidade-cadastrar',
    component: () => import(/* webpackChunkName: "form" */ '../views/especialidade/especialidade-form.vue')
  },
  {
    path: '/especialidade/detalhar/:id',
    name: 'especialidade-detalhar',
    props: (router) => ({ id: router.params.id}),
    component: () => import('../views/especialidade/especialidade-detail.vue')
  },
  {
    path: '/especialidade/editar/:id',
    name: 'especialidade-editar',
    props: (router) => ({ id: router.params.id}),
    component: () => import('../views/especialidade/especialidade-edit.vue')
  },
  {
    path: '/convenio/listar',
    name: 'convenio-listar',
    component: () => import('../views/convenio/convenio-list.vue')
  },
  {
    path: '/convenio/cadastrar',
    name: 'convenio-cadastrar',
    component: () => import('../views/convenio/convenio-form.vue')
  },
  {
    path: '/convenio/detalhar/:id',
    name: 'convenio-detalhar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/convenio/convenio-detail.vue')
  },
  {
    path: '/convenio/editar/:id',
    name: 'convenio-editar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/convenio/convenio-edit.vue')
  },
  {
    path: '/medico/listar',
    name: 'medico-listar',
    component: () => import("../views/medico/medico-list.vue")
  },
  {
    path: '/medico/cadastrar',
    name: 'medico-cadastrar',
    component: () => import("../views/medico/medico-form.vue")
  },
  {
    path: '/medico/detalhar/:id',
    name: 'medico-detalhar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/medico/medico-detail.vue')
  },
  {
    path: '/medico/editar/:id',
    name: 'medico-editar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/medico/medico-edit.vue')
  },
  {
    path: '/paciente/listar',
    name: 'paciente-listar',
    component: () => import("../views/paciente/paciente-list.vue")
  },
  {
    path: '/paciente/cadastrar',
    name: 'paciente-cadastrar',
    component: () => import("../views/paciente/paciente-form.vue")
  },
  {
    path: '/paciente/detalhar/:id',
    name: 'paciente-detalhar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/paciente/paciente-detail.vue')
  },
  {
    path: '/paciente/editar/:id',
    name: 'paciente-editar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/paciente/paciente-edit.vue')
  },
  {
    path: '/secretaria/listar',
    name: 'secretaria-listar',
    component: () => import("../views/secretaria/secretaria-list.vue")
  },
  {
    path: '/secretaria/cadastrar',
    name: 'secretaria-cadastrar',
    component: () => import("../views/secretaria/secretaria-form.vue")
  },
  {
    path: '/secretaria/detalhar/:id',
    name: 'secretaria-detalhar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/secretaria/secretaria-detail.vue')
  },
  {
    path: '/secretaria/editar/:id',
    name: 'secretaria-editar',
    props: (router) => ({id: router.params.id}),
    component: () => import('../views/secretaria/secretaria-edit.vue')
  },
  {
    path: '/agenda/listar',
    name: 'agenda-listar',
    component: () => import("../views/agenda/agenda-list.vue")
  },
  {
    path: '/historico/listar',
    name: 'historico-listar',
    component: () => import("../views/historico/historico-list.vue")
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
