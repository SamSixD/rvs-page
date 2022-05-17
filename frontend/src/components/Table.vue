<template>
  <table>
    <tr>
      <th>Position</th>
      <th>Name</th>
      <th>Score</th>
      <th>Remove Game</th>
    </tr>
    <template v-for="game in games">

      <tr>
        <td>{{ game.position }}</td>
        <td>{{ game.name }}</td>
        <td>{{ game.score }}</td>
        <td>
          <button @click.prevent="handleDeletion(game.id)">Delete Game</button>
        </td>
      </tr>

      <tr>
        <td> </td>
        <td> <button @click="awesome = !awesome">Toggle</button> </td>
      </tr>


      <tr v-if="awesome === awesome">

        <td> <input type="text" id="updatePos"> </td>
        <td> <input type="text" id="updateName"> </td>
        <td> <input type="text" id="updateScore"> </td>
        <td>

          <button @click.prevent="handleUpdate(game.id)">Update Game</button>
        </td>
      </tr>


    </template>



  </table>
</template>

<script>



export default {
  name: "Table", data() {
    return {games: []}

  },
  methods: {

    getGames() {
      fetch('/games?maxScore=100000&maxPos=100000')

          .then(response => response.json())
          .then(data => {this.games = data;
            console.log('game list has been populated!')}
          )
    },

    handleDeletion(gameId) {
      fetch('/games/' + gameId, {
        method: "DELETE",
      }).then(result => {
        console.log(gameId + 'has been deleted!');this.getGames();
      }).catch(err => console.log(err));

    },
    handleUpdate(gameId) {
      var pos = (document.getElementById("updatePos").value);
      var name = (document.getElementById("updateName").value);
      var score = (document.getElementById("updateScore").value);


   console.log(gameId,pos,name,score)

    }
  },

  mounted() {
    this.getGames()
  }

}

</script>

<style scoped>

</style>