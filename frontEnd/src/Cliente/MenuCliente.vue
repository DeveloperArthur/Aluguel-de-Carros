<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container>
                <v-card>
                    <v-card-title class="elevation-3 indigo darken-3 title"
                    style="color:white"> Selecione o carro desejado
                        <v-spacer></v-spacer>
                        <v-tooltip bottom>
                            <template v-slot:activator="{on}">
                                <v-btn 
                                class="indigo darken-2"
                                style="color: white"
                                @click="pegarCarros"
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
                        item-key='placa'>
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
                <v-form v-model="formularioCompleto"
                ref="aluguelForms">
                    <v-card>
                        <v-card-title 
                        class="indigo darken-3"
                        style="color: white">Preencha o tempo para locação
                        <v-spacer></v-spacer>
                        <v-btn
                        text
                        @click="resetaDados()">
                            <v-icon color="white">mdi-close</v-icon>
                        </v-btn>
                        </v-card-title>
                        <v-card-text>       
                            <v-container grid-list-md>
                                <v-layout row wrap>
                                    <v-flex xs6>
                                        
                                        <v-text-field
                                        type="date"
                                        :disabled="datasDisativadas"
                                        :rules="[rules.required,rules.dataRetirada]"
                                        v-model="aluguel.retirada"
                                        label="Data de retirada:">
                                        </v-text-field>
                                    </v-flex>

                                    <v-flex xs6>
                                        <v-text-field
                                        v-model="aluguel.entrega"
                                        type="date"
                                        :disabled="datasDisativadas"
                                        :rules="[rules.required, rules.dataEntrega]"
                                        label="Data da entrega:">
                                        </v-text-field>
                                    </v-flex>
                                    
                                    <v-flex xs3>
                                        <v-text-field
                                        v-model="aluguel.valor"
                                        disabled
                                        label="Valor a pagar:">
                                        </v-text-field>
                                    </v-flex>
                                    
                                    <v-flex xs5>
                                        <v-btn
                                        style="float: right; margin: 20px 0px 0px 60px; color: white"
                                        class="indigo darken-3"
                                        :disabled="!formularioCompleto"
                                        @click="calcularValorDoAluguel(aluguel.retirada, aluguel.entrega)">
                                            Calcular aluguel
                                        </v-btn>
                                    </v-flex>
                                    
                                    <v-flex xs4>
                                        <v-btn
                                        style="float: right; margin-top: 20px; color: white"
                                        class="indigo darken-3"
                                        :disabled="!precoCalculado"
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
            <deslogado-component 
            @abreDialogo="estaLogado = $event"
            :estaLogado="estaLogado">
            </deslogado-component>
        </v-container>
    </div>
</template>

<script>
import service from './ClienteService.js'
import { mask } from 'vue-the-mask'
import ComponentDeslogado from '../components/ComponentDeslogado.vue'

export default {
    created(){
        this.pegarCarros()
        this.verificarADataAtual('-')
    }, 

    directives:{
        mask,
    },

    data(){
        return{
            estaLogado: false,
            datasDisativadas: false,
            dataAtual: '', 
            dialogAluguel: false,
            formularioCompleto: false,
            precoCalculado: false,
            selecionado: [],
            carrosParaAlugar: [],
            aluguel:{
                retirada:'',
                entrega:'',
                valor: 'R$ 0,00'
            },
            defaultAluguel: {
                retirada:'',
                entrega:'',
                valor:'R$ 0,00' 
            },
            header:[
                {text: 'Placa', value: 'placa', sortable: false},
                {text: 'Documento do Carro', value:'documentoCarro', sortable: false},
                {text: 'Modelo', value:'modelo'},
                {text: 'Marca', value:'marca'},
                {text: "KM's Rodados", value: 'kmsRodados'},
                {text: 'Tipo de Combustível', value:'tipoCombustivel'},
                {text: 'Valor para Locação', value: 'valorCarro'}
            ],
            rules: {
                required: value => !!value || 'Campo Obrigatório',
                dataEntrega: v => v > this.aluguel.retirada || 'A data deve ser maior que o dia de retirada',
                dataRetirada: v => v >= this.dataAtual || 'A data deve ser maior ou igual ao dia atual'  
            }
        }
    },

    computed: {
        dadosUsuarioLogado(){
            return this.$store.state.usuarioLogado
        }
    },

    components:{
        'deslogado-component': ComponentDeslogado
    },    
    
    methods:{   
        async alugarCarro(){
            let valorAluguelFormatado = this.aluguel.valor.replace('R$ ', '')
            this.aluguel.valor = valorAluguelFormatado
            let locacao = {
                carroId:this.selecionado[0].id,
                ...this.aluguel,
            }
            if(this.dadosUsuarioLogado.id != 0){
                await service.salva(locacao)
                .then(resposta => console.log(resposta))
                .catch(erro => console.log(erro))
                this.carrosParaAlugar.splice(this.selecionado, 1)
                this.resetaDados()
            }else{
                this.estaLogado = true
                this.resetaDados()
            } 
        },
        
        pegarCarros(){
            this.carrosParaAlugar = [];
            service.todosCarrosParaAlugar()
            .then(resposta => {
                console.log(resposta)
                let carros = resposta.data
                for(let i = 0; i < carros.length; i++){
                    if(carros[i].estaAlugado == false){
                        this.carrosParaAlugar.push(carros[i])
                    }
                }        
            }).catch(erro => console.log(erro))
        },
        
        calcularValorDoAluguel(retirada, entrega){
            var moment = require('moment');
            moment().format();
            let dataRetirada = moment(retirada, 'YYYY/MM/DD')
            let dataEntrega = moment(entrega, 'YYYY/MM/DD')
            let quantidadeDias = dataEntrega.diff(dataRetirada, 'days')
            let valorDoAluguel = quantidadeDias * this.selecionado[0].valorCarro;
            this.datasDisativadas = true
            this.formatarValorDoAluguel(valorDoAluguel)
        },

        formatarValorDoAluguel(valorDoAluguel){
            this.aluguel.valor = 'R$ ' + (valorDoAluguel/1).toFixed(2).replace(',', '.')
            this.precoCalculado = true
            return this.aluguel.valor.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") 
        },

        verificarADataAtual(sp){
            let hoje = new Date()
            let DD = hoje.getDate();
            let MM = hoje.getMonth()+1;
            let YYYY = hoje.getFullYear();
            if(DD<10){
                DD='0'+DD;
            } 
            if(MM<10){
                MM='0'+MM;
            } 
            this.dataAtual = YYYY + sp + MM + sp + DD
        },

        resetaDados(){
            this.selecionado = []
            this.dialogAluguel = false
            this.datasDisativadas = false
            this.precoCalculado = false
            this.aluguel = Object.assign({}, this.defaultAluguel)
            this.$refs.aluguelForms.reset();
        }
    }
}
</script>

<style>

</style>
