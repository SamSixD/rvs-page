<template>
  <table>
    <tr>
      <th>Position</th>
      <th>Name</th>
      <th>Score</th>
      <th>Remove Game</th>
      <th>id-test</th>
    </tr>
    <tr v-for="game in games">
      <td>{{ game.position }}</td>
      <td>{{ game.name }}</td>
      <td>{{ game.score }}</td>
      <td>
        <button @click.prevent="handleDeletion(game.id)">Delete Game</button>
      </td>
    </tr>
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
          .then(data => this.games = data)
    },

    handleDeletion(gameId) {

      fetch('/games/' + gameId, {
        method: "DELETE",
      }).then(result => {
        console.log(gameId + 'has been deleted!');
      }).catch(err => console.log(err));
    }
  },

  mounted() {
    this.getGames()
  }

}

</script>

<style scoped>

</style>