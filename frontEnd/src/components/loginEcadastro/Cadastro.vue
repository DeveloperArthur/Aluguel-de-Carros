<template>
    <dir>
        <v-container style="width: 700px; margin-top: 60px" grid-list-md>
            <v-form class="elevation-2" style="height: auto" v-model="formularioCompleto">
                <p class="headline text-center ; indigo darken-3"
                style="color: white"> Nova Conta</p>
                <v-layout row wrap>
                    <v-radio-group 
                    style="margin-left: 100px"
                    v-model="novaConta.perfil" 
                    v-for="profs in perfils"
                    :key="profs"
                    >
                        <v-radio color="indigo darken-2" :label="profs" :value="profs"></v-radio>
                    </v-radio-group>
                </v-layout>

                <v-content style="margin-left: 30px; margin-right: 30px">
                    <v-layout row wrap>
                        <v-flex xs6>
                            <v-text-field
                                v-model="novaConta.nome"
                                label="Nome:"
                                :rules="[rules.required]">
                            </v-text-field>
                        </v-flex>
                        
                        <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.endereco.logradouro"
                            label="Logradouro:"
                            :rules="[rules.required]">
                            </v-text-field>
                        </v-flex>

                        <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.cpf"
                            v-mask="cpfMask"
                            :rules="[rules.required, rules.validateCPF]"
                            label="CPF:">
                            </v-text-field>
                        </v-flex>

                         <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.endereco.bairro"
                            :rules="[rules.required]"
                            label="Bairro:">
                            </v-text-field>
                        </v-flex>

                        <v-flex xs6 v-if="novaConta.perfil != 'alugador'">
                            <v-text-field
                            v-model="novaConta.cnh"
                            :rules="[rules.required, rules.minCNH]"
                            v-mask="cnhMask"
                            label="CNH:">
                            </v-text-field>
                        </v-flex>

                         <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.endereco.numero"
                            :rules="[rules.required]"
                            type="number"
                            label="Número:">
                            </v-text-field>
                        </v-flex>              

                        <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.telefone"
                            :rules="[rules.required]"
                            label="Telefone:"
                            v-mask="telefoneMask"
                            placeholder="Digite seu telefone/celular">
                            </v-text-field>
                        </v-flex>

                        <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.endereco.cidade"
                            :rules="[rules.required]"
                            label="Cidade:">
                            </v-text-field>
                        </v-flex>

                        <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.email"
                            :rules="[rules.required, rules.email]"
                            label="E-mail:">
                            </v-text-field>
                        </v-flex>

                        <v-flex xs3>
                            <v-select
                            v-model="novaConta.endereco.estado"
                            :rules="[rules.required]"
                            :items="estados"
                            label="Estado:">
                            </v-select>
                        </v-flex>

                        <v-flex xs3>
                             <v-text-field
                            v-model="novaConta.endereco.complemento" 
                            label="Complemento:"
                            color="indigo darken-2">
                            </v-text-field>
                        </v-flex>

                        <v-flex xs6>
                            <v-text-field
                            v-model="novaConta.senha"
                            :append-icon="mostrarSenhaCadastro? 'mdi-eye' : 'mdi-eye-off'"
                            @click:append="mostrarSenhaCadastro = !mostrarSenhaCadastro"
                            :type="mostrarSenhaCadastro? 'text' : 'password'"
                            :rules="[rules.required,rules.min]"
                            label="Senha:">
                            </v-text-field>
                        </v-flex>
                        
                        <v-flex xs10>
                            <v-btn 
                            color="indigo darken-2"
                            style="color: white;font-size: 13px"
                            :loading="loadingVoltarParaOLogin"
                            @click="voltarParaOLogin">Voltar</v-btn>
                        </v-flex>
                        
                        <v-flex xs2>
                            <v-btn
                            :disabled="!formularioCompleto" 
                            color="indigo darken-2"
                            :loading="loadingFinalizarCriacaoDeConta"
                            style="margin-bottom: 10px; color: white; font-size: 13px"
                            @click="criarConta()"
                            > Finalizar </v-btn>
                        </v-flex>
                    
                    </v-layout>
                </v-content>
            </v-form>
        </v-container>
    </dir>
</template>

<script>
import { mask } from 'vue-the-mask'; 
import service from './CadastroELoginService.js';

export default {
    directives:{
        mask,
    },
    
    data(){
        return{
            cpfMask: '###.###.###-##',
            cnhMask: '###########',
            telefoneMask: '(##)####-####', 
            formularioCompleto: false,
            mostrarSenhaCadastro: false,
            loadingVoltarParaOLogin: false,
            loadingFinalizarCriacaoDeConta: false,
            perfils: ['cliente', 'alugador', 'misto'],
            estados: ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR',
            'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'],
            novaConta: {
                nome: '',
                cpf: '',
                cnh: '',
                telefone: '',
                email: '',
                senha: '',
                perfil: '',
                endereco: {
                    logradouro: '',
                    bairro: '',
                    numero: '',
                    complemento: '',
                    cidade: '',
                    estado: '',
                }
            },
            defaultData: {
                nome: '',
                cpf: '',
                cnh: '',
                telefone: '',
                email: '',
                senha: '',
                perfil: '',
                endereco: {
                    logradouro: '',
                    bairro: '',
                    numero: '',
                    complemento: '',
                    cidade: '',
                    estado: '',
                }
            },
            rules: {
                required: value => !!value || 'Campo Obrigatório',
                min: v => v.length >= 8 || 'Min 8 characters', 
                validateCPF: v => v.length == 14 || 'Digite o CPF completo',
                minCNH: v => v.length == 11 || 'Digite uma CNH válida ',
                email: value => {
                const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                return pattern.test(value) || 'Digite um e-mail válido'
                }
            }
        }
    },

    methods:{

        voltarParaOLogin(){
           this.loadingVoltarParaOLogin = true
           setTimeout(() =>{
                this.loadingVoltarParaOLogin = false
                this.$router.push({path:'/login'})
           }, 1500)   
        },

        async criarConta(){
            this.loadingFinalizarCriacaoDeConta = true
            await service.salva(this.novaConta)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro)) 
            setTimeout(() =>{
                this.loadingFinalizarCriacaoDeConta = false
                this.novaConta = this.defaultData
                this.$router.push({path:'/login'})
            }, 1500)
        },
    }
}
</script>

<style>

</style>
