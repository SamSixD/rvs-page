<template>
  <table>
    <tr>
      <th>Position</th>
      <th>Name</th>
      <th>Score</th>
      <th>Remove Game</th>
    </tr>

    <template v-for="(game,index) in games">

      <tr>
        <td>{{ game.position }}</td>
        <td>{{ game.name }}</td>
        <td>{{ game.score }}</td>
        <td>
          <button @click.prevent="handleDeletion(game.id)">Delete Game</button>
        </td>
      </tr>

      <tr>
        <td></td>
        <td>
          <button @click.prevent="updateOpen=index"></button>
        </td>
      </tr>


      <tr v-if="index===updateOpen">

        <td>
          <Form :key="index" @submitEve="(n,p,s)=>handleUpdate(game.id,n,p,s)" :prop-name="game.name"
                :prop-position="game.position" :prop-score="game.score">

          </Form>
        </td>


      </tr>


    </template>


  </table>
</template>

<script>


import Form from "@/components/Form";

export default {
  name: "Table",
  components: {Form},
  data() {
    return {games: [], updateOpen: null}

  },
  methods: {

    getGames() {
      fetch('/games?maxScore=100000&maxPos=100000')

          .then(response => response.json())
          .then(data => {
                this.games = data;
                console.log('game list has been populated!')
              }
          )
    },

    handleDeletion(gameId) {
      fetch('/games/' + gameId, {
        method: "DELETE",
      }).then(result => {
        console.log(gameId + 'has been deleted!');
        this.getGames();
      }).catch(err => console.log(err));

    },

    handleUpdate(gameId, nameAdd, positionAdd, scoreAdd) {
      const game = {name: nameAdd, position: positionAdd, score: scoreAdd}
      const address = '/games/' + gameId
      fetch(address, {
        method: "PUT",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(game)
      }).then(result => {
        console.log(result);
        this.getGames()
      })
    }
  },

  mounted() {
    this.getGames()
  }

}

</script>

<style scoped>

</style>