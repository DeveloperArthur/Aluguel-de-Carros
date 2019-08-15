<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container>
                <v-card>
                   
                    <v-card-title class="elevation-3 indigo darken-3 title"
                    style="color:white"> Selecione o carro desejado

                        <v-spacer></v-spacer>

                        <v-tooltip bottom v-if="perfilUsuario == 'misto'">
                            <template v-slot:activator="{on}">
                                <v-btn 
                                class="indigo darken-2"
                                style="color: white"
                                @click="atualizarCarros"
                                v-on="on">
                                    <v-icon>mdi-reload</v-icon>
                                </v-btn>
                            </template>
                            Atualizar lista de carros    
                        </v-tooltip>

                    </v-card-title>
                    <v-card-text>
                        <v-data-table
                        v-model="selecionado"
                        :headers="header"
                        :items="carrosParaAlugar"
                        show-select
                        :single-select="true"
                        item-key='placa'
                        >
                        </v-data-table> 
                    </v-card-text>
                </v-card>
            </v-container>
            <v-btn 
                :disabled="selecionado.length == 0"
                class="indigo darken-3"
                style="color: white; float: right; margin-right: 12px"
                @click="dialogAluguel = true">Alugar Carro
            </v-btn>

            <v-dialog v-model="dialogAluguel" max-width="600px"> 
                <v-form>
                    <v-card>
                        <v-card-title 
                        class="indigo darken-3"
                        style="color: white">Preencha o tempo para locação</v-card-title>
                        <v-card-text>       
                            <v-container grid-list-md>
                                <v-layout row wrap>
                                    <v-flex xs6>
                                        <v-text-field
                                        v-mask="dataMask"
                                        v-model="aluguel.dataRet"
                                        label="Data de retirada:">
                                        </v-text-field>
                                    </v-flex>

                                    <v-flex xs6>
                                        <v-text-field
                                        v-model="aluguel.dataEnt"
                                        v-mask="dataMask"
                                        label="Data da entrega:">
                                        </v-text-field>
                                    </v-flex>
                                    
                                    <v-flex xs4>
                                        <v-text-field
                                        v-model="aluguel.valor"
                                        disabled
                                        label="Valor a pagar:">
                                        </v-text-field>
                                    </v-flex>

                                    <v-flex xs8>
                                        <v-btn
                                        style="float: right; margin-top: 20px; color: white"
                                        class="indigo darken-3"
                                        @click="alugarCarro()">
                                            Finalizar
                                        </v-btn>
                                    </v-flex>    
                                </v-layout>
                            </v-container>
                        </v-card-text>
                    </v-card>
                </v-form>
            </v-dialog>
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
        this.pegarCarros()
    }, 

    computed: {
        coletarCarros(){
            return this.$store.state.carrosParaAlugar
        },
        perfilUsuario(){
            return this.$store.state.usuarioLogado.perfil
        }
    },

    data(){
        return{
            dataMask: '##/##/####',
            dialogAluguel: false,
            aluguel:{
                dataRet:'',
                dataEnt:'',
                valor:'' 
            },
            header:[
                {text: 'Placa', value: 'placa', sortable: false},
                {text: 'Documento do Carro', value:'docCarro', sortable: false},
                {text: 'Modelo', value:'modelo'},
                {text: 'Marca', value:'marca'},
                {text: "KM's Rodados", value: 'kmRodados'},
                {text: 'Tipo de Combustível', value:'tipoCombustivel'},
            ],
            selecionado: [],
            carrosParaAlugar: []
            
        }
    },
    
    methods:{
        async alugarCarro(){
            let locacao = {
                ...this.selecionado[0],
                ...this.aluguel
            }
            await service.salva(locacao)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.dialogAluguel = false     
        },
        
        pegarCarros(){
            service.todosCarrosParaAlugar()
            .then(resposta => {
                console.log(resposta)
                this.carrosParaAlugar = resposta.data
            }).catch(erro => console.log(erro))
        },
        
        atualizarCarros(){
            this.pegarCarros()
        }
    }
}
</script>

<style>

</style>
