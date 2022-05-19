<template>
<!--  <table>-->
  <el-table
      :data="games"
      :header-cell-style="{color:'black'}"
      border
      stripe
      style="width: 100%">


    <el-table-column
        prop="position"
        label="Position"
        >
    </el-table-column>
    <el-table-column
        prop="name"
        label="Name"
        >
    </el-table-column>
    <el-table-column
        prop="score"
        label="Score"
        >
    </el-table-column>

    <el-table-column
        prop="id"

        label="Delete">
      <template slot-scope="{row}">
        <el-button

            type="primary"
            native-type="button"

            @click="handleDeletion(row.id)"
        >
          Delete Entry
        </el-button>
      </template>
    </el-table-column>



  </el-table>
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

    handleWhat(what) {
        console.log(what);


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