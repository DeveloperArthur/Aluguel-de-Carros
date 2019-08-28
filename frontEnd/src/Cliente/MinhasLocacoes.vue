<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container v-if="carrosAlugados != ''" grid-list-md>
                <v-layout row wrap>
                    <v-flex xs6 v-for="locacoes in carrosAlugados"
                    :key="locacoes.id"
                    style="padding: 5px">
                        <v-card 
                        max-width="800px"
                        height="660px">
                            <v-toolbar color="light-blue" dark>
                                <v-toolbar-title>{{locacoes.carro.modelo}}</v-toolbar-title>
                                <v-spacer></v-spacer>
                                    <v-btn
                                    text
                                    @click="editarCamposAluguel = !editarCamposAluguel">
                                        <v-icon color="white">mdi-border-color</v-icon>
                                    </v-btn>
                                    <v-btn
                                    text
                                    @click="dialogoDeExclusao = true;pegarLocacaoParaExcluir(locacoes)">
                                        <v-icon color="white">mdi-delete</v-icon>
                                    </v-btn>
                            </v-toolbar>

                            <v-spacer></v-spacer>
                            
                            <v-layout row wrap class="ml-2">
                                <v-flex xs11>                              
                                    <v-text-field
                                    v-model="locacoes.carro.kmsRodados"
                                    disabled
                                    label="KM's Rodados:"
                                    ></v-text-field>    
                                </v-flex>

                                <v-flex xs11>
                                    <v-text-field
                                    v-model="locacoes.carro.documentoCarro"
                                    disabled
                                    label="Documento do Carro:">
                                    </v-text-field>
                                </v-flex>

                                <v-flex xs11>
                                    <v-text-field
                                    v-model="locacoes.carro.tipoCombustivel"
                                    disabled
                                    label="Tipo de Combustível:">
                                    </v-text-field>
                                </v-flex>

                                <v-flex xs11>
                                    <v-text-field
                                    v-model="locacoes.carro.marca"
                                    disabled
                                    label="Marca:">
                                    </v-text-field>
                                </v-flex>

                                <v-flex xs11>
                                    <v-text-field
                                    v-model="locacoes.carro.modelo"
                                    disabled
                                    label="Modelo:">
                                    </v-text-field>
                                </v-flex>

                                <v-flex xs11>
                                    <v-text-field
                                    v-model="locacoes.carro.placa"
                                    disabled
                                    label="Placa:">
                                    </v-text-field>
                                </v-flex>
                            
                                <v-flex xs4>                                      
                                    <v-text-field
                                    label="Retirada:"
                                    type="date"
                                    :rules="[rules.dataRetirada]"
                                    :disabled="editarCamposAluguel"
                                    v-model="locacoes.retirada">
                                    </v-text-field>
                                </v-flex>

                                <v-flex xs4>
                                    <v-text-field
                                    label="Entrega:"
                                    type="date"
                                    :rules="[locacoes.entrega > locacoes.retirada || 'A data deve ser maior que o dia de retirada']"
                                    :disabled="editarCamposAluguel"
                                    v-model="locacoes.entrega">
                                    </v-text-field>
                                </v-flex>
                                
                                <v-flex xs3>
                                    <v-text-field
                                    label="Valor:"
                                    disabled
                                    v-model="locacoes.valor">
                                    </v-text-field>
                                </v-flex>

                                <v-flex v-if="editarCamposAluguel != true" class="mr-8">
                                    <v-btn 
                                    class="light-blue"
                                    :disabled="!precoCalculado"
                                    style="color:white; float: right"
                                    @click="editarLocacao(locacoes)">
                                        Salvar
                                    </v-btn>

                                    <v-btn
                                    class="light-blue"
                                    style="color:white; float: right; margin-right: 10px"
                                    @click="calcularValorDoAluguel(locacoes)">
                                        Recalcular aluguel
                                    </v-btn>
                                </v-flex>
                            </v-layout>   
                        </v-card>
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
                        Você não possui nenhuma locação até o momento...
                    </v-card-text>
                </v-card>
            </v-container>

            <v-dialog v-model="dialogDevolucaoDeCarros" max-width="500px">
                <v-card max-height="500px">
                    <v-card-title class="headline; indigo darken-3" style="color: white"> Olá
                        <v-spacer></v-spacer>
                        <v-btn text @click="dialogDevolucaoDeCarros = false">
                            <v-icon color="white">mdi-close</v-icon>
                        </v-btn>
                    </v-card-title>   
                    <br>
                    <v-card-text style="font-size: 15px" class="text-center">
                        <b><p style="color:black; font-size:"
                        >Viemos comunicar que você possui {{carrosParaDevolucao.length}} 
                        carros pendentes para devolução
                        </p></b>
                        <br>
                        <v-list v-for="carros in carrosParaDevolucao" :key="carros.id">
                            <v-list-item>
                                <b><p style="color:black">Modelo: {{carros.carro.modelo}}  | Placa: {{carros.carro.placa}}  |
                                    <v-btn text color="indigo darken-3" @click="carroEntregue(carros)">
                                    Carro entregue
                                    </v-btn> 
                                </p></b>
                            </v-list-item>
                        </v-list>
                    </v-card-text>
                </v-card>
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
        this.pegarMinhasLocacoes()
        this.verificarADataAtual('-')
    },

    data(){
        return{
            dialogDevolucaoDeCarros: false,
            carrosParaDevolucao: [],
            precoCalculado: false,
            dialogoDeExclusao: false,
            carrosAlugados: [],
            dataMask: '##/##/####',
            editarCamposAluguel: true,
            objetoParaExcluir: {},
            dataAtual: '',
            rules: {
                dataRetirada: v => v >= this.dataAtual || 'A data deve ser maior ou igual ao dia atual'
            },
            money:{
                decimal: '.',
                thousands: ',',
                prefix: 'R$',
                sufix: '',
                precision: 2,
            }
        }
    },

    methods:{
        pegarMinhasLocacoes(){
            service.todasMinhasLocacoes()
            .then(resposta => {
                console.log(resposta)
                this.carrosAlugados = resposta.data
                for(let i = 0; i < this.carrosAlugados.length; i++){
                    this.carrosAlugados[i].valor = "R$ " + this.carrosAlugados[i].valor
                }
                this.verificaEntregaDoCarro()
            }).catch(erro => console.log(erro))
        },

        async editarLocacao(locacoes){
            let locacaoFormatada = locacoes
            let valorFormatado = locacaoFormatada.valor.replace('R$ ', '')
            locacaoFormatada.valor = valorFormatado
            await service.atualiza(locacaoFormatada)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.editarCamposAluguel = true
            this.precoCalculado = false
        },

        pegarLocacaoParaExcluir(locacoes){
            this.objetoParaExcluir = locacoes
        },

        excluirLocacao(){
            service.apaga(this.objetoParaExcluir)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.carrosAlugados.splice(this.objetoParaExcluir,1)
            this.dialogoDeExclusao = false
        },

        carroEntregue(locacao){
            console.log(locacao)
            service.apaga(locacao)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.carrosAlugados.splice(this.locacao,1)
            this.dialogDevolucaoDeCarros = false
        },

        calcularValorDoAluguel(locacao){
            var moment = require('moment');
            moment().format();
            let dataRetirada = moment(locacao.retirada, 'YYYY/MM/DD')
            let dataEntrega = moment(locacao.entrega, 'YYYY/MM/DD')
            let quantidadeDias = dataEntrega.diff(dataRetirada, 'days')
            let valorDoAluguel = quantidadeDias * locacao.carro.valorCarro;
            this.formatarValorDoAluguel(valorDoAluguel, locacao)
        },

        formatarValorDoAluguel(valorDoAluguel,locacao){
            locacao.valor = 'R$ ' + (valorDoAluguel/1).toFixed(2).replace(',', '.')
            this.precoCalculado = true
            return locacao.valor.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")     
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

        verificaEntregaDoCarro(){
            this.carrosParaDevolucao = []
            for(let i = 0; i < this.carrosAlugados.length; i++){
                if(this.carrosAlugados[i].entrega == this.dataAtual){
                    this.carrosParaDevolucao.push(this.carrosAlugados[i])
                    this.dialogDevolucaoDeCarros = true    
                } 
            }
            console.log(this.carrosParaDevolucao)
        }  
    }
}
</script>

<style>

</style>
