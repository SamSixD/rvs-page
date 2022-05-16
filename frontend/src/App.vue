<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <Form @save="handleSave"></Form>
    <Table ref="table"></Table>
  </div>
</template>

<script>

import HelloWorld from './components/HelloWorld.vue';
import Form from "@/components/Form";
import Table from "@/components/Table";


export default {
  name: 'app',
  components: {
    Form,
    Table,
    HelloWorld
  },

  methods:{
    handleSave(){
      this.$refs.table.getGames()
    }
  }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
