<template>
    <div> 
        <v-container grid-list-xl style="margin-top: 60px">
            <v-form >
                <v-card>
                    <v-card-title class="elevation-3 indigo darken-3 title"
                    style="color:white">Cadastre um veículo
                    
                    </v-card-title>
                    <v-card-text>
                        <v-layout row wrap>
                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.placa"
                                label="Placa:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.kmRodados"
                                type="number"
                                label="KM's Rodados:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.docCarro"
                                label="Documento do Carro:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.tipoCombustivel"
                                label="Tipo do Combustível:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.marca"
                                label="Marca:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.modelo"
                                label="Modelo:">
                                </v-text-field>
                            </v-flex>

                            <v-flex>
                                <v-btn
                                color="indigo darken-2"
                                style="color: white; float: right"
                                @click="publicarCarro()">Publicar Carro</v-btn>    
                            </v-flex> 

                        </v-layout>
                    </v-card-text>
                </v-card>
            </v-form>
        </v-container>
    </div>
</template>

<script>
import service from './AlugadorService.js'

export default {

    data(){
        return{
            carro: {
                kmRodados: '',
                docCarro: '',
                tipoCombustivel: '',
                marca: '',
                modelo: '',
                placa: ''
            },
            defaultData: {
                kmRodados: '',
                docCarro: '',
                tipoCombustivel: '',
                marca: '',
                modelo: '',
                placa: ''
            },
            
        }
    },
    methods: {
        async publicarCarro(){
            await service.salva(this.carro)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.carro = this.defaultData
        },
    }
}
</script>

<style>

</style>
