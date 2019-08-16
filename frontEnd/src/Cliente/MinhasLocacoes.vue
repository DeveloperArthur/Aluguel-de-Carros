<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container>
                <v-layout row wrap>
                    <v-flex xs6 v-for="locacoes in carrosAlugados"
                    :key="locacoes.id"
                    style="padding: 5px">
                        <v-card 
                        max-width="800"
                        class="mx-auto">
                            <v-toolbar color="light-blue" dark>
                                <v-toolbar-title>{{locacoes.modelo}}</v-toolbar-title>
                                <v-spacer></v-spacer>
                                    <v-btn
                                    text
                                    @click="editarCamposAluguel = !editarCamposAluguel">
                                        <v-icon color="white">mdi-border-color</v-icon>
                                    </v-btn>
                                    <v-btn
                                    text
                                    @click="excluirLocacao(locacoes)">
                                        <v-icon color="white">mdi-delete</v-icon>
                                    </v-btn>
                            </v-toolbar>
                            <v-spacer></v-spacer>
                            <v-form >
                                <v-list-item class="elevation-3">
                                    <v-list-item-content>
                                        <b>
                                
                                        <v-text-field
                                        :value="locacoes.kmRodados"
                                        disabled
                                        label="KM's Rodados:"
                                        ></v-text-field>
                                        
                                        <v-text-field
                                        :value="locacoes.docCarro"
                                        disabled
                                        label="Documento do Carro:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.tipoCombustivel"
                                        disabled
                                        label="Tipo de Combustível:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.marca"
                                        disabled
                                        label="Marca:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.modelo"
                                        disabled
                                        label="Modelo:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.placa"
                                        disabled
                                        label="Placa:">
                                        </v-text-field>

                                        <v-container grid-list-md>
                                            <v-layout row wrap>
                                                <v-flex xs4>
                                                    <v-text-field
                                                    label="Retirada:"
                                                    v-mask="dataMask"
                                                    :disabled="editarCamposAluguel"
                                                    v-model="locacoes.dataRet">
                                                    </v-text-field>
                                                </v-flex>

                                                <v-flex xs4>
                                                    <v-text-field
                                                    label="Entrega:"
                                                    v-mask="dataMask"
                                                    :disabled="editarCamposAluguel"
                                                    v-model="locacoes.dataEnt">
                                                    </v-text-field>
                                                </v-flex>

                                                <v-flex xs4>
                                                    <v-text-field
                                                    label="Valor:"
                                                    disabled
                                                    :value="locacoes.valor">
                                                    </v-text-field>
                                                </v-flex>
                                            </v-layout>
                                        </v-container>
                                        </b>
                                        <v-flex>
                                            <v-btn v-if="editarCamposAluguel != true"
                                            class="light-blue"
                                            style="color:white; float: right"
                                            @click="editarLocacao(locacoes)">
                                                Salvar
                                            </v-btn>
                                        </v-flex>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-form>    
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-container>
    </div>
</template>

<script>
import service from './ClienteService.js'
import { mask } from 'vue-the-mask'

export default {
     directives:{
        mask,
    },

    created(){
        this.pegarMinhasLocacoes()
    },

    data(){
        return{
            carrosAlugados: [],
            dataMask: '##/##/####',
            editarCamposAluguel: true,
        }
    },

    methods:{
        pegarMinhasLocacoes(){
            service.todasMinhasLocacoes()
            .then(resposta => {
                console.log(resposta)
                this.carrosAlugados = resposta.data
            }).catch(erro => console.log(erro))
        },

        excluirLocacao(locacoes){
            service.apaga(locacoes)
            .then(resposta => console.log(resposta))
            this.carrosAlugados.splice(locacoes,1)
            .catch(erro => console.log(erro))
        },

        async editarLocacao(locacoes){
            await service.atualiza(locacoes)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.editarCamposAluguel = false
        }
    }
}
</script>

<style>

</style>
