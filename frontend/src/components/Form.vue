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

      this.$emit("submitEve",this.name, this.position, this.score)


// console.log(game.toString(),JSON.stringify(game))


    }
  }
}
</script>

<style scoped>

</style>