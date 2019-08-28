<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container grid-list-md>
                <v-layout row wrap>
                    <v-flex xs6 
                    v-for="alugados in meusCarrosAlugados"
                    :key="alugados.id">
                        <v-card>
                            <v-card-title  
                            class="elevation-3 light-blue" 
                            style="color: white">
                                Carro alugado: 
                                {{alugados.carro.modelo}}
                            </v-card-title>
                            <v-card-text style="padding-top: 5px">
                                <b>
                                <v-flex xs12>
                                    <v-text-field
                                    label="Cliente:"
                                    disabled
                                    :value="alugados.usuarioCliente.nome">
                                    </v-text-field>
                                </v-flex>
                                <v-flex xs12>
                                    <v-text-field
                                    label="CNH:"
                                    disabled
                                    :value="alugados.usuarioCliente.cnh">
                                    </v-text-field>
                                </v-flex>
                                <v-flex xs12>
                                    <v-text-field
                                    label="Email:"
                                    disabled
                                    :value="alugados.usuarioCliente.email">
                                    </v-text-field>
                                </v-flex>
                                <v-flex xs12>
                                    <v-text-field
                                    label="Telefone:"
                                    disabled
                                    :value="alugados.usuarioCliente.telefone">
                                    </v-text-field>
                                </v-flex>
                                <v-layout row wrap>
                                    <v-flex xs4>
                                        <v-text-field
                                        label="Data inicial:"
                                        disabled
                                        type="date"
                                        :value="alugados.retirada">
                                        </v-text-field>
                                    </v-flex>
                                    <v-flex xs4>
                                        <v-text-field
                                        label="Data final:"
                                        disabled
                                        type="date"
                                        :value="alugados.entrega">
                                        </v-text-field>
                                    </v-flex> 
                                    <v-flex xs4>
                                        <v-text-field
                                        label="Valor a ser recebido:"
                                        disabled
                                        :value="'R$ ' + alugados.valor">
                                        </v-text-field>
                                    </v-flex>
                                </v-layout>
                                </b>
                            </v-card-text>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-container>
    </div>
</template>

<script>
import service from './AlugadorService.js'
export default {

    created(){
        this.pegarMeusCarrosAlugados();
    },
    
    data(){
        return{
            meusCarrosAlugados: [],
            money: {
                decimal: '.',
                thousands: ',',
                prefix: 'R$',
                sufix: '',
                precision: 2,
            }
        }
    },
    
    methods: {
        pegarMeusCarrosAlugados(){
            this.meusCarrosAlugados = []
            service.meusCarrosAlugados()
            .then(resposta => {
                console.log(resposta)
                this.meusCarrosAlugados = resposta.data
            }).catch(erro => console.log(erro))
        },
    }
}
</script>

<style>

</style>