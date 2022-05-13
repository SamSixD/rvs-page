<template>
  <form>
    <input v-model="position">
    <input v-model="name">
    <input v-model="score">
    <button @click.prevent="handleSubmit">submit</button>
  </form>
</template>

<script>
export default {
  name: "Form",
  data() {
    return {
      name: "",
      position: "",
      score: ""
    }
  },
  methods: {
    handleSubmit() {
      const game = {name: this.name, position: this.position, score: this.score}
// console.log(game.toString(),JSON.stringify(game))
      fetch('/games', {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(game)
      }).then(result => {
        console.log(result);
        resolve(result.translations[0].translation);
      }).catch(err => console.log(err));
    }
  }
}
</script>

<style scoped>

</style>