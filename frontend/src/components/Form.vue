<template>

  <el-form ref="form" :model="form" label-width="120px">
    <el-form-item label="position">
      <el-input v-model="position"></el-input>
    </el-form-item>
    <el-form-item label="name">
      <el-input v-model="name"></el-input>
    </el-form-item>
    <el-form-item label="score">
      <el-input v-model="score"></el-input>
    </el-form-item>



    <el-form-item>
      <el-button @click.prevent="handleSubmit">submit</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
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


    },
    onSubmit() {
      console.log(this.position,this.name,this.score)
    }
  },props:{propName:String,propPosition:Number,propScore:Number}
}
</script>

<style scoped>

</style>