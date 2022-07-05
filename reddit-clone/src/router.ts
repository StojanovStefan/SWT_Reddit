import { createRouter, createWebHistory } from 'vue-router';
import Home from './views/pages/HomeView.vue';
import Profile from './views/pages/ProfileView.vue';
import Community from './views/pages/CommunityView.vue';
import Login from './views/pages/LoginView.vue';
import Register from './views/pages/RegisterView.vue';
import ManageCommunity from './views/pages/ManageCommunity.vue';
import ManagePost from './views/pages/ManagePost.vue';
import Dashboard from './views/layouts/Dashboard.vue';
import { useCurrentUserStore } from './stores/currentUser';

const routes = [
  {
    path: '/',
    component: Dashboard,
    children: [
      { path: '/', redirect: '/home' },
      { path: '/home', name: 'Home', component: Home },
      {
        path: '/r/:community',
        name: 'Community',
        component: Community
      }
    ]
  },
  {
    path: '/manage/community',
    name: 'CreateCommunity',
    component: ManageCommunity
  },
  {
    path: '/manage/community/:id',
    name: 'ManageCommunity',
    component: ManageCommunity
  },
  {
    path: '/manage/post',
    name: 'CreatePost',
    component: ManagePost
  },
  {
    path: '/manage/post/:id',
    name: 'ManagePost',
    component: ManagePost
  },
  { path: '/login', name: 'Login', component: Login },
  { path: '/register', name: 'Register', component: Register },
  { path: '/profile', name: 'Profile', component: Profile }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach(async (to, from) => {
  const currentUser = useCurrentUserStore();
  if (currentUser.isLogedIn && (to.name == 'Login' || to.name == 'Register')) {
    return { name: 'Home' };
  }
  if (
    !currentUser.isLogedIn &&
    (to.name == 'Profile' || to.path.startsWith('/manage'))
  ) {
    return { name: 'Login' };
  }
});

export default router;
