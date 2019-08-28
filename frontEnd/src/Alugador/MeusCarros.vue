<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container v-if="carrosParaAlugar != ''">
                <v-layout row wrap>
                    <v-flex xs6 v-for="carros in carrosParaAlugar"
                    :key="carros.id"
                    style="padding: 5px">
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
                                @click="dialogoDeExclusao = true; pegarCarroParaExcluir(carros)">
                                    <v-icon color="white">mdi-delete</v-icon>
                                </v-btn>    
                        </v-toolbar>

                        <v-spacer></v-spacer>
                        <v-form v-model="formularioCompleto">
                            <v-list-item class="elevation-3">
                                <v-list-item-content>
                                    <b>
                                    
                                    <v-text-field
                                    v-model="carros.kmsRodados"
                                    :rules="[rules.required]"
                                    type="number"
                                    :disabled="!campoEditavel"
                                    label="KM's Rodados:"
                                    ></v-text-field>
                                    
                                    <v-text-field
                                    v-model="carros.documentoCarro"
                                    :rules="[rules.required, rules.renavam]"
                                    v-mask="renavamMask"
                                    :disabled="!campoEditavel"
                                    label="Documento do Carro:">
                                    </v-text-field>

                                    <v-text-field
                                    v-model="carros.tipoCombustivel"
                                    :rules="[rules.required]"
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
                                    :rules="[rules.required]"
                                    :disabled="!campoEditavel"
                                    label="Modelo:">
                                    </v-text-field>

                                    <v-text-field
                                    v-model="carros.placa"
                                    :rules="[rules.required]"
                                    v-mask="placaMask"
                                    :disabled="!campoEditavel"
                                    label="Placa:">
                                    </v-text-field>
                                    </b>

                                    <v-flex>
                                        <v-btn v-if="campoEditavel != false"
                                        class="light-blue"
                                        :disabled="!formularioCompleto"
                                        style="float: right; color: white"
                                        @click="editarCarro()">
                                            Salvar
                                        </v-btn>
                                    </v-flex>
                                    
                                </v-list-item-content>
                            </v-list-item>
                            
                        </v-form>
                        <v-dialog v-model="dialogoDeExclusao" width="265px">
                            <v-card>
                                <v-card-title class="elevation-2 indigo darken-3">Confirmar Exclusão</v-card-title>
                                <v-card-text
                                style="font-size:20px;
                                color:black">
                                    <br><br>
                                    Tem certeza que quer excluir este carro?
                                    <br><br><br><br>
                                    <v-btn
                                    text
                                    color="indigo darken-3"
                                    @click="dialogoDeExclusao = false">
                                        Não
                                    </v-btn>
                                    <v-btn
                                    text
                                    style="float: right"
                                    color="indigo darken-3"
                                    @click="excluirCarro()">
                                        Sim
                                    </v-btn>
                                </v-card-text>
                            </v-card>
                        </v-dialog>
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
                    style="height: 250px; color: black;"
                    class="elevation-2
                    text-center
                    headline"><br><br>
                        Você não possui nenhum carro publicado até o momento...
                    </v-card-text>
                </v-card>
            </v-container>
        </v-container>
    </div>
</template>

<script>
import service from './AlugadorService.js'
import {mask} from 'vue-the-mask'

export default {
    directives:{
        mask
    },

    created(){
        this.pegarTodosOsCarros()
    },

    data(){
        return{
            placaMask: 'SSS-####',
            renavamMask: '###########',
            formularioCompleto: false,
            dialogoDeExclusao: false,
            carrosParaAlugar: [],
            campoEditavel: false,
            objetoParaEditar: {},
            objetoParaExcluir: {},
            rules: {
                required: value => !!value || 'Campo Obrigatório',
                renavam: v => v.length == 11 || 'Digite um RENAVAM válido',
                minPlaca: v=> v.length == 8 || 'Digite uma placa válida'
            }
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

        excluirCarro(){
            service.apaga(this.objetoParaExcluir)
            this.carrosParaAlugar.splice(this.objetoParaExcluir, 1); 
            this.dialogoDeExclusao = false
        },
        
        habilitarCamposParaEdicao(carro){
            this.objetoParaEditar = carro
        },
        
        async editarCarro(){
            await service.atualiza(this.objetoParaEditar)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.campoEditavel = !this.campoEditavel
        },
        
        pegarCarroParaExcluir(carros){
            this.objetoParaExcluir = carros
        }
    }
}
</script>

<style>

</style>
