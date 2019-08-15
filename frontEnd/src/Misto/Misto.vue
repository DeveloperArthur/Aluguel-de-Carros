<template>
    <div>
        <app-alugador></app-alugador>
        <app-cliente></app-cliente>
    </div>
</template>

<script>
import MenuAlugador from '../Alugador/MenuAlugador.vue'
import MenuCliente from '../Cliente/MenuCliente.vue'

export default {
    components:{
        'app-alugador': MenuAlugador,
        'app-cliente': MenuCliente
    }
}
</script>

<style>

</style>
