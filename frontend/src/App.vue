<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <EleForm> </EleForm>
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <Form @submitEve="handleSave"></Form>
<!--    <Form @save="handleSave"></Form>-->
    <Table ref="table"></Table>

  </div>
</template>

<script>

import HelloWorld from './components/HelloWorld.vue';
import Form from "@/components/Form";
import Table from "@/components/Table";
import EleForm from "@/components/EleForm.vue";


export default {
  name: 'app',
  components: {
    Form,
    Table,
    HelloWorld,
    EleForm
  },

  methods:{
    handleSave(nameAdd,positionAdd,scoreAdd){
      const game = {name: nameAdd, position: positionAdd, score: scoreAdd}

      fetch('/games', {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(game)
      }).then(result => {
        console.log(result);
        this.$refs.table.getGames()
        //this.$emit("save");

        // console.log(result.translations[0].translation)
        //resolve(result.translations[0].translation);
        //this.getGames();
      });




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
