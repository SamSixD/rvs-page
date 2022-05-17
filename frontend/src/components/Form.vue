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
      name: this.propName,
      position: this.propPosition,
      score: this.propScore
    }
  },
  methods: {
    handleSubmit() {

      this.$emit("submitEve",this.name, this.position, this.score)


// console.log(game.toString(),JSON.stringify(game))


    }
  },props:{propName:String,propPosition:Number,propScore:Number}
}
</script>

<style scoped>

</style>