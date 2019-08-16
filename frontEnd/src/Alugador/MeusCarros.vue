<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container v-if="carrosParaAlugar != ''">
                <v-layout row wrap>
                    <v-flex xs6 v-for="carros in carrosParaAlugar"
                    :key="carros.id"
                    style="padding: 5px">
                        <v-card
                        max-width="800"
                        class="mx-auto">
                        </v-card>

                        <v-toolbar color="light-blue" dark>
                            <v-toolbar-title>{{carros.modelo}}</v-toolbar-title>
                            <v-spacer></v-spacer>
                                <v-btn
                                text
                                @click="habilitarCamposParaEdicao(carros);campoEditavel = !campoEditavel">
                                    <v-icon color="white">mdi-border-color</v-icon>
                                </v-btn>  

                                <v-btn
                                text
                                @click="excluirCarro(carros)">
                                    <v-icon color="white">mdi-delete</v-icon>
                                </v-btn>    
                        </v-toolbar>

                        <v-spacer></v-spacer>

                        <v-list-item class="elevation-3">
                            <v-list-item-content>
                                <b>
                                
                                <v-text-field
                                v-model="carros.kmRodados"
                                :disabled="!campoEditavel"
                                label="KM's Rodados:"
                                ></v-text-field>
                                
                                <v-text-field
                                v-model="carros.docCarro"
                                :disabled="!campoEditavel"
                                label="Documento do Carro:">
                                </v-text-field>

                                <v-text-field
                                v-model="carros.tipoCombustivel"
                                :disabled="!campoEditavel"
                                label="Tipo de Combustível:">
                                </v-text-field>

                                <v-text-field
                                v-model="carros.marca"
                                :disabled="!campoEditavel"
                                label="Marca:">
                                </v-text-field>

                                <v-text-field
                                v-model="carros.modelo"
                                :disabled="!campoEditavel"
                                label="Modelo:">
                                </v-text-field>

                                <v-text-field
                                v-model="carros.placa"
                                :disabled="!campoEditavel"
                                label="Placa:">
                                </v-text-field>
                                </b>

                                <v-flex>
                                    <v-btn v-if="campoEditavel != false"
                                    class="light-blue"
                                    style="float: right; color: white"
                                    @click="editarCarro()">
                                        Salvar
                                    </v-btn>
                                </v-flex>

                            </v-list-item-content>
                        </v-list-item>
                    </v-flex>
                </v-layout>
            </v-container>

            <v-container v-else>
                <v-card max-width="500px">
                    <v-card-title style="color:white" class="light-blue"> Ops...
                        <v-spacer></v-spacer>
                        <v-icon color="white">mdi-emoticon-sad</v-icon>
                    </v-card-title>
                    <v-card-text 
                    class="elevation-2">
                        Você não possui nenhum carro publicado até o momento
                    </v-card-text>
                </v-card>
            </v-container>
        </v-container>
    </div>
</template>

<script>
import service from './AlugadorService.js'

export default {
    created(){
        this.pegarTodosOsCarros()
    },

    data(){
        return{
            carrosParaAlugar: [],
            campoEditavel: false,
            objetoParaEditar: {},
        }
    },
    
    methods:{
        pegarTodosOsCarros(){
            service.todos()
            .then(resposta => {
                console.log(resposta)
                this.carrosParaAlugar = resposta.data
            }).catch(erro => console.log(erro))
        },

        excluirCarro(carro){
            console.log(carro)
            service.apaga(carro)
            this.carrosParaAlugar.splice(carro, 1);
        },
        
        habilitarCamposParaEdicao(carro){
            this.objetoParaEditar = carro
            console.log(this.objetoParaEditar)
        },
        async editarCarro(){
            await service.atualiza(this.objetoParaEditar)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.campoEditavel = !this.campoEditavel
        }
    }
}
</script>

<style>

</style>
