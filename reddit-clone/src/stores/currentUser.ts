import { defineStore } from 'pinia';
import { SERVER_URI } from '../constants';
import { useStorage } from '@vueuse/core';

export const useCurrentUserStore = defineStore({
  id: 'currentUser',
  state: () => ({
    currentUser: useStorage('currentUser', {
      user_data: null,
      access_token: null,
      refresh_token: null
    })
  }),
  actions: {
    async login(username: string, password: string) {
      const userData = await apiLogin(username, password);

      this.$patch({
        currentUser: userData
      });
    },
    logout() {
      this.currentUser.user_data = null;
      this.currentUser.access_token = null;
      this.currentUser.refresh_token = null;
    }
  },
  getters: {
    isLogedIn(state) {
      return (
        state.currentUser !== null &&
        state.currentUser.user_data !== null &&
        state.currentUser.access_token !== null
      );
    }
  }
});

async function apiLogin(username: string, password: string) {
  const response = await fetch(SERVER_URI + '/login', {
    mode: 'cors',
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: new URLSearchParams({
      username: username,
      password: password
    })
  });

  const userdata = await response.json();

  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return userdata;
}
