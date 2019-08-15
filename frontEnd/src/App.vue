<template>
  <v-app>
    <toolbar-component></toolbar-component>
    <router-view></router-view>
  </v-app>
</template>

<script>
import Toolbar from './components/Toolbar';

export default {
  components: {
    'toolbar-component': Toolbar,
  }
};
</script>

<style>

</style>
