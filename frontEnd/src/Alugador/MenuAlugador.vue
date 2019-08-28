<template>
    <div> 
        <v-container grid-list-xl style="margin-top: 60px">
            <v-form 
            v-model="formularioCompleto"
            ref="formCarros">
                <v-card>
                    <v-card-title class="elevation-3 indigo darken-3 title"
                    style="color:white">Cadastre um veículo
                    
                    </v-card-title>
                    <v-card-text>
                        <v-layout row wrap>
                            <v-flex xs4>
                                <v-text-field
                                v-mask="placaMask"
                                v-model="carro.placa"
                                :rules="[rules.required, rules.minPlaca]"
                                label="Placa:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.kmsRodados"
                                :rules="[rules.required]"
                                type="number"
                                label="KM's Rodados:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.documentoCarro"
                                placeholder="Digite o RENAVAM"
                                :rules="[rules.required, rules.renavam]"
                                v-mask="renavamMask"
                                label="Documento do Carro:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs3>
                                <v-text-field
                                v-model="carro.tipoCombustivel"
                                :rules="[rules.required]"
                                label="Tipo do Combustível:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs3>
                                <v-text-field
                                v-model="carro.marca"
                                label="Marca:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs3>
                                <v-text-field
                                v-model="carro.modelo"
                                :rules="[rules.required]"
                                label="Modelo:">
                                </v-text-field>
                            </v-flex>

                            <v-flex xs3>
                                <v-text-field
                                v-money="money"
                                v-model="carro.valorCarro"
                                :rules="[rules.required]"
                                label="Preço:">
                                </v-text-field>
                            </v-flex>

                            <v-flex>
                                <v-btn
                                color="indigo darken-2"
                                style="color: white; float: right"
                                :disabled="!formularioCompleto"
                                @click="dialogoPublicarCarro = true">Publicar Carro</v-btn>    
                            </v-flex> 

                            <v-dialog v-model="dialogoPublicarCarro" max-width="280px">
                                <v-card>
                                    <v-card-title
                                    class="elevation-2 indigo darken-3"
                                    style="color:white"
                                    >Confirmar Publicação</v-card-title>
                                    <v-card-text 
                                    style="height: 200px; 
                                    font-size: 20px; 
                                    color:black">
                                        <br><br>
                                        Deseja publicar este carro para possíveis locações?
                                        <br><br><br><br>
                                            <v-btn
                                            text
                                            color="indigo darken-3"
                                            @click="dialogoPublicarCarro = false">Não
                                            </v-btn>
                                            <v-btn
                                            text
                                            style="float: right"
                                            color="indigo darken-3"
                                            :disabled="carregandoBtn"
                                            @click="publicarCarro()">Sim
                                            </v-btn>       
                                    </v-card-text>
                                </v-card>
                                <v-snackbar
                                top
                                :timeout="1500"
                                color="error"
                                v-model="snackbarErroCadastro">Já há um carro cadastrado com esta placa
                                    <v-icon color="white">mdi-information</v-icon>
                                </v-snackbar>
                            </v-dialog>
                        </v-layout>
                    </v-card-text>
                </v-card>
            </v-form>
            <deslogado-component 
            @abreDialogo="estaLogado = $event"
            :estaLogado="estaLogado">
            </deslogado-component>
        </v-container>
    </div>
</template>

<script>
import DeslogadoComponent from '../components/ComponentDeslogado.vue'
import {mask} from 'vue-the-mask'
import service from './AlugadorService.js'
import {VMoney} from 'v-money'
import { setTimeout } from 'timers';

export default {
    directives: {
        mask,
        money: VMoney
    },

    computed:{
        dadosUsuarioLogado(){
            return this.$store.state.usuarioLogado
        }
    },

    

    data(){
        return{
            estaLogado: false,
            snackbarErroCadastro: false,
            carregandoBtn: false,
            isInputActive: false,
            formularioCompleto: false,
            placaMask: 'SSS-####',
            renavamMask: '###########',
            dialogoPublicarCarro: false,
            carro: {
                kmsRodados: '',
                documentoCarro: '',
                tipoCombustivel: '',
                marca: '',
                modelo: '',
                placa: '',
                valorCarro: 'R$0.00',
                estaAlugado: false,
            },
            defaultData: {
                kmsRodados: '',
                documentoCarro: '',
                tipoCombustivel: '',
                marca: '',
                modelo: '',
                placa: '',
                valorCarro: 'R$000',
                estaAlugado: false,
            },
            rules: {
                required: value => !!value || 'Campo Obrigatório',
                renavam: v => v.length == 11 || 'Digite um RENAVAM válido',
                minPlaca: v => v.length == 8 || 'Digite uma placa válida',
            },
            money: {
                decimal: ',',
                thousands: '.',
                prefix: 'R$',
                sufix: '',
                precision: 2,
            }
        }
    },

    methods: {
        async publicarCarro(){
            this.carregandoBtn = true;
            let valorSemPrefixo = this.carro.valorCarro.replace('R$','')
            let valorFormatado = valorSemPrefixo.replace(',', '.')
            this.carro.valorCarro = valorFormatado
            if(this.dadosUsuarioLogado.id != 0){
                await service.salva(this.carro)
                .then(resposta => {
                    console.log(resposta)
                    this.carro = this.defaultData
                    this.dialogoPublicarCarro = false;
                    this.carregandoBtn = false;
                    this.$refs.formCarros.reset();
                })
                .catch(erro => {
                    console.log(erro)
                    if(erro.request.status == 409){
                        this.snackbarErroCadastro = true
                    }
                    setTimeout(() => {
                        this.dialogoPublicarCarro = false;
                        this.carregandoBtn = false;
                        this.snackbarErroCadastro = false
                    },1500)
                })
            }else{
                this.estaLogado = true
                this.dialogoPublicarCarro = false;
                this.carregandoBtn = false;       
            }
        },
    },

    components: {
        'deslogado-component': DeslogadoComponent
    }
}
</script>

<style>
    input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    }

    input[type=number] {
        -moz-appearance:textfield;
    }
</style>
